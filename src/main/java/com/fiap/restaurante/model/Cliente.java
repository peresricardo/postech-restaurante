package com.fiap.restaurante.model;

import com.fiap.restaurante.model.embedded.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String fone;

    @Embedded
    private Endereco endereco;


}
