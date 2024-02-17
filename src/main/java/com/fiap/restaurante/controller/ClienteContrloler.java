package com.fiap.restaurante.controller;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import com.fiap.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes", produces = {"application/json"})
public class ClienteContrloler {

    private final ClienteService clienteService;

    @Autowired
    public ClienteContrloler(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDto clienteDto){
        Cliente novoCliente = clienteService.cadastrarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }
}
