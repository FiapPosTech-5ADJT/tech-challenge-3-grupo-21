package br.com.fiap.restauranteapi.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoDto(
        @Schema(hidden = true)
        Long id,
        String nome,
        String comentario,

        @NotNull
        @Min(1)
        @Max(5)
        Integer nota,
        Long restauranteId
) {
}
