package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.DTO.LocalizacaoDTO;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;

import java.util.List;
import java.util.Optional;

public class RestauranteService {

    private final RestauranteGateway restauranteGateway;

    public RestauranteService(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante createRestaurante(Restaurante restaurante) {
        if(restaurante == null) {
            throw new IllegalArgumentException("Restaurante não pode ser nulo");
        }
        return restauranteGateway.create(restaurante);
    }

    public Optional<Restaurante> getRestauranteById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return restauranteGateway.findById(id);
    }

    public List<Restaurante> getAllRestaurantes() {
        return restauranteGateway.findAll();
    }

    public List<Restaurante> searchRestaurantesByLocation(LocalizacaoDTO localizacaoDTO) {
        if(localizacaoDTO == null) {
            throw new IllegalArgumentException("Localização não pode ser nula ou vazia");
        }
        return restauranteGateway.findByLocation(localizacaoDTO);
    }
}
