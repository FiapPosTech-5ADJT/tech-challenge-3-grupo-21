package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.domain.dto.LocalizacaoDto;
import br.com.fiap.restauranteapi.domain.dto.RestauranteRequestDto;
import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.enums.DiasSemana;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestauranteControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveRetornarStatus200AoCriarRestaurantes() {
        Localizacao localizacao = new Localizacao("89041183", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        RestauranteRequestDto restauranteRequestDto = new RestauranteRequestDto("Restaurante Teste",
                localizacao,
                horarioFuncionamento,
                "Italiana",
                10);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Restaurante Teste"))
                .body("localizacao.cep", equalTo("89041183"))
                .body("capacidade", equalTo(10));
    }

    @Test
    void deveRetornarStatus500QuandoNomeRestauranteForNulo() {
        Localizacao localizacao = new Localizacao("89041183", "111", "111", "111", "111", "111", "111", "111");
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        RestauranteRequestDto restauranteRequestDto = new RestauranteRequestDto(null, localizacao, horarioFuncionamento, "teste", 123);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(500);
    }

    @Test
    void deveRetornarStatus500QuandoCapacidadeForNegativa() {
        Localizacao localizacao = new Localizacao("89041183", "111", "111", "111", "111", "111", "111", "111");
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        RestauranteRequestDto restauranteRequestDto = new RestauranteRequestDto("teste", localizacao, horarioFuncionamento, "teste", -1);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(500);
    }

    @Test
    void deveRetornarStatus200ComListaVaziaQuandoNaoExistemRestaurantes() {
        LocalizacaoDto localizacaoDto = new LocalizacaoDto("111", "111", "111", "111", "111", "111");

        RestAssured.given()
                .contentType("application/json")
                .body(localizacaoDto)
                .when()
                .post("/restaurante/buscar")
                .then()
                .statusCode(200)
                .body("$", equalTo(Collections.emptyList()));
    }

    @Test
    void deveRetornarStatus200AoBuscarRestaurantes() {
        Localizacao localizacao = new Localizacao("89041183", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "São Paulo", "SP", "Brasil");
        List<DiasSemana> diasSemanaList = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura = LocalTime.of(8, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(diasSemanaList, horarioAbertura, horarioFechamento);
        RestauranteRequestDto restauranteRequestDto = new RestauranteRequestDto("Restaurante Teste",
                localizacao,
                horarioFuncionamento,
                "Italiana",
                10);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Restaurante Teste"))
                .body("localizacao.cep", equalTo("89041183"))
                .body("capacidade", equalTo(10));
    }

    @Test
    void deveRetornarStatus200QuandoBuscarRestaurantesPorLocalizacao() {
        // Primeiro, criar dois restaurantes no banco de dados
        Localizacao localizacao1 = new Localizacao("01000-000", "Rua Teste", "100", "Lado do mercado", "Bairro teste", "Blumenau", "SP", "Brasil");
        List<DiasSemana> diasSemanaList1 = Arrays.asList(DiasSemana.SEGUNDA, DiasSemana.TERCA);
        LocalTime horarioAbertura1 = LocalTime.of(8, 0);
        LocalTime horarioFechamento1 = LocalTime.of(18, 0);
        HorarioFuncionamento horarioFuncionamento1 = new HorarioFuncionamento(diasSemanaList1, horarioAbertura1, horarioFechamento1);
        RestauranteRequestDto restauranteRequestDto1 = new RestauranteRequestDto("Restaurante Teste 1", localizacao1, horarioFuncionamento1, "Italiana", 10);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto1)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(200);

        Localizacao localizacao2 = new Localizacao("01000-000", "Rua Teste", "200", "Em frente ao parque", "Bairro teste", "Blumenau", "SP", "Brasil");
        List<DiasSemana> diasSemanaList2 = Arrays.asList(DiasSemana.QUARTA, DiasSemana.QUINTA);
        LocalTime horarioAbertura2 = LocalTime.of(9, 0);
        LocalTime horarioFechamento2 = LocalTime.of(19, 0);
        HorarioFuncionamento horarioFuncionamento2 = new HorarioFuncionamento(diasSemanaList2, horarioAbertura2, horarioFechamento2);
        RestauranteRequestDto restauranteRequestDto2 = new RestauranteRequestDto("Restaurante Teste 2", localizacao2, horarioFuncionamento2, "Japonesa", 20);

        RestAssured.given()
                .contentType("application/json")
                .body(restauranteRequestDto2)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(200);

        // Agora, buscar os restaurantes pela localização
        LocalizacaoDto localizacaoDto = new LocalizacaoDto("", "", "", "Blumenau", "", "");

        RestAssured.given()
                .contentType("application/json")
                .body(localizacaoDto)
                .when()
                .post("/restaurante/buscar")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].nome", equalTo("Restaurante Teste 1"))
                .body("[1].nome", equalTo("Restaurante Teste 2"));
    }


}
