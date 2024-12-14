package br.com.fiap.restauranteapi.domain.entity;

import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Restaurante", "Lanchonete", 20);
    }

    @Test
    void devePermitirCriarRestauranteSemLocalizacaoSemHorario() {
        final Restaurante restauranteSemLocalizacaoSemHorario = new Restaurante("Restaurante", "Lanchonete", 20);
        assertThat(restaurante).isNotNull();
        assertThat(restaurante.getNome()).isEqualTo("Restaurante");
        assertThat(restaurante.getLocalizacao()).isNull();
        assertThat(restaurante.getHorarioFuncionamento()).isNull();
        assertThat(restaurante.getTipoRestaurante()).isEqualTo("Lanchonete");
        assertThat(restaurante.getCapacidade()).isEqualTo(20);
    }

    @Test
    void devePermitirCriarRestauranteSemDatas() {
        final Localizacao localizacao = new Localizacao("89041183",
                "Rua Teste",
                "100",
                "Lado do mercado",
                "Bairro teste",
                "São Paulo",
                "SP",
                "Brasil");
        final HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(
                List.of(DiasSemana.SEGUNDA, DiasSemana.TERCA, DiasSemana.QUARTA, DiasSemana.QUINTA, DiasSemana.SEXTA),
                LocalTime.of(8, 0),
                LocalTime.of(18, 0));

        final Restaurante restauranteSemDatas = new Restaurante("Restaurante do Zé", localizacao, horarioFuncionamento,"Lanchonete" , 10);
        assertThat(restauranteSemDatas).isNotNull();
        assertThat(restauranteSemDatas.getNome()).isEqualTo("Restaurante do Zé");
        assertThat(restauranteSemDatas.getLocalizacao()).isNotNull();
        assertThat(restauranteSemDatas.getLocalizacao().getCep()).isEqualTo("89041183");
        assertThat(restauranteSemDatas.getHorarioFuncionamento()).isNotNull();
        assertThat(restauranteSemDatas.getHorarioFuncionamento().getDiasSemanaList()).isEqualTo(List.of(DiasSemana.SEGUNDA, DiasSemana.TERCA, DiasSemana.QUARTA, DiasSemana.QUINTA, DiasSemana.SEXTA));
        assertThat(restauranteSemDatas.getTipoRestaurante()).isEqualTo("Lanchonete");
        assertThat(restauranteSemDatas.getCapacidade()).isEqualTo(10);
    }

    @Test
    void devePermitirSetarNome() {
        final String novoNome = "Restaurante Novo Nome";
        restaurante.setNome(novoNome);
        assertThat(restaurante.getNome()).isEqualTo(novoNome);
    }

    @Test
    void devePermitirSetarLocalizacao() {
        final Localizacao novaLocalizacao = new Localizacao("89041183", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");
        restaurante.setLocalizacao(novaLocalizacao);
        assertThat(restaurante.getLocalizacao()).isEqualTo(novaLocalizacao);
        assertThat(restaurante.getLocalizacao().getCep()).isEqualTo("89041183");
        assertThat(restaurante.getLocalizacao().getLogradouro()).isEqualTo("Rua Teste");
    }

    @Test
    void devePermitirSetarHorarioFuncionamento() {
        List<DiasSemana> listaDias = List.of(DiasSemana.SEGUNDA, DiasSemana.TERCA, DiasSemana.QUARTA, DiasSemana.QUINTA, DiasSemana.SEXTA);
        final HorarioFuncionamento novoFuncionamento = new HorarioFuncionamento(listaDias, LocalTime.of(8, 0), LocalTime.of(18, 0));
        restaurante.setHorarioFuncionamento(novoFuncionamento);
        assertThat(restaurante.getHorarioFuncionamento()).isEqualTo(novoFuncionamento);
        assertThat(restaurante.getHorarioFuncionamento().getDiasSemanaList()).isEqualTo(listaDias);
        assertThat(restaurante.getHorarioFuncionamento().getHorarioAbertura()).isEqualTo(LocalTime.of(8, 0));
    }

    @Test
    void naoDevePermitirSetarNomeNulo() {
        final String novoNome = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setNome(novoNome))
                .withMessage("Nome deve ser informado.");

    }

    @Test
    void naoDevePermitirSetarNomeVazio() {
        final String novoNome = "";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setNome(novoNome))
                .withMessage("Nome deve ser informado.");
    }

    @Test
    void naoDevePermitirSetarLocalizacaoNula() {
        final Localizacao novaLocalizacao = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setLocalizacao(novaLocalizacao))
                .withMessage("Localização deve ser informada.");
    }

    @Test
    void naoDevePermitirSetarHorarioFuncionamentoNulo() {
        final HorarioFuncionamento novoFuncionamento = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setHorarioFuncionamento(novoFuncionamento))
                .withMessage("Horário de funcionamento deve ser informado.");
    }

    @Test
    void naoDevePermitirSetarHorarioFuncionamentoSemDiasSemana() {
        final HorarioFuncionamento novoFuncionamento = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setHorarioFuncionamento(novoFuncionamento))
                .withMessage("Horário de funcionamento deve ser informado.");
    }

    @Test
    void naoDevePermitirSetarCapacidadeMenorQueMinimo() {
        final int novaCapacidade = 0;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restaurante.setCapacidade(novaCapacidade))
                .withMessage("Capacidade mínima é 1.");
    }


}
