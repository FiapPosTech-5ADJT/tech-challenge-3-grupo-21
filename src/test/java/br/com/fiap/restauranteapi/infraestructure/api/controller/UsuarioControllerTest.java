package br.com.fiap.restauranteapi.infraestructure.api.controller;

import br.com.fiap.restauranteapi.domain.dto.UsuarioRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldCreateUser() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(newUser())
                .when()
                .post("/usuario")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    static UsuarioRequestDto newUser() {
        return new UsuarioRequestDto("98190011022", "José da Silva", "19", "998765434", "josedasilva@example.com", LocalDate.now());
    }

    @Test
    void shouldReturnExceptionWhenCreatedWithoutCpf() {
        var user = new UsuarioRequestDto("", "José da Silva", "19", "998765434", "josedasilva@example.com", LocalDate.now());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when()
                .post("/usuario")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}