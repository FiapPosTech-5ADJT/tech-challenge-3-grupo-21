package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.service.ReservaService;

import java.time.LocalDateTime;

public class CadastroReservaUseCase {
    private final ReservaService reservaService;

    public CadastroReservaUseCase(ReservaService reservaService) {
        this.reservaService = reservaService;
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

    public Reserva cadastrar(Long idUsuario, Long idRestaurante, Integer quantidadePessoas, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return reservaService.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicio, dataHoraFim);
    }
}
