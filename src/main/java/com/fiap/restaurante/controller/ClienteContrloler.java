package com.fiap.restaurante.controller;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes", produces = {"application/json"})
public class ClienteContrloler {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteDto clienteDto){
        return clienteService.cadastrarCliente(clienteDto);
    }
}
