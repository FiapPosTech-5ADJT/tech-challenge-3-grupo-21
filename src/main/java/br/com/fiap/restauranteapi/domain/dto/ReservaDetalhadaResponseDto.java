package br.com.fiap.restauranteapi.domain.dto;

import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record ReservaDetalhadaResponseDto(
        Long id,

        UsuarioReservaDetalhadaDto cliente,

        int quantidadePessoas,

        LocalDateTime dataHoraInicio,

        LocalDateTime dataHoraFim,

        @Enumerated(EnumType.STRING)
        StatusReserva status
) {
}
