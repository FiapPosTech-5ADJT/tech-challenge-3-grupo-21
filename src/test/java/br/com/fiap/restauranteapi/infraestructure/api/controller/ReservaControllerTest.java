package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.domain.dto.AtualizaStatusReservaRequestDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaRequestDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaResponsetDto;
import br.com.fiap.restauranteapi.domain.entity.enums.StatusReserva;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservaControllerTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void deveCriarReservaComSucesso() {
    ReservaRequestDto reservaRequestDto = new ReservaRequestDto(1L, 1L, 4, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(4));
    ReservaResponsetDto reservaResponsetDto = new ReservaResponsetDto(1L, StatusReserva.AGENDADO);

    given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(reservaRequestDto)
      .when()
      .post("/reserva")
      .then()
      .statusCode(HttpStatus.CREATED.value())
      .body("id", equalTo(3))
      .body("status", equalTo(reservaResponsetDto.status().name()));
  }

  @Test
  void deveAtualizarStatusReservaComSucesso() {
    AtualizaStatusReservaRequestDto atualizaStatusReservaRequestDto = new AtualizaStatusReservaRequestDto(StatusReserva.CONCLUIDA.name());

    given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(atualizaStatusReservaRequestDto)
      .when()
      .patch("/reserva/1/status")
      .then()
      .statusCode(HttpStatus.NO_CONTENT.value());
  }
}
