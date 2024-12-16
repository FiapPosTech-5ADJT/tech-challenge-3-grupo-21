package br.com.fiap.restauranteapi.domain.dto;

public record UsuarioReservaDetalhadaDto(
        Long id,
        String nome,
        String telefone,
        String email
) {

}
