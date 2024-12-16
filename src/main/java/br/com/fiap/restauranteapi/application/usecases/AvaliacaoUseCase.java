package br.com.fiap.restauranteapi.application.usecases;

import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.domain.service.AvaliacaoService;

public class AvaliacaoUseCase {
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoUseCase(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    public Avaliacao cadastrar(Avaliacao avaliacao) {
        return avaliacaoService.cadastrarAvaliacao(avaliacao);
    }
}
