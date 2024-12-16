package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.gateway.AvaliacaoGateway;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;

import java.util.List;

public class AvaliacaoService {
    private final ReservaGateway reservaGateway;
    private final AvaliacaoGateway avaliacaoGateway;

    public AvaliacaoService(ReservaGateway reservaGateway, AvaliacaoGateway avaliacaoGateway) {
        this.reservaGateway = reservaGateway;
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) {
        validarReservaDaAvaliacao(avaliacao);
        return this.avaliacaoGateway.cadastrar(avaliacao);
    }

    private void validarReservaDaAvaliacao(Avaliacao avaliacao) {
        Reserva reservaParaAvaliar = reservaGateway.buscarPeloId(avaliacao.getIdReserva());
        validarReserva(reservaParaAvaliar);
        validarStatusDaReserva(reservaParaAvaliar);
        validarSeReservaJaFoiAvaliada(reservaParaAvaliar);
    }

    private void validarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Avaliação não pode ser realizada pois a reserva não foi encontrada.");
        }
    }

    private void validarStatusDaReserva(Reserva reserva) {
        List<StatusReserva> statusAvaliaveis = List.of(StatusReserva.CONCLUIDA, StatusReserva.CANCELADA);
        if (!statusAvaliaveis.contains(reserva.getStatus())) {
            throw new IllegalArgumentException("Reserva não se encontra em um status disponível para avaliação. " +
                    "Para realizar a avaliação a reserva deve estar em um desses status: " + statusAvaliaveis + ".");
        }
    }

    private void validarSeReservaJaFoiAvaliada(Reserva reserva) {
        Avaliacao avaliacao = avaliacaoGateway.buscarPeloIdDaReserva(reserva.getId());
        if (avaliacao != null) {
            throw new IllegalArgumentException("A reserva já foi avaliada.");
        }
    }
}
