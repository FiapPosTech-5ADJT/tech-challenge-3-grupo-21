package br.com.fiap.restauranteapi.domain.entity;


import java.time.LocalDate;

public class Usuario {
  public final int TAMANHO_MAXIMO_NOME = 100;

  private Long id;
  private String cpf;
  private String nome;
  private String ddd;
  private String telefone;
  private String email;
  private LocalDate dataCadastro;

  public Usuario(String cpf, String nome, String ddd, String telefone, String email, LocalDate dataCadastro) {
    this.setCpf(cpf);
    this.setNome(nome);
    this.setDdd(ddd);
    this.setTelefone(telefone);
    this.setEmail(email);
    this.dataCadastro = dataCadastro;
  }

  public Usuario(Long id, String cpf, String nome, String ddd, String telefone, String email, LocalDate dataCadastro) {
    this.id = id;
    this.setCpf(cpf);
    this.setNome(nome);
    this.setDdd(ddd);
    this.setTelefone(telefone);
    this.setEmail(email);
    this.dataCadastro = dataCadastro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    if (cpf == null) {
      throw new IllegalArgumentException("O campo 'cpf' não pode ser nulo.");
    }
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("O campo 'nome' não pode estar nulo/vazio.");
    }

    if (nome.length() >TAMANHO_MAXIMO_NOME) {
      throw new IllegalArgumentException("O campo 'nome' deve ter no máximo " +
        TAMANHO_MAXIMO_NOME + " caracteres.");
    }

    this.nome = nome;
  }

  public String getDdd() {
    return ddd;
  }

  public void setDdd(String ddd) {
    if (ddd == null) {
      throw new IllegalArgumentException("O campo 'ddd' não pode ser nulo.");
    }
    this.ddd = ddd;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    if (telefone == null) {
      throw new IllegalArgumentException("O campo 'telefone' não pode ser nulo.");
    }
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email == null) {
      throw new IllegalArgumentException("O campo 'email' não pode ser nulo.");
    }
    this.email = email;
  }

  public LocalDate getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(LocalDate dataCadastro) {
    this.dataCadastro = dataCadastro;
  }
}
