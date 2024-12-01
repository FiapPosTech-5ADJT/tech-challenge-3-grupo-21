package br.com.fiap.restauranteapi.infraestructure.persistence;

import br.com.fiap.restauranteapi.domain.restaurante.entity.Restaurante;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);
        Restaurante restauranteSalvo = restauranteRepository.save(restaurante);
        assertThat(restauranteSalvo).isNotNull()
                .isEqualTo(restaurante);

        verify(restauranteRepository, times(1)).save(any(Restaurante.class));

    }

    @Test
    void devePermitirBuscarRestaurantePorId() {
        Restaurante restaurante = criarRestaurante();
        Long id = 1L;
        restaurante.setId(id);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));

        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(id);

        assertThat(restauranteEncontrado).isPresent();
        assertThat(restauranteEncontrado.get().getId()).isEqualTo(id);

        verify(restauranteRepository, times(1)).findById(id);
    }

    @Test
    void devePermitirBuscarTodosRestaurantes() {
        Restaurante restaurante1 = criarRestaurante();
        Restaurante restaurante2 = criarRestaurante();
        when(restauranteRepository.findAll()).thenReturn(List.of(restaurante1, restaurante2));

        assertThat(restauranteRepository.findAll()).isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(restaurante1, restaurante2);

        verify(restauranteRepository, times(1)).findAll();
    }

    @Test
    void devePermitirDeletarRestaurantePorId() {
        Long id = 1L;
        doNothing().when(restauranteRepository).deleteById(id);

        restauranteRepository.deleteById(id);

        verify(restauranteRepository, times(1)).deleteById(id);
    }

    Restaurante criarRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante do Zé");
        restaurante.setLocalizacao("Rua do Zé, 123");
        restaurante.setTipoRestaurante("11999999999");
        restaurante.setHorario("10:00 - 22:00");
        restaurante.setCapacidade(100);
        return restaurante;
    }

}
