package br.com.fiap.restauranteapi.infraestructure.persistence;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class RestauranteRepositoryIntegratedTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void devePermitirCriarTabela() {
        var count = restauranteRepository.count();
        assertThat(count).isNotNegative();
    }

    @Test
    void devePermitirRegistrarRestaurante() {
        final Restaurante restaurante = criarRestaurante();
        final RestauranteJPAEntity restauranteJpa = toJpa(restaurante);

        final RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(restauranteJpa);

        assertThat(restauranteSalvo).isNotNull()
                .isInstanceOf(RestauranteJPAEntity.class);

        assertThat(restauranteSalvo.getLogradouro()).isSameAs(restaurante.getLocalizacao().getLogradouro());

    }

    @Test
    void devePermitirBuscarRestaurante() {
        final Restaurante restaurante = criarRestaurante();

        final RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(toJpa(restaurante));

        var restauranteRespostaOptional = restauranteRepository.findById(restauranteSalvo.getId());

        restauranteRespostaOptional.ifPresent(restauranteResposta -> {
            assertThat(restauranteResposta.getId()).isEqualTo(restauranteSalvo.getId());
        });
    }

    @Test
    void devePermitirBuscarTodosRestaurantes() {
        final Restaurante restaurante = criarRestaurante();
        final RestauranteJPAEntity restauranteJpa = toJpa(restaurante);

        final RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(restauranteJpa);

        var restaurantes = restauranteRepository.findAll();

        assertThat(restaurantes).isNotEmpty()
                .hasSizeGreaterThan(3)
                .contains(restauranteJpa);
    }

    private Restaurante criarRestaurante() {
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

        return new Restaurante("Restaurante do Zé", localizacao, horarioFuncionamento, "Lanchonete", 10);
    }

    private RestauranteJPAEntity toJpa(Restaurante restaurante) {
        return RestauranteConverter.toJpa(restaurante);
    }

    private Restaurante fromJpa(RestauranteJPAEntity restauranteJpa) {
        return RestauranteConverter.toDomain(restauranteJpa);
    }
}
