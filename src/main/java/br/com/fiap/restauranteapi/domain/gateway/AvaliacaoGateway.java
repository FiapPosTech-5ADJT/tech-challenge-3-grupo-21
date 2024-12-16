package br.com.fiap.restauranteapi.domain.gateway;

import br.com.fiap.restauranteapi.domain.entity.Avaliacao;

public interface  AvaliacaoGateway {
  Avaliacao cadastrar(Avaliacao avaliacao);

  Avaliacao buscarPeloIdDaReserva(Long id);
}
