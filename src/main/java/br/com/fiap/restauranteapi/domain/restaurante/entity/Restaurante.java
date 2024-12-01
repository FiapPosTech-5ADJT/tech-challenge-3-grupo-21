package br.com.fiap.restauranteapi.domain.restaurante.entity;

import lombok.Data;

@Data
public class Restaurante {
    private Long id;
    private String nome;
    private String localizacao;
    private String tipoRestaurante;
    private String horario;
    private int capacidade;
}
