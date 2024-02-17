package com.fiap.restaurante.controller;

import com.fiap.restaurante.domain.Restaurante;
import com.fiap.restaurante.domain.dto.RestauranteDto;
import com.fiap.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/restaurantes", produces = {"application/json"})
public class RestauranteController {

    private final RestauranteService restauranteService;

    @Autowired
    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> cadastrarRestaurante(@RequestBody RestauranteDto restauranteDto) {
        Restaurante novoRestaurante = restauranteService.cadastrar(restauranteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listar();
        return ResponseEntity.ok().body(restaurantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable UUID id, @RequestBody RestauranteDto restauranteDto) {
        Restaurante restaurante = restauranteService.atualizarRestaurante(id, restauranteDto);
        return ResponseEntity.ok().body(restaurante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable UUID id) {
        restauranteService.deletarRestaurante(id);
        return ResponseEntity.noContent().build();
    }


}
