package br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacoes")
public class AvaliacaoJPAEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "reserva_id")
  private ReservaJPAEntity reservaEntity;

  @Column(nullable = false)
  private short nota;

  @Column(nullable = true)
  private String comentario;
}
