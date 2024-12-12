package br.com.fiap.restauranteapi.domain.dto;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;

public record RestauranteRequestDto(String nome,
                                    Localizacao localizacao,
                                    HorarioFuncionamento horarioFuncionamento,
                                    String tipoRestaurante,
                                    int capacidade) {
}
