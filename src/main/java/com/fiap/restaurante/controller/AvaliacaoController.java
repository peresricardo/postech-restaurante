package com.fiap.restaurante.controller;


import com.fiap.restaurante.domain.Avaliacao;
import com.fiap.restaurante.domain.dto.AvaliacaoDto;
import com.fiap.restaurante.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/avaliacao", produces = {"applications/json"})
@Tag(name = "Cadastro de avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Efetua a inclusão de uma nova avaliacao", method = "POST")
    public ResponseEntity<Avaliacao> cadastrar(@RequestBody AvaliacaoDto avaliacaoDto){
        Avaliacao novaAvaliacao = avaliacaoService.cadastrar(avaliacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Efetua a listagem de todas as avaliacoes", method = "GET")
    public ResponseEntity<List<Avaliacao>> listar() {
        List<Avaliacao> avaliacao = avaliacaoService.listar();
        return ResponseEntity.ok().body(avaliacao);
    }
}
