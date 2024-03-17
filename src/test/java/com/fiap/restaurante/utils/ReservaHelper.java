package com.fiap.restaurante.utils;

import com.fiap.restaurante.domain.Reserva;
import com.fiap.restaurante.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservaHelper {

    public static Reserva gerarReserva() {
        return Reserva.builder()
                    .idMesa(UUID.fromString("1f2b6507-0443-40c5-b5ac-7e89cbdfee19"))
                    .idRestaurante(UUID.randomUUID())
                    .horario(LocalDateTime.now()).build();
    }

    public static Reserva registrarReserva(Reserva reserva, ReservaRepository repository) {
        return repository.save(reserva);
    }
}
