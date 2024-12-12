package br.com.fiap.restauranteapi.infraestructure.persistence;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestauranteRepositoryTest {

    @Mock
    RestauranteRepository restauranteRepository;

    AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void devePermitirCriarRestaurante() {
        Restaurante restaurante = criarRestaurante();
        RestauranteJPAEntity restauranteJpa = toJpa(restaurante);
        when(restauranteRepository.save(any(RestauranteJPAEntity.class))).thenReturn(restauranteJpa);
        RestauranteJPAEntity restauranteSalvo = restauranteRepository.save(restauranteJpa);
        assertThat(restauranteSalvo).isNotNull()
                .isInstanceOf(RestauranteJPAEntity.class);

        assertThat(restauranteSalvo.getId()).isSameAs(restaurante.getId());

        verify(restauranteRepository, times(1)).save(any(RestauranteJPAEntity.class));
    }

    @Test
    void devePermitirBuscarRestaurantePorId() {
        final Restaurante restaurante = criarRestaurante();
        RestauranteJPAEntity restauranteJpa = toJpa(restaurante);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteJpa));

        Optional<RestauranteJPAEntity> restauranteEncontrado = restauranteRepository.findById(1L);

        assertThat(restauranteEncontrado).isPresent();
        assertThat(restauranteEncontrado.get().getId()).isEqualTo(restauranteJpa.getId());

        verify(restauranteRepository, times(1)).findById(1L);
    }

    @Test
    void devePermitirBuscarTodosRestaurantes() {
        RestauranteJPAEntity restauranteJpa1 = toJpa(criarRestaurante());
        RestauranteJPAEntity restauranteJpa2 = toJpa(criarRestaurante());
        when(restauranteRepository.findAll()).thenReturn(List.of(restauranteJpa1, restauranteJpa2));

        List<RestauranteJPAEntity> restaurantes = restauranteRepository.findAll();

        assertThat(restaurantes).isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(restauranteJpa1, restauranteJpa2);

        verify(restauranteRepository, times(1)).findAll();
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

        return new Restaurante("Restaurante do Zé", localizacao, horarioFuncionamento,"Lanchonete" , 10);
    }

    private RestauranteJPAEntity toJpa(Restaurante restaurante) {
        return RestauranteEntityConverter.toEntity(restaurante);
    }

}
