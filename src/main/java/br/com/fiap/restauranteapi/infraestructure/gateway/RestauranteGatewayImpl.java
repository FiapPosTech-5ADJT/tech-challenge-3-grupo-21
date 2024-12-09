package br.com.fiap.restauranteapi.infraestructure.gateway;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDTO;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Restaurante create(Restaurante restaurante) {
        RestauranteJPAEntity restauranteJpa = RestauranteConverter.toJpa(restaurante);
        RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(restauranteJpa);
        return RestauranteConverter.toDomain(restauranteSalvo);
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return restauranteRepository.findById(id)
                .map(RestauranteConverter::toDomain);
    }

    @Override
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteConverter::toDomain)
                .toList();
    }

    @Override
    public List<Restaurante> findByLocation(LocalizacaoDTO localizacao) {
        return restauranteRepository.findByLocation(
                localizacao.nome(),
                localizacao.tipoCozinha(),
                localizacao.endereco(),
                localizacao.cidade(),
                localizacao.estado(),
                localizacao.cep()
        ).stream()
                .map(RestauranteConverter::toDomain)
                .toList();
    }

}
