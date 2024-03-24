package com.fiap.restaurante.controller;

import com.fiap.restaurante.utils.ClienteHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.startsWith;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ClienteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }

    @Nested
    class RegistrarCliente {

        @Test
        void devePermitirRegistrarCliente() {
            var clienteRequest = ClienteHelper.gerarRegistroRequest();

            given()
                        .filter(new AllureRestAssured())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(clienteRequest)
                    .when()
                         .post("/clientes")
                    .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("$", hasKey("id"))
                        .body("$", hasKey("nome"))
                        .body("$", hasKey("email"))
                        .body("$", hasKey("fone"))
                        .body("nome", equalTo(clienteRequest.getNome()))
                        .body("email", equalTo(clienteRequest.getEmail()))
                        .body("fone", equalTo(clienteRequest.getFone()));
        }
        @Test
        void deveGerarExcecao_QuandoRegistrarCliente_NomeEmBranco() {
            var mensagemRequest = ClienteHelper.gerarRegistroRequest();
            mensagemRequest.setNome("");

            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(mensagemRequest)
                    .when()
                    .post("/clientes")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

        }

        @Test
        void devePermitirRegistrarCliente_ValidarSchema() {
            var mensagemRequest = ClienteHelper.gerarRegistroRequest();

            given()
                    .header("Content-Type", "application/json")
                    .body(mensagemRequest)
                    .when()
                    .post("/clientes")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .header("Content-Type", notNullValue())
                    .header("Content-Type", startsWith("application/json"))
                    .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
        }

    }

    @Nested
    class BuscarCliente {

        @Test
        @Sql(scripts = {"/clean.sql",
                "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        void devePermitirBuscarCliente() {
            var id = "5f789b39-4295-42c1-a65b-cfca5b987db2";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/clientes/{id}", id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
        }

        @Test
        void deveGerarExcecao_QuandoBuscarCliente_IdNaoExistente() {
            var id = "5f789b39-4295-42c1-a65b-cfca5b987db3";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/clientes/{id}", id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body(equalTo("mensagem não encontrada"));
        }

        @Test
        void deveGerarExcecao_QuandoBuscarMensagem_IdInvalido() {
            var id = "2";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/clientes/{id}", id)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

}
