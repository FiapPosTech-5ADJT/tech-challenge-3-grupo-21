package br.com.fiap.restauranteapi.domain.dto;


import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record ReservaDto(
        @Schema(hidden = true)
        Long id,
        Long usuario_id,
        Long restaurante_id,
        int quantidadePessoas,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,

        @Enumerated(EnumType.STRING)
        StatusReserva status
) {
}
