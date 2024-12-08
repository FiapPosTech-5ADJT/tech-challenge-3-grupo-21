package br.com.fiap.restauranteapi.domain.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioFuncionamento {

    private List<DiasSemana> diasSemanaList;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    public HorarioFuncionamento(List<DiasSemana> diasSemanaList, LocalTime horarioAbertura, LocalTime horarioFechamento) {
        setDiasSemanaList(diasSemanaList);
        setHorarioAbertura(horarioAbertura);
        setHorarioFechamento(horarioFechamento);
    }

    public List<DiasSemana> getDiasSemanaList() {
        return diasSemanaList;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setDiasSemanaList(List<DiasSemana> diasSemanaList) {
        if (diasSemanaList == null || diasSemanaList.isEmpty()) {
            throw new IllegalArgumentException("Dias da semana devem ser informados.");
        }
        this.diasSemanaList = diasSemanaList;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        if (horarioAbertura == null) {
            throw new IllegalArgumentException("Horário de abertura deve ser informado.");
        }
        this.horarioAbertura = horarioAbertura;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        if (horarioFechamento == null) {
            throw new IllegalArgumentException("Horário de fechamento deve ser informado.");
        }
        this.horarioFechamento = horarioFechamento;
    }
}
