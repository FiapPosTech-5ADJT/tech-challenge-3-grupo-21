package br.com.fiap.restauranteapi.domain.dto;

public record LocalizacaoDto(String nome, String tipoCozinha, String endereco, String cidade, String estado, String cep) {
}
