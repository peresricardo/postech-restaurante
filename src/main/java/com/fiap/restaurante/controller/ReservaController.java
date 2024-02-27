package com.fiap.restaurante.controller;

import com.fiap.restaurante.domain.Reserva;
import com.fiap.restaurante.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/reservas", produces = {"application/json"})
@Tag(name = "Cadastro de Reseservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    @Operation(summary = "Cria uma reserva de uma mesa em um Restaurante", method = "POST")
    public ResponseEntity<Reserva> cadastrarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.cadastrar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    @GetMapping
    @Operation(summary = "Efetua a listagem de todas as reservas", method = "GET")
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.listar();
        return ResponseEntity.ok().body(reservas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Efetua a alteração de uma reserva", method = "PUT")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable UUID id, @RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.atualizarReserva(id, reserva);
        return ResponseEntity.ok().body(novaReserva);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Efetua a exclusão de uma reserva", method = "DELETE")
    public ResponseEntity<Void> deletarReserva(@PathVariable UUID id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();
    }

}
