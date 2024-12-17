package br.com.fiap.restauranteapi.domain.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservaDetalhadaTest {

    @Test
    void deveCriarReservaDetalhadaComDadosValidos() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(2);
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, inicio, fim, StatusReserva.AGENDADO);
        assertThat(reservaDetalhada).isNotNull();
        assertThat(reservaDetalhada.getId()).isEqualTo(1L);
        assertThat(reservaDetalhada.getQuantidadePessoas()).isEqualTo(4);
        assertThat(reservaDetalhada.getDataHoraInicio()).isEqualTo(inicio);
        assertThat(reservaDetalhada.getDataHoraFim()).isEqualTo(fim);
        assertThat(reservaDetalhada.getStatus()).isEqualTo(StatusReserva.AGENDADO);
    }

    @Test
    void deveSetarEObterStatusCorretamente() {
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2), StatusReserva.AGENDADO);
        reservaDetalhada.setStatus(StatusReserva.CANCELADA);
        assertThat(reservaDetalhada.getStatus()).isEqualTo(StatusReserva.CANCELADA);
    }

    @Test
    void deveSetarEObterDataHoraInicioCorretamente() {
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2), StatusReserva.AGENDADO);
        LocalDateTime novaDataHoraInicio = LocalDateTime.now().plusDays(1);
        reservaDetalhada.setDataHoraInicio(novaDataHoraInicio);
        assertThat(reservaDetalhada.getDataHoraInicio()).isEqualTo(novaDataHoraInicio);
    }

    @Test
    void deveSetarEObterDataHoraFimCorretamente() {
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2), StatusReserva.AGENDADO);
        LocalDateTime novaDataHoraFim = LocalDateTime.now().plusDays(1).plusHours(2);
        reservaDetalhada.setDataHoraFim(novaDataHoraFim);
        assertThat(reservaDetalhada.getDataHoraFim()).isEqualTo(novaDataHoraFim);
    }

    @Test
    void deveSetarEObterQuantidadePessoasCorretamente() {
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2), StatusReserva.AGENDADO);
        reservaDetalhada.setQuantidadePessoas(5);
        assertThat(reservaDetalhada.getQuantidadePessoas()).isEqualTo(5);
    }

    @Test
    void deveSetarEObterIdCorretamente() {
        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(1L, 4, LocalDateTime.now(), LocalDateTime.now().plusHours(2), StatusReserva.AGENDADO);
        reservaDetalhada.setId(2L);
        assertThat(reservaDetalhada.getId()).isEqualTo(2L);
    }
}
