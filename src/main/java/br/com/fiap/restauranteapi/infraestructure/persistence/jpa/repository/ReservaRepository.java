package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository;


import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.ReservaJPAEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaJPAEntity, Long> {
  @Query("SELECT r FROM ReservaJPAEntity r WHERE " +
    "r.restauranteEntity.id = :idRestaurante AND " + // Somente reservas do restaurante desejado
    "(r.dataHoraInicio >= :inicio AND r.dataHoraFim <= :fim) AND " + // Intervalo desejado
    "(r.status = 'ANDAMENTO' OR r.status = 'AGENDADO')" // Apenas status que impossibilitariam novas reservas
  )
  List<ReservaJPAEntity> findAllOccupiedReservationBetweenDates(
    @Param("idRestaurante") Long idRestaurante,
    @Param("inicio") LocalDateTime dataHoraInicio,
    @Param("fim") LocalDateTime dataHoraFim
  );

  @Query("SELECT r FROM ReservaJPAEntity r WHERE " +
    "r.usuarioEntity.id = :idUsuario AND " + // Somente reservas do usuário em específico
    "(r.dataHoraInicio >= :inicio AND r.dataHoraFim <= :fim) AND " + // Intervalo desejado
    "(r.status = 'ANDAMENTO' OR r.status = 'AGENDADO')" // Apenas status que impossibilitariam novas reservas
  )
  List<ReservaJPAEntity> findAllByIdUsuarioBetweenDates(
    @Param("idUsuario") Long idUsuario,
    @Param("inicio") LocalDateTime dataHoraInicio,
    @Param("fim") LocalDateTime dataHoraFim
  );

  Optional<List<ReservaJPAEntity>> findAllByRestauranteEntityId(Long idRestaurante);

  Optional<List<ReservaJPAEntity>> findAllByRestauranteEntityId(Long idRestaurante, Pageable pageable);
}
