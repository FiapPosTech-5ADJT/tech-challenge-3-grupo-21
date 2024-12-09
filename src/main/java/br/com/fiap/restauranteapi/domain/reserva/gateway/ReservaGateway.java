package br.com.fiap.restauranteapi.domain.reserva.gateway;


import br.com.fiap.restauranteapi.domain.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.reserva.entity.Reserva;
import br.com.fiap.restauranteapi.domain.reserva.entity.ReservaDetalhada;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaGateway {
  List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(
    Long idRestaurante,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  );

  Reserva cadastrar(Reserva reserva);

  List<Reserva> obterReservasDoUsuarioNoHorarioAgendado(
    Long idUsuario,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim
  );

  Reserva buscarPeloId(Long id);

  List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(Long idRestaurante);

  List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(
    Long idRestaurante,
    int pagina,
    int numeroItensPorPagina,
    String ordenarPor,
    boolean ordemCrescente
  );

  Boolean verificarSeExiste(Long id);

  Reserva alterarStatus(Long idReserva, StatusReserva novoStatus);
}
