package br.com.fiap.restauranteapi.domain.entity;


import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;

import java.time.LocalDateTime;

public class ReservaDetalhada {
    private Long id;
    private int quantidadePessoas;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusReserva status;

    public ReservaDetalhada(
            Long id,
            int quantidadePessoas,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            StatusReserva status
    ) {
        this.id = id;
        this.quantidadePessoas = quantidadePessoas;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
