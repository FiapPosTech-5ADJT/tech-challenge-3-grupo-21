package br.com.fiap.restauranteapi.domain.entity;

import java.time.LocalDateTime;

public class Restaurante {
    private Long id;
    private String nome;
    private Localizacao localizacao;
    private HorarioFuncionamento horarioFuncionamento;
    private String tipoRestaurante;
    private int capacidade;

    public Restaurante(Long id, String nome, Localizacao localizacao, HorarioFuncionamento horarioFuncionamento, String tipoRestaurante, int capacidade, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        setNome(nome);
        setLocalizacao(localizacao);
        setHorarioFuncionamento(horarioFuncionamento);
        setTipoRestaurante(tipoRestaurante);
        setCapacidade(capacidade);
    }

    public Restaurante(String nome, Localizacao localizacao, HorarioFuncionamento horarioFuncionamento, String tipoRestaurante, int capacidade) {
        this.id = id;
        setNome(nome);
        setLocalizacao(localizacao);
        setHorarioFuncionamento(horarioFuncionamento);
        setTipoRestaurante(tipoRestaurante);
        setCapacidade(capacidade);
    }

    public Restaurante(String nome, String tipoRestaurante, int capacidade) {
        this.id = id;
        setNome(nome);
        setTipoRestaurante(tipoRestaurante);
        setCapacidade(capacidade);
        this.localizacao = null;
        this.horarioFuncionamento = null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public HorarioFuncionamento getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public String getTipoRestaurante() {
        return tipoRestaurante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome deve ser informado.");
        }
        this.nome = nome;
    }

    public void setLocalizacao(Localizacao localizacao) {
        if (localizacao == null) {
            throw new IllegalArgumentException("Localização deve ser informada.");
        }
        this.localizacao = localizacao;
    }

    public void setHorarioFuncionamento(HorarioFuncionamento horarioFuncionamento) {
        if (horarioFuncionamento == null) {
            throw new IllegalArgumentException("Horário de funcionamento deve ser informado.");
        }
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public void setTipoRestaurante(String tipoRestaurante) {
        if (tipoRestaurante == null || tipoRestaurante.isBlank()) {
            throw new IllegalArgumentException("Tipo de restaurante deve ser informado.");
        }
        this.tipoRestaurante = tipoRestaurante;
    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 1) {
            throw new IllegalArgumentException("Capacidade mínima é 1.");
        }
        this.capacidade = capacidade;
    }
}