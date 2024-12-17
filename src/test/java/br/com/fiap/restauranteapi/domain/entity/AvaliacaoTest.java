package br.com.fiap.restauranteapi.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AvaliacaoTest {

    @Test
    void deveCriarAvaliacaoComNotaValida() {
        Avaliacao avaliacao = new Avaliacao(1L, (short) 4);
        assertThat(avaliacao).isNotNull();
        assertThat(avaliacao.getIdReserva()).isEqualTo(1L);
        assertThat(avaliacao.getNota()).isEqualTo((short) 4);
    }

    @Test
    void deveCriarAvaliacaoComNotaEComentarioValidos() {
        Avaliacao avaliacao = new Avaliacao(1L, (short) 4, "Bom serviço");
        assertThat(avaliacao).isNotNull();
        assertThat(avaliacao.getIdReserva()).isEqualTo(1L);
        assertThat(avaliacao.getNota()).isEqualTo((short) 4);
        assertThat(avaliacao.getComentario()).isEqualTo("Bom serviço");
    }

    @Test
    void naoDeveCriarAvaliacaoComIdReservaInvalido() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Avaliacao(null, (short) 4))
                .withMessage("Id de reserva inválido.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Avaliacao(0L, (short) 4))
                .withMessage("Id de reserva inválido.");
    }

    @Test
    void naoDeveCriarAvaliacaoComNotaInvalida() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Avaliacao(1L, (short) 0))
                .withMessage("O valor do campo 'nota' deve estar entre 1 e 5.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Avaliacao(1L, (short) 6))
                .withMessage("O valor do campo 'nota' deve estar entre 1 e 5.");
    }

    @Test
    void naoDeveCriarAvaliacaoComComentarioMuitoLongo() {
        String comentarioLongo = "a".repeat(251);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Avaliacao(1L, (short) 4, comentarioLongo))
                .withMessage("Quando informado, o comentário deve ter no máximo 250 caracteres.");
    }

  @Test
  void deveCriarAvaliacaoComIdNotaEComentarioValidos() {
    Avaliacao avaliacao = new Avaliacao(1L, 1L, (short) 4, "Bom serviço");
    assertThat(avaliacao).isNotNull();
    assertThat(avaliacao.getId()).isEqualTo(1L);
    assertThat(avaliacao.getIdReserva()).isEqualTo(1L);
    assertThat(avaliacao.getNota()).isEqualTo((short) 4);
    assertThat(avaliacao.getComentario()).isEqualTo("Bom serviço");
  }

  @Test
  void deveSetarEObterIdCorretamente() {
    Avaliacao avaliacao = new Avaliacao(1L, (short) 4);
    avaliacao.setId(2L);
    assertThat(avaliacao.getId()).isEqualTo(2L);
  }
}

