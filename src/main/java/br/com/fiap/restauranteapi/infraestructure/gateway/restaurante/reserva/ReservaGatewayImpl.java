package br.com.fiap.restauranteapi.infraestructure.gateway.restaurante.reserva;


import br.com.fiap.restauranteapi.domain.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.reserva.entity.Reserva;
import br.com.fiap.restauranteapi.domain.reserva.gateway.ReservaGateway;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ReservaGatewayImpl implements ReservaGateway {

  @Override
  public List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(Long idRestaurante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return List.of();
  }

  @Override
  public Reserva cadastrar(Reserva reserva) {
    return null;
  }

  @Override
  public List<Reserva> obterReservasDoUsuarioNoHorarioAgendado(Long idUsuario, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
    return List.of();
  }

  @Override
  public Reserva buscarPeloId(Long id) {
    return null;
  }

  @Override
  public List<Reserva> buscarDetalhesPeloIdDoRestaurante(Long idRestaurante) {
    return List.of();
  }

  @Override
  public List<Reserva> buscarDetalhesPeloIdDoRestaurante(Long idRestaurante, int pagina, int numeroItensPorPagina, String ordenarPor, boolean ordemCrescente) {
    return List.of();
  }

  @Override
  public Boolean verificarSeExiste(Long id) {
    return null;
  }

  @Override
  public Reserva alterarStatus(Long idReserva, StatusReserva novoStatus) {
    return null;
  }
}
