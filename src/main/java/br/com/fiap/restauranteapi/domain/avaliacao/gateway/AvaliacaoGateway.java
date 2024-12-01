package br.com.fiap.restauranteapi.domain.avaliacao.gateway;

import br.com.fiap.restauranteapi.domain.avaliacao.entity.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoGateway {

    Avaliacao create(Avaliacao avaliacao);

    Optional<Avaliacao> findById(Long id);

    List<Avaliacao> findAll();

    Avaliacao update(Avaliacao avaliacao);

    void deleteById(Long id);
}
