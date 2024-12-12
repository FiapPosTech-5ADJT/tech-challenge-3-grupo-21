package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.domain.service.RestauranteService;

import java.util.List;

public class BuscarRestauranteUseCase {

    private final RestauranteService restauranteService;

    public BuscarRestauranteUseCase(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    public List<Restaurante> buscarRestaurantePorLocalizacao(LocalizacaoDto localizacaoDTO) {
        return this.restauranteService.searchRestaurantesByLocation(localizacaoDTO);
    }

}
