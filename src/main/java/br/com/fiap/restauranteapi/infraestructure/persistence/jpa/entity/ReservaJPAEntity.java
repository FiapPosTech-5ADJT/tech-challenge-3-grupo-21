package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
  public class ReservaJPAEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  private RestauranteJPAEntity restauranteEntity;

  @NotNull
  private Integer quantidadePessoas;

  @NotNull
  private LocalDateTime dataHoraInicio;

  @NotNull
  private LocalDateTime dataHoraFim;

  @Enumerated(EnumType.STRING)
  private StatusReserva status;

  public ReservaJPAEntity(Long id) {
    this.id = id;
  }
}
