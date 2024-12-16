package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaService {
  public static final int PERMANCENCIA_MINIMA = 1;
  public static final int PERMANCENCIA_MAXIMA = 5;

  private final UsuarioGateway usuarioGateway;
  private final RestauranteGateway restauranteGateway;
  private final ReservaGateway reservaGateway;

  public ReservaService(
    UsuarioGateway usuarioGateway,
    RestauranteGateway restauranteGateway,
    ReservaGateway reservaGateway
  ) {
    this.usuarioGateway = usuarioGateway;
    this.restauranteGateway = restauranteGateway;
    this.reservaGateway = reservaGateway;
  }

  public Reserva cadastrar(Reserva reserva) {
    return this.cadastrar(
      reserva.getIdUsuario(),
      reserva.getIdRestaurante(),
      reserva.getQuantidadePessoas(),
      reserva.getDataHoraInicio(),
      reserva.getDataHoraFim()
    );
  }

  public Reserva cadastrar(
    Long idUsuario,
    Long idRestaurante,
    Integer quantidadePessoas,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  ) {
    Reserva reserva = new Reserva(
      idUsuario,
      idRestaurante,
      quantidadePessoas,
      dataHoraInicio,
      dataHoraFim
    );

    validarConsistenciaDoHorarioDoAgendamento(dataHoraInicio, dataHoraFim);
    validarIntegridadeDosDadosNaBaseDeDados(idUsuario, idRestaurante);
    validarDisponibilidadeNoHorarioAgendadoDoUsuario(idUsuario, dataHoraInicio, dataHoraFim);
    validarDisponibilidadeNoHorarioAgendadoDoRestaurante(idRestaurante, dataHoraInicio, dataHoraFim, quantidadePessoas);

    reserva.setStatus(StatusReserva.AGENDADO);

    return reservaGateway.cadastrar(reserva);
  }

  private void validarConsistenciaDoHorarioDoAgendamento(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    if (!verificaSeDataHoraEstaNoFuturo(dataHoraInicio)) {
      throw new IllegalArgumentException("Data de início da reserva não pode estar no passado.");
    }

    if (!verificaSeDataHoraEstaNoFuturo(dataHoraFim)) {
      throw new IllegalArgumentException("Data de fim da reserva não pode estar no passado.");
    }

    if (!verificaSeAgendamentoEstaNoMesmoDia(dataHoraInicio, dataHoraFim)) {
      throw new IllegalArgumentException("Data de agendamento inicial deve acontecer no mesmo dia que o fim.");
    }

    if (!verificaSeHoraInicialAntecedeFinal(dataHoraInicio, dataHoraFim)) {
      throw new IllegalArgumentException("Horário inicial do agendamento deve preceder o final.");
    }

    if (!verificaSePermanenciaMinimaEstaValida(dataHoraInicio, dataHoraFim)) {
      throw new IllegalArgumentException("Permanência mínima é de " + PERMANCENCIA_MINIMA + "h.");
    }

    if (!verificaSePermanenciaMaximaEstaValida(dataHoraInicio, dataHoraFim)) {
      throw new IllegalArgumentException("Permanência máxima é de " + PERMANCENCIA_MAXIMA + "h.");
    }
  }

  private boolean verificaSeDataHoraEstaNoFuturo(LocalDateTime dataHora) {
    return dataHora.isAfter(LocalDateTime.now());
  }

  private boolean verificaSeAgendamentoEstaNoMesmoDia(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return dataHoraInicio.getDayOfMonth() == dataHoraFim.getDayOfMonth();
  }

  private boolean verificaSeHoraInicialAntecedeFinal(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return dataHoraInicio.getHour() < dataHoraFim.getHour();
  }

  private boolean verificaSePermanenciaMinimaEstaValida(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return calcularPermanencia(dataHoraInicio, dataHoraFim) >= PERMANCENCIA_MINIMA;
  }

  private boolean verificaSePermanenciaMaximaEstaValida(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return calcularPermanencia(dataHoraInicio, dataHoraFim) <= PERMANCENCIA_MAXIMA;
  }

  private Integer calcularPermanencia(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return Duration.between(dataHoraInicio, dataHoraFim).toHoursPart();
  }

  private void validarIntegridadeDosDadosNaBaseDeDados(Long idUsuario, Long idRestaurante) {
    if (!usuarioGateway.verificarSeExistePeloId(idUsuario)) {
      throw new IllegalArgumentException("Usuário não encontrado.");
    }

    if (!restauranteGateway.verificarSeExiste(idRestaurante)) {
      throw new IllegalArgumentException("Restaurante não encontrado.");
    }
  }

  /**
   * Considerar o restaurante não importa nesse caso, pois a aplicação foi desenvolvida para o uso individual, ou seja
   * o usuário deve reservar mesas para si (obrigadtóriamente) mas permite ter mais pessoas o acompanhando. Assim
   * levamos em conta que caso o cliente tenha uma reserva em qualquer restaurante do sistema, não deve estar
   * em dois lugares ao mesmo tempo. A menos que libere sua posição na reserva anterior.
   */
  private void validarDisponibilidadeNoHorarioAgendadoDoUsuario(
    Long idUsuario,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  ) {
    List<Reserva> reservas = reservaGateway.obterReservasDoUsuarioNoHorarioAgendado(
      idUsuario,
      dataHoraInicio,
      dataHoraFim
    );

    if(!reservas.isEmpty()){
      throw new IllegalArgumentException("Usuário já está ocupando a capacidade do restaurante neste horário.");
    }
  }

  private void validarDisponibilidadeNoHorarioAgendadoDoRestaurante(
    Long idRestaurante,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim,
    Integer quantidadePessoas
  ) {
    Boolean estaDisponivelParaReservar = verificarSeEstaDisponivelParaReservar(
      idRestaurante,
      dataHoraInicio,
      dataHoraFim,
      quantidadePessoas
    );

    if (!estaDisponivelParaReservar) {
      throw new IllegalArgumentException("Restaurante não suporta mais reservas no horário selecionado.");
    }
  }

  private boolean verificarSeEstaDisponivelParaReservar(
    Long idRestaurante,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim,
    Integer quantidadePessoas
  ) {
    List<Reserva> reservasEmAndamento = reservaGateway.obterTodasAsReservasOcupadasNoHorarioAgendado(
      idRestaurante,
      dataHoraInicio,
      dataHoraFim
    );

    Integer capacidadeOcupada = reservasEmAndamento.stream()
      .mapToInt(Reserva::getQuantidadePessoas)
      .sum();

    Restaurante restaurante = restauranteGateway.buscarPeloId(idRestaurante);
    Integer capacidadeSuportadoNoRestaurante = restaurante.getCapacidade();

    return (capacidadeOcupada + quantidadePessoas) <= capacidadeSuportadoNoRestaurante;
  }

  public Reserva atualizarStatusReserva(Long idReserva, String novoStatus) {
    validaSeNovoStatusExiste(novoStatus);
    validaSeReservaExiste(idReserva);

    return reservaGateway.alterarStatus(idReserva, Enum.valueOf(StatusReserva.class, novoStatus));
  }

  private void validaSeNovoStatusExiste(String novoStatus) {
    List<String> statusReservaValidos = EnumSet.allOf(StatusReserva.class).stream()
      .map(Enum::toString)
      .collect(Collectors.toList());

    if (!statusReservaValidos.contains(novoStatus)) {
      throw new IllegalArgumentException("O status " + novoStatus + " não é válido." +
        "Para alterar o status deve-se enviar um dos seguinte valores: " + statusReservaValidos + ".");
    }
  }

  private void validaSeReservaExiste(Long idReserva) {
    if (!reservaGateway.verificarSeExiste(idReserva)) {
      throw new IllegalArgumentException("Reserva não encontrada.");
    }
  }
}
