package br.com.fiap.restauranteapi.infraestructure.gateway;


import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.domain.gateway.AvaliacaoGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.AvaliacaoEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.AvaliacaoJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.ReservaJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.AvaliacaoRepository;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.ReservaRepository;

import java.util.Optional;

public class AvaliacaoGatewayImpl implements AvaliacaoGateway {
  private final ReservaRepository reservaRepository;
  private final AvaliacaoRepository avaliacaoRepository;

  private final AvaliacaoEntityConverter avaliacaoEntityConverter;

  public AvaliacaoGatewayImpl(
    ReservaRepository reservaRepository,
    AvaliacaoRepository avaliacaoRepository,
    AvaliacaoEntityConverter avaliacaoEntityConverter
  ) {
    this.reservaRepository = reservaRepository;
    this.avaliacaoRepository = avaliacaoRepository;
    this.avaliacaoEntityConverter = avaliacaoEntityConverter;
  }

  @Override
  public Avaliacao cadastrar(Avaliacao avaliacao) {
    AvaliacaoJPAEntity avaliacaoEntity = avaliacaoEntityConverter.toEntity(avaliacao);

    ReservaJPAEntity reservaEntity = reservaRepository.getReferenceById(avaliacao.getIdReserva());
    avaliacaoEntity.setReservaEntity(reservaEntity);

    AvaliacaoJPAEntity avaliacaoCadastrada = avaliacaoRepository.save(avaliacaoEntity);

    return avaliacaoEntityConverter.toDomainObj(avaliacaoCadastrada);
  }

  @Override
  public Avaliacao buscarPeloIdDaReserva(Long idReserva) {
    Optional<AvaliacaoJPAEntity> avaliacaoEntity = avaliacaoRepository.findByReservaEntityId(idReserva);

    if (avaliacaoEntity.isPresent()) {
      return avaliacaoEntityConverter.toDomainObj(avaliacaoEntity.get());
    }

    return null;
  }
}
