package br.com.fiap.restauranteapi.domain.service;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import br.com.fiap.restauranteapi.domain.gateway.RestauranteGateway;
import br.com.fiap.restauranteapi.infraestructure.persistence.converter.db.RestauranteEntityConverter;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

class RestauranteServiceTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteEntityConverter restauranteEntityConverter;

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private RestauranteService restauranteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final Localizacao localizacao = new Localizacao("89041183", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");

    @Test
    void devePermitirCriarRestaurante() {
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Italiana", 10);
        restaurante.setLocalizacao(localizacao);
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        restaurante.setHorarioFuncionamento(horarioFuncionamento);
        RestauranteJPAEntity restauranteJPAEntity = restauranteEntityConverter.toEntity(restaurante);

        when(restauranteRepository.save(restauranteJPAEntity)).thenReturn(restauranteJPAEntity);
        when(restauranteGateway.create(restaurante)).thenReturn(restaurante);

        Restaurante createdRestaurante = restauranteService.createRestaurante(restaurante);

        assertThat(createdRestaurante).isNotNull();
        assertThat(createdRestaurante.getNome()).isEqualTo("Restaurante Teste");
    }

    @Test
    void devePermitirBuscarRestaurantesPorLocalizacao() {
        LocalizacaoDto localizacaoDto = new LocalizacaoDto("Rua Teste", "Italiana", "Rua Teste", "São Paulo", "SP", "01000-000");
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Italiana", 10);
        restaurante.setLocalizacao(localizacao);
        RestauranteJPAEntity restauranteJPAEntity = restauranteEntityConverter.toEntity(restaurante);
        when(restauranteRepository.findByLocation("Restaurante Teste", "Italiana", "Rua Teste", "São Paulo", "SP", "01000-000"))
                .thenReturn(Collections.singletonList(restauranteJPAEntity));
        when(restauranteGateway.findByLocation(localizacaoDto)).thenReturn(Collections.singletonList(restaurante));
        List<Restaurante> restaurantes = restauranteService.searchRestaurantesByLocation(localizacaoDto);

        assertThat(restaurantes).isNotEmpty();
        assertThat(restaurantes.get(0).getNome()).isEqualTo("Restaurante Teste");
    }

    @Test
    void naoDevePermitirCriarRestauranteNulo() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restauranteService.createRestaurante(null))
                .withMessage("Restaurante não pode ser nulo");
    }

    @Test
    void naoDevePermitirCriarRestauranteComNumeroLocalizacaoNegativo() {
        Localizacao localizacaoInvalida = new Localizacao("89041183", "Rua Teste", "-100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Italiana", 10);
        restaurante.setLocalizacao(localizacaoInvalida);
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        restaurante.setHorarioFuncionamento(horarioFuncionamento);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restauranteService.createRestaurante(restaurante))
                .withMessage("O número da localização, quando informado, deve ser positivo.");
    }

    @Test
    void naoDevePermitirCriarRestauranteComHorarioAberturaAposHorarioFechamento() {
        Restaurante restaurante = new Restaurante("Restaurante Teste", "Italiana", 10);
        restaurante.setLocalizacao(localizacao);
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(18, 0);
        LocalTime horarioFechamento = LocalTime.of(8, 0);

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        restaurante.setHorarioFuncionamento(horarioFuncionamento);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restauranteService.createRestaurante(restaurante))
                .withMessage("Horário de abertura deve ser antes do horário de encerramento.");
    }

    @Test
    void naoDevePermitirBuscarRestaurantesPorLocalizacaoNula() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> restauranteService.searchRestaurantesByLocation(null))
                .withMessage("Localização não pode ser nula ou vazia");
    }
}