package br.com.fiap.restauranteapi.domain.entity;

public class Avaliacao {
  public final short NOTA_MAXIMA = 5;
  public final short NOTA_MINIMA = 1;
  public final Integer TAMANHO_MAXIMO_COMENTARIO = 250;

  private Long id;
  private Long idReserva;
  private short nota;
  private String comentario;

  public Avaliacao(Long idReserva, short nota) {
    this.setIdReserva(idReserva);
    this.setNota(nota);
  }

  public Avaliacao(Long idReserva, short nota, String comentario) {
    this.setIdReserva(idReserva);
    this.setNota(nota);
    this.setComentario(comentario);
  }

  public Avaliacao(Long id, Long idReserva, short nota, String comentario) {
    this.id = id;
    this.setIdReserva(idReserva);
    this.setNota(nota);
    this.setComentario(comentario);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdReserva() {
    return idReserva;
  }

  public void setIdReserva(Long idReserva) {
    if (idReserva == null || idReserva <= 0) {
      throw new IllegalArgumentException("Id de reserva inválido.");
    }

    this.idReserva = idReserva;
  }

  public short getNota() {
    return nota;
  }

  public void setNota(short nota) {
    if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
      throw new IllegalArgumentException("O valor do campo 'nota' deve estar entre " +
        NOTA_MINIMA + " e " + NOTA_MAXIMA + "."
      );
    }
    this.nota = nota;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    if (comentario != null && comentario.length() > TAMANHO_MAXIMO_COMENTARIO) {
      throw new IllegalArgumentException("Quando informado, o comentário deve ter no máximo " +
        TAMANHO_MAXIMO_COMENTARIO + " caracteres."
      );
    }

    this.comentario = comentario;
  }
}
