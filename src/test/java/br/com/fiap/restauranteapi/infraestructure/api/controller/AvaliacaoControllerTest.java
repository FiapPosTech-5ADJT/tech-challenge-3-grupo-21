package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.domain.dto.AvaliacaoRequestDto;
import br.com.fiap.restauranteapi.domain.dto.AvaliacaoResponseDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvaliacaoControllerTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void deveCriarAvaliacaoComSucesso() {
    AvaliacaoRequestDto avaliacaoRequestDto = new AvaliacaoRequestDto(2L, (short) 5, "Excelente serviço!");
    given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(avaliacaoRequestDto)
      .when()
      .post("/avaliacao")
      .then()
      .statusCode(HttpStatus.CREATED.value());
  }
}
