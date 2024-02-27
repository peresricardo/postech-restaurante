package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaService {

    Reserva cadastrar(Reserva restauranteDto);

    List<Reserva> listar();

    Reserva atualizarReserva(UUID id, Reserva reserva);

    void deletarReserva(UUID id);
}
