package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.gateway.AvaliacaoGateway;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class AvaliacaoServiceTest {

    private ReservaGateway reservaGateway;
    private AvaliacaoGateway avaliacaoGateway;
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        reservaGateway = mock(ReservaGateway.class);
        avaliacaoGateway = mock(AvaliacaoGateway.class);
        avaliacaoService = new AvaliacaoService(reservaGateway, avaliacaoGateway);
    }

  @Test
  void deveCadastrarAvaliacaoComSucesso() {
    Avaliacao avaliacao = new Avaliacao(1L, (short) 5);

    Reserva reserva = new Reserva(1L, 1L, 4, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusHours(1));
    reserva.setStatus(StatusReserva.CONCLUIDA);

    when(reservaGateway.buscarPeloId(1L)).thenReturn(reserva);
    when(avaliacaoGateway.buscarPeloIdDaReserva(1L)).thenReturn(null);
    when(avaliacaoGateway.cadastrar(avaliacao)).thenReturn(avaliacao);

    Avaliacao avaliacaoCadastrada = avaliacaoService.cadastrarAvaliacao(avaliacao);

    assertThat(avaliacaoCadastrada).isNotNull();
    verify(avaliacaoGateway, times(1)).cadastrar(avaliacao);
  }

  @Test
  void naoDeveCadastrarAvaliacaoQuandoReservaNaoForEncontrada() {
    Avaliacao avaliacao = new Avaliacao(1L, (short) 5);

    when(reservaGateway.buscarPeloId(1L)).thenReturn(null);

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> avaliacaoService.cadastrarAvaliacao(avaliacao))
      .withMessage("Avaliação não pode ser realizada pois a reserva não foi encontrada.");
  }

  @Test
  void naoDeveCadastrarAvaliacaoQuandoReservaNaoEstiverEmStatusAvaliavel() {
    Avaliacao avaliacao = new Avaliacao(1L, (short) 5);

    Reserva reserva = new Reserva(1L, 1L, 4, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusHours(2));
    reserva.setStatus(StatusReserva.AGENDADO);

    when(reservaGateway.buscarPeloId(1L)).thenReturn(reserva);

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> avaliacaoService.cadastrarAvaliacao(avaliacao))
      .withMessage("Reserva não se encontra em um status disponível para avaliação. Para realizar a avaliação a reserva deve estar em um desses status: [CONCLUIDA, CANCELADA].");
  }
}
