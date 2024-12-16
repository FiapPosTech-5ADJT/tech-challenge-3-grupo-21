package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository;

import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.AvaliacaoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoJPAEntity, Long> {
  Optional<AvaliacaoJPAEntity> findByReservaEntityId(Long idReserva);
}
