package br.com.fiap.restauranteapi.infraestructure.gateway.restaurante;

import br.com.fiap.restauranteapi.domain.restaurante.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.restaurante.gateway.RestauranteGateway;

import java.util.List;
import java.util.Optional;

public class RestauranteGatewayImpl implements RestauranteGateway {

    @Override
    public Restaurante create(Restaurante restaurante) {
        return null;
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Restaurante> findAll() {
        return List.of();
    }

    @Override
    public Restaurante update(Restaurante restaurante) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
