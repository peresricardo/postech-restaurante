package com.fiap.restaurante.model;

import java.util.List;
import java.util.UUID;
import com.fiap.restaurante.model.embedded.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_restaurantes")
@Data
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 80)
    private String razaoSocial;
    @Column(nullable = false, length = 60)
    private String nomeFantasia;
    @Column(nullable = false, length = 50)
    private String tipoCozinha;
    @Column(nullable = false)
    private Integer capacidade;
    @Embedded
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HorarioFuncionamento> horario;
}
