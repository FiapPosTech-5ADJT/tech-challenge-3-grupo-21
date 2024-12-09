package br.com.fiap.restauranteapi.application.usecases;


import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import br.com.fiap.restauranteapi.domain.gateway.ReservaGateway;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class AtualizaStatusReservaUseCase {
    private final ReservaGateway reservaGateway;

    public AtualizaStatusReservaUseCase(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    public Reserva atualizar(Long idReserva, String novoStatus) {
        validaSeNovoStatusExiste(novoStatus);
        validaSeReservaExiste(idReserva);

        return reservaGateway.alterarStatus(idReserva, Enum.valueOf(StatusReserva.class, novoStatus));
    }

    private void validaSeNovoStatusExiste(String novoStatus) {
        List<String> statusReservaValidos = EnumSet.allOf(StatusReserva.class).stream()
                .map(s -> s.toString())
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
