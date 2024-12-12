package br.com.fiap.restauranteapi.domain.gateway;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante create(Restaurante restaurante);

    Optional<Restaurante> findById(Long id);

    List<Restaurante> findAll();

    List<Restaurante> findByLocation(LocalizacaoDto localizacaoDTO);
}
