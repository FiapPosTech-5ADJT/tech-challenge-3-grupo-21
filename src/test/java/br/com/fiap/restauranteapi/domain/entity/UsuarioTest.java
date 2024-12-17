package br.com.fiap.restauranteapi.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UsuarioTest {

    @Test
    void deveCriarUsuarioComDadosValidos() {
        LocalDate dataCadastro = LocalDate.now();
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", dataCadastro);
        assertThat(usuario).isNotNull();
        assertThat(usuario.getCpf()).isEqualTo("12345678900");
        assertThat(usuario.getNome()).isEqualTo("João Silva");
        assertThat(usuario.getDdd()).isEqualTo("11");
        assertThat(usuario.getTelefone()).isEqualTo("987654321");
        assertThat(usuario.getEmail()).isEqualTo("joao.silva@example.com");
        assertThat(usuario.getDataCadastro()).isEqualTo(dataCadastro);
    }

    @Test
    void naoDeveCriarUsuarioComCpfInvalido() {
        LocalDate dataCadastro = LocalDate.now();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario(null, "João Silva", "11", "987654321", "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'cpf' não pode ser nulo.");
    }

    @Test
    void naoDeveCriarUsuarioComNomeInvalido() {
        LocalDate dataCadastro = LocalDate.now();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", null, "11", "987654321", "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'nome' não pode estar nulo/vazio.");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", "", "11", "987654321", "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'nome' não pode estar nulo/vazio.");
    }

    @Test
    void naoDeveCriarUsuarioComNomeMaiorQueTamanhoMaximo() {
        LocalDate dataCadastro = LocalDate.now();
        String nomeGrande = "A".repeat(101);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", nomeGrande, "11", "987654321", "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'nome' deve ter no máximo 100 caracteres.");
    }

    @Test
    void naoDeveCriarUsuarioComDddInvalido() {
        LocalDate dataCadastro = LocalDate.now();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", "João Silva", null, "987654321", "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'ddd' não pode ser nulo.");
    }

    @Test
    void naoDeveCriarUsuarioComTelefoneInvalido() {
        LocalDate dataCadastro = LocalDate.now();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", "João Silva", "11", null, "joao.silva@example.com", dataCadastro))
                .withMessage("O campo 'telefone' não pode ser nulo.");
    }

    @Test
    void naoDeveCriarUsuarioComEmailInvalido() {
        LocalDate dataCadastro = LocalDate.now();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Usuario("12345678900", "João Silva", "11", "987654321", null, dataCadastro))
                .withMessage("O campo 'email' não pode ser nulo.");
    }

    @Test
    void deveSetarEObterCpfCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setCpf("09876543210");
        assertThat(usuario.getCpf()).isEqualTo("09876543210");
    }

    @Test
    void deveSetarEObterNomeCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setNome("Maria Silva");
        assertThat(usuario.getNome()).isEqualTo("Maria Silva");
    }

    @Test
    void deveSetarEObterDddCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setDdd("21");
        assertThat(usuario.getDdd()).isEqualTo("21");
    }

    @Test
    void deveSetarEObterTelefoneCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setTelefone("123456789");
        assertThat(usuario.getTelefone()).isEqualTo("123456789");
    }

    @Test
    void deveSetarEObterEmailCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setEmail("maria.silva@example.com");
        assertThat(usuario.getEmail()).isEqualTo("maria.silva@example.com");
    }

    @Test
    void deveSetarEObterDataCadastroCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        LocalDate novaDataCadastro = LocalDate.now().plusDays(1);
        usuario.setDataCadastro(novaDataCadastro);
        assertThat(usuario.getDataCadastro()).isEqualTo(novaDataCadastro);
    }

    @Test
    void deveSetarEObterIdCorretamente() {
        Usuario usuario = new Usuario("12345678900", "João Silva", "11", "987654321", "joao.silva@example.com", LocalDate.now());
        usuario.setId(2L);
        assertThat(usuario.getId()).isEqualTo(2L);
    }
}
