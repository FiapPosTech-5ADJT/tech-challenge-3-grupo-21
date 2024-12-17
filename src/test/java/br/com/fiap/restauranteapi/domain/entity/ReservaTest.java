package br.com.fiap.restauranteapi.domain.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ReservaTest {

    @Test
    void deveCriarReservaComDadosValidos() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        Reserva reserva = new Reserva(1L, 1L, 1L, 4, inicio, fim, StatusReserva.AGENDADO);
        assertThat(reserva).isNotNull();
        assertThat(reserva.getIdUsuario()).isEqualTo(1L);
        assertThat(reserva.getIdRestaurante()).isEqualTo(1L);
        assertThat(reserva.getQuantidadePessoas()).isEqualTo(4);
        assertThat(reserva.getDataHoraInicio()).isEqualTo(inicio);
        assertThat(reserva.getDataHoraFim()).isEqualTo(fim);
        assertThat(reserva.getStatus()).isEqualTo(StatusReserva.AGENDADO);
    }

    @Test
    void naoDeveCriarReservaComIdUsuarioInvalido() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(null,  null, 1L, 4, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Id de usuário inválido.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(0L,  null, 1L, 4, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Id de usuário inválido.");
    }

    @Test
    void naoDeveCriarReservaComIdRestauranteInvalido() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(1L, null, 4, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Id de restaurante inválido.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(1L, 0L, 4, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Id de restaurante inválido.");
    }

    @Test
    void naoDeveCriarReservaComQuantidadePessoasInvalida() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(1L, 1L, null, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Quantidade mínima de pessoas deve ser pelo menos 1.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Reserva(1L, 1L, 0, inicio, fim, StatusReserva.AGENDADO))
                .withMessage("Quantidade mínima de pessoas deve ser pelo menos 1.");
    }

    @Test
    void deveSetarEObterIdCorretamente() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        Reserva reserva = new Reserva(1L, 1L, 4, inicio, fim, StatusReserva.AGENDADO);
        reserva.setId(2L);
        assertThat(reserva.getId()).isEqualTo(2L);
    }

  @Test
  void deveCriarReservaComDadosValidosSemStatus() {
    LocalDateTime inicio = LocalDateTime.now();
    LocalDateTime fim = inicio.plusHours(2);
    Reserva reserva = new Reserva(1L, 1L, 4, inicio, fim);
    assertThat(reserva).isNotNull();
    assertThat(reserva.getIdUsuario()).isEqualTo(1L);
    assertThat(reserva.getIdRestaurante()).isEqualTo(1L);
    assertThat(reserva.getQuantidadePessoas()).isEqualTo(4);
    assertThat(reserva.getDataHoraInicio()).isEqualTo(inicio);
    assertThat(reserva.getDataHoraFim()).isEqualTo(fim);
  }

  @Test
  void deveSetarEObterStatusCorretamente() {
    Reserva reserva = new Reserva(1L, 1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    reserva.setStatus(StatusReserva.AGENDADO);
    assertThat(reserva.getStatus()).isEqualTo(StatusReserva.AGENDADO);
  }

  @Test
  void deveSetarEObterDataHoraInicioCorretamente() {
    Reserva reserva = new Reserva(1L, 1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    LocalDateTime novaDataHoraInicio = LocalDateTime.now().plusDays(1);
    reserva.setDataHoraInicio(novaDataHoraInicio);
    assertThat(reserva.getDataHoraInicio()).isEqualTo(novaDataHoraInicio);
  }

  @Test
  void deveSetarEObterDataHoraFimCorretamente() {
    Reserva reserva = new Reserva(1L, 1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    LocalDateTime novaDataHoraFim = LocalDateTime.now().plusDays(1).plusHours(2);
    reserva.setDataHoraFim(novaDataHoraFim);
    assertThat(reserva.getDataHoraFim()).isEqualTo(novaDataHoraFim);
  }
}
