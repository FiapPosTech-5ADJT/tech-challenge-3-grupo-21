package br.com.fiap.restauranteapi.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class LocalizacaoTest {

    private Localizacao localizacao;

    @BeforeEach
    void setUp() {
        localizacao = new Localizacao("89041183", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");
    }

    @Test
    void devePermitirCriarLocalizacao() {
        assertThat(localizacao).isNotNull();
        assertThat(localizacao.getCep()).isEqualTo("89041183");
        assertThat(localizacao.getLogradouro()).isEqualTo("Rua Teste");
        assertThat(localizacao.getNumero()).isEqualTo("100");
        assertThat(localizacao.getComplemento()).isEqualTo("Lado do mercado");
        assertThat(localizacao.getBairro()).isEqualTo("Bairro teste");
        assertThat(localizacao.getCidade()).isEqualTo("São Paulo");
        assertThat(localizacao.getEstado()).isEqualTo("SP");
        assertThat(localizacao.getPais()).isEqualTo("Brasil");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComEnderecoNulo() {
        final String NovoLogradouro = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setLogradouro(NovoLogradouro))
                .withMessage("Logradouro não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComCidadeNula() {
        final String novaCidade = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setCidade(novaCidade))
                .withMessage("Cidade não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComEstadoNulo() {
        final String novoEstado = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setEstado(novoEstado))
                .withMessage("Estado não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComPaisNulo() {
        final String novoPais = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setPais(novoPais))
                .withMessage("País não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComNumeroNulo() {
        final String novoNumero = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setNumero(novoNumero))
                .withMessage("Número não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComComplementoNulo() {
        final String novoComplemento = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setComplemento(novoComplemento))
                .withMessage("Complemento não pode estar em branco");

    }

    @Test
    void deveRetornarExceptionAoSetarLocalizacaoComBairroNulo() {
        final String novoBairro = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setBairro(novoBairro))
                .withMessage("Bairro não pode estar em branco");
    }

    @Test
    void deveRetornarExceptionAoSetarCepInvalido() {
        final String novoCep = "89041-1832";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setCep(novoCep))
                .withMessage("Formato de CEP inválido");
    }

    @Test
    void deveRetornarExceptionAoSetarCepNulo() {
        final String novoCep = null;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> localizacao.setCep(novoCep))
                .withMessage("Formato de CEP inválido");
    }

    @Test
    void devePermitirSetarCepSemHifen() {
        final String novoCep = "89041183";
        localizacao.setCep(novoCep);
        assertThat(localizacao.getCep()).isEqualTo(novoCep);
    }

    @Test
    void devePermitirSetarCepComHifen() {
        final String novoCep = "89041-183";
        localizacao.setCep(novoCep);
        assertThat(localizacao.getCep()).isEqualTo(novoCep);
    }
}
