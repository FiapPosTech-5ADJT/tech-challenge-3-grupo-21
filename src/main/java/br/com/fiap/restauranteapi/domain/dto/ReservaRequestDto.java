package br.com.fiap.restauranteapi.domain.dto;

import java.time.LocalDateTime;

public record ReservaRequestDto(
        Long idUsuario,
        Long idRestaurante,
        int quantidadePessoas,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim
) {
}
