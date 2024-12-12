package br.com.fiap.restauranteapi.infraestructure.gateway;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityConverter restauranteEntityConverter;

    @Override
    public Restaurante create(Restaurante restaurante) {
        RestauranteJPAEntity restauranteJpa = restauranteEntityConverter.toEntity(restaurante);
        RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(restauranteJpa);
        return restauranteEntityConverter.toDomainObj(restauranteSalvo);
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return restauranteRepository.findById(id)
                .map(restauranteEntityConverter::toDomainObj);
    }

    @Override
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll().stream()
                .map(restauranteEntityConverter::toDomainObj)
                .toList();
    }

    @Override
    public List<Restaurante> findByLocation(LocalizacaoDto localizacao) {
        return restauranteRepository.findByLocation(
                localizacao.nome(),
                localizacao.tipoCozinha(),
                localizacao.endereco(),
                localizacao.cidade(),
                localizacao.estado(),
                localizacao.cep()
        ).stream()
                .map(restauranteEntityConverter::toDomainObj)
                .toList();
    }

}
