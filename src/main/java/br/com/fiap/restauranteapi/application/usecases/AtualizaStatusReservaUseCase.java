package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.service.ReservaService;

public class AtualizaStatusReservaUseCase {
    private final ReservaService reservaService;

    public AtualizaStatusReservaUseCase(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public Reserva atualizar(Long idReserva, String novoStatus) {
        return reservaService.atualizarStatusReserva(idReserva, novoStatus);
    }
}
