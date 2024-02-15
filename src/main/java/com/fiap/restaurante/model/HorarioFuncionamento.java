package com.fiap.restaurante.model;

import java.time.LocalTime;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;


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
