package com.fiap.restaurante.steps;

import com.fiap.restaurante.domain.Cliente;
import com.fiap.restaurante.domain.dto.ClienteDto;
import com.fiap.restaurante.utils.ClienteHelper;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ClienteStep {
    private Response response;
    private ClienteDto clienteDto;
    private String ENDPOINT_CLIENTES = "http://localhost:8080/clientes";



    @Quando("submeter um novo cliente")
    public Cliente submeterNovoCliente() {
        var clienteRequest = ClienteHelper.gerarRegistro();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when().post(ENDPOINT_CLIENTES);
        return response.then().extract().as(Cliente.class);
    }

    @Entao("o cliente e registrado com sucesso")
    public void clienteRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

}
