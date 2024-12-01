package br.com.fiap.restauranteapi.domain.restaurante.gateway;

import br.com.fiap.restauranteapi.domain.restaurante.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante create(Restaurante restaurante);

    Optional<Restaurante> findById(Long id);

    List<Restaurante> findAll();

    Restaurante update(Restaurante restaurante);

    void deleteById(Long id);
}
