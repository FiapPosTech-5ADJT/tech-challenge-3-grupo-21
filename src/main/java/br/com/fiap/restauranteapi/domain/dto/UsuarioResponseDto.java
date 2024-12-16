package br.com.fiap.restauranteapi.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UsuarioResponseDto(
        @Schema(hidden = true)
        Long id,

        @Schema(hidden = true)
        LocalDate dataCadastro
) {

}
