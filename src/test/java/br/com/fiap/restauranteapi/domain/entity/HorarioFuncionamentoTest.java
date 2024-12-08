package br.com.fiap.restauranteapi.domain.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HorarioFuncionamentoTest {
    @Test
    void devePermitirCriarHorarioFuncionamento() {
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);

        assertThat(horarioFuncionamento).isNotNull();
        assertThat(horarioFuncionamento.getDiasSemanaList()).isEqualTo(diasSemanaList);
        assertThat(horarioFuncionamento.getHorarioAbertura()).isEqualTo(horarioAbertura);
        assertThat(horarioFuncionamento.getHorarioFechamento()).isEqualTo(horarioFechamento);
    }

    @Test
    void naoDevePermitirCriarHorarioFuncionamentoSemDiasSemana() {
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new HorarioFuncionamento(Collections.emptyList(), horarioAbertura, horarioFechamento))
                .withMessage("Dias da semana devem ser informados.");
    }

    @Test
    void naoDevePermitirCriarHorarioFuncionamentoComHorarioAberturaNulo() {
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioFechamento = LocalTime.of(18, 0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new HorarioFuncionamento(diasSemanaList, null, horarioFechamento))
                .withMessage("Horário de abertura deve ser informado.");
    }

    @Test
    void naoDevePermitirCriarHorarioFuncionamentoComHorarioFechamentoNulo() {
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new HorarioFuncionamento(diasSemanaList, horarioAbertura, null))
                .withMessage("Horário de fechamento deve ser informado.");
    }
}
