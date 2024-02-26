package com.fiap.restaurante.domain;

import com.fiap.restaurante.domain.dto.AvaliacaoDto;
import com.fiap.restaurante.domain.embedded.Reserva;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Entity
@Table(name = "tb_avaliacao")
@Data
@NoArgsConstructor
@Jacksonized


public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID idReserva;

    @Column
    private String nota;

    @Column
    private String comentario;

    @Embedded
    private Reserva reserva;

    public Avaliacao(AvaliacaoDto avaliacaoDto) {
        this.nota = avaliacaoDto.nota();
        this.comentario = avaliacaoDto.comentario();
        this.reserva = new Reserva();
        this.reserva.setId(AvaliacaoDto.reserva().id());
    }


}
