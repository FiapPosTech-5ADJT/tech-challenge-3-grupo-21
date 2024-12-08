package br.com.fiap.restauranteapi.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Avaliacao {
    private Long id;
    private String comentario;
    private LocalDateTime dataAvaliacao;
    private Double nota;
    private Long idRestauranteFk;
}
