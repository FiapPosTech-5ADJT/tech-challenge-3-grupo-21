package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

  private UsuarioGateway usuarioGateway;
  private RestauranteGateway restauranteGateway;
  private ReservaGateway reservaGateway;
  private ReservaService reservaService;

  @BeforeEach
  void setUp() {
    usuarioGateway = mock(UsuarioGateway.class);
    restauranteGateway = mock(RestauranteGateway.class);
    reservaGateway = mock(ReservaGateway.class);
    reservaService = new ReservaService(usuarioGateway, restauranteGateway, reservaGateway);
  }

  @Test
  void deveCadastrarReservaComDadosValidos() {
    Long idUsuario = 1L;
    Long idRestaurante = 1L;
    Integer quantidadePessoas = 4;
    LocalDateTime dataHoraInicio = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0);
    LocalDateTime dataHoraFim = dataHoraInicio.plusHours(2);

    when(usuarioGateway.verificarSeExistePeloId(idUsuario)).thenReturn(true);
    when(restauranteGateway.verificarSeExiste(idRestaurante)).thenReturn(true);
    when(reservaGateway.obterReservasDoUsuarioNoHorarioAgendado(idUsuario, dataHoraInicio, dataHoraFim)).thenReturn(Collections.emptyList());
    when(reservaGateway.obterTodasAsReservasOcupadasNoHorarioAgendado(idRestaurante, dataHoraInicio, dataHoraFim)).thenReturn(Collections.emptyList());
    when(restauranteGateway.buscarPeloId(idRestaurante)).thenReturn(new Restaurante(
      idRestaurante,
      "Restaurante Teste",
      new Localizacao("01000-000", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil"),
      new HorarioFuncionamento(
        Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA, DiasSemana.QUARTA, DiasSemana.QUINTA, DiasSemana.SEXTA, DiasSemana.SABADO, DiasSemana.DOMINGO),
        LocalTime.of(9, 0),
        LocalTime.of(18, 0)
      ),
      "Tipo",
      10,
      LocalDateTime.now(),
      LocalDateTime.now()
    ));
    when(reservaGateway.cadastrar(any(Reserva.class))).thenAnswer(invocation -> {
      Reserva reserva = invocation.getArgument(0);
      reserva.setStatus(StatusReserva.AGENDADO);
      return reserva;
    });

    Reserva reserva = reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicio, dataHoraFim);

    assertThat(reserva).isNotNull();
    assertThat(reserva.getStatus()).isEqualTo(StatusReserva.AGENDADO);
  }

  @Test
  void naoDeveCadastrarReservaComUsuarioInvalido() {
    Long idUsuario = 1L;
    Long idRestaurante = 1L;
    Integer quantidadePessoas = 4;
    LocalDateTime dataHoraInicio = LocalDateTime.now().plusDays(1);
    LocalDateTime dataHoraFim = dataHoraInicio.plusHours(2);

    when(usuarioGateway.verificarSeExistePeloId(idUsuario)).thenReturn(false);

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicio, dataHoraFim))
      .withMessage("Usuário não encontrado.");
  }

  @Test
  void deveAtualizarStatusReserva() {
    Long idReserva = 1L;
    String novoStatus = "AGENDADO";

    when(reservaGateway.verificarSeExiste(idReserva)).thenReturn(true);
    when(reservaGateway.alterarStatus(idReserva, StatusReserva.AGENDADO)).thenReturn(new Reserva(idReserva, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now(), StatusReserva.AGENDADO));

    Reserva reserva = reservaService.atualizarStatusReserva(idReserva, novoStatus);

    assertThat(reserva).isNotNull();
  }

  @Test
  void deveValidarConsistenciaDoHorarioDoAgendamento() {
    Long idUsuario = 1L;
    Long idRestaurante = 1L;
    Integer quantidadePessoas = 4;

    // Teste para data de início no passado
    LocalDateTime dataHoraInicioPassado = LocalDateTime.now().minusDays(1);
    LocalDateTime dataHoraFimPassado = dataHoraInicioPassado.plusHours(2);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioPassado, dataHoraFimPassado))
      .withMessage("Data de início da reserva não pode estar no passado.");

    // Teste para data de fim no passado
    LocalDateTime dataHoraInicioFuturo = LocalDateTime.now().plusDays(1);
    LocalDateTime dataHoraFimPassado2 = LocalDateTime.now().minusDays(1);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioFuturo, dataHoraFimPassado2))
      .withMessage("Data de fim da reserva não pode estar no passado.");

    // Teste para agendamento em dias diferentes
    LocalDateTime dataHoraInicioDia1 = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0);
    LocalDateTime dataHoraFimDia2 = dataHoraInicioDia1.plusDays(1).withHour(14).withMinute(0);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioDia1, dataHoraFimDia2))
      .withMessage("Data de agendamento inicial deve acontecer no mesmo dia que o fim.");

    // Teste para horário inicial não antecedendo o final
    LocalDateTime dataHoraInicioInvalido = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0);
    LocalDateTime dataHoraFimInvalido = dataHoraInicioInvalido.minusHours(2);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioInvalido, dataHoraFimInvalido))
      .withMessage("Horário inicial do agendamento deve preceder o final.");

    // Teste para permanência mínima inválida
    LocalDateTime dataHoraInicioMinima = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0);
    LocalDateTime dataHoraFimMinima = dataHoraInicioMinima.plusMinutes(30);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioMinima, dataHoraFimMinima))
      .withMessage("Horário inicial do agendamento deve preceder o final.");

    // Teste para permanência máxima inválida
    LocalDateTime dataHoraInicioMaxima = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0);
    LocalDateTime dataHoraFimMaxima = dataHoraInicioMaxima.plusHours(6);
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicioMaxima, dataHoraFimMaxima))
      .withMessage("Permanência máxima é de " + ReservaService.PERMANCENCIA_MAXIMA + "h.");
  }

}
