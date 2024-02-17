package com.fiap.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;


@Entity
@Table(name = "tb_horarios")
@Data
@NoArgsConstructor
public class HorarioFuncionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 15)
    private String diaSemana;
    @Column(nullable = false)
    private LocalTime horaAbertura;
    @Column(nullable = false)
    private LocalTime horaFechamento;
    @ManyToOne
    private Restaurante restaurante;
}
