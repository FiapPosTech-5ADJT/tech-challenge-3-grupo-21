package br.com.fiap.restauranteapi.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AvaliacaoResponseDto(@Schema(hidden = true) Long id) {
}
