package br.com.fiap.restauranteapi.domain.restaurante.service;

import br.com.fiap.restauranteapi.domain.restaurante.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.restaurante.gateway.RestauranteGateway;

import java.util.List;
import java.util.Optional;

public class RestauranteService {

    private final RestauranteGateway restauranteGateway;

    public RestauranteService(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante createRestaurante(Restaurante restaurante) {
        return restauranteGateway.create(restaurante);
    }

    public Optional<Restaurante> getRestauranteById(Long id) {
        return restauranteGateway.findById(id);
    }

    public List<Restaurante> getAllRestaurantes() {
        return restauranteGateway.findAll();
    }

    public Restaurante updateRestaurante(Restaurante restaurante) {
        return restauranteGateway.update(restaurante);
    }

    public void deleteRestauranteById(Long id) {
        restauranteGateway.deleteById(id);
    }
}
