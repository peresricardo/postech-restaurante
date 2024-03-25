package com.fiap.restaurante.controller;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import com.fiap.restaurante.utils.RestauranteHelper;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Nested
    class RegistrarRestaurante {

        @Test
        void devePermitirRegistrarRestaurante() {
            var restauranteRequest = RestauranteHelper.gerarRegistro();

            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restauranteRequest)
                    .when()
                    .post("/restaurantes")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("$", hasKey("id"))
                    .body("$", hasKey("nomeFantasia"))
                    .body("$", hasKey("tipoCozinha"))
                    .body("$", hasKey("capacidade"))
                    .body("nomeFantasia", equalTo(restauranteRequest.getNomeFantasia()))
                    .body("tipoCozinha", equalTo(restauranteRequest.getTipoCozinha()))
                    .body("capacidade", equalTo(restauranteRequest.getCapacidade()));
        }

        @Test
        void deveGerarExcecao_QuandoRegistrarRestaurante_NomeFantasiaEmBranco() {
            var restauranteRequest = RestauranteHelper.gerarRegistro();
            restauranteRequest.setNomeFantasia("");

            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restauranteRequest)
                    .when()
                    .post("/restaurantes")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

        }

        @Test
        void devePermitirRegistrarRestaurante_ValidarSchema() {
            var restauranteRequest = RestauranteHelper.gerarRegistro();

            given()
                    .header("Content-Type", "application/json")
                    .body(restauranteRequest)
                    .when()
                    .post("/restaurantes")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .header("Content-Type", notNullValue())
                    .header("Content-Type", startsWith("application/json"))
                    .body(matchesJsonSchemaInClasspath("./schemas/RestauranteResponseSchema.json"));
        }

    }

    @Nested
    class BuscarRestaurante {

        @Test
        @Sql(scripts = {"/clean.sql",
                "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void devePermitirBuscarRestaurante() {
            var id = "5f789b39-4295-42c1-a65b-cfca5b987db2";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurantes")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", hasItem(id));
        }

        @Test
        void deveGerarExcecao_QuandoBuscarRestaurante_IdNaoExistente() {
            var id = "5f789b39-4295-42c1-a65b-cfca5b987db3";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurantes/{id}", id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body(equalTo("Restaurante não encontrado"));
        }

        @Test
        void deveGerarExcecao_QuandoBuscarRestaurante_IdInvalido() {
            var id = "2";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/restaurantes/{id}", id)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}
