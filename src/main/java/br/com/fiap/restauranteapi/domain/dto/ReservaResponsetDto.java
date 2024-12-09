package br.com.fiap.restauranteapi.domain.dto;


import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ReservaResponsetDto(
        @Schema(hidden = true)
        Long id,
        @Enumerated(EnumType.STRING)
        StatusReserva status
) {
}
