package br.com.fiap.restauranteapi.infraestructure.persistence.entity.reserva;

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
  public class ReservaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private UsuarioEntity usuarioEntity;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  private RestauranteEntity restauranteEntity;

  @NotNull
  private Integer quantidadePessoas;

  @NotNull
  private LocalDateTime dataHoraInicio;

  @NotNull
  private LocalDateTime dataHoraFim;

  @Enumerated(EnumType.STRING)
  private StatusReserva status;

  public ReservaEntity(Long id) {
    this.id = id;
  }
}
