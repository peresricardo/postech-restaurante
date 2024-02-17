package com.fiap.restaurante.domain;

import com.fiap.restaurante.domain.dto.ClienteDto;
import com.fiap.restaurante.domain.embedded.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_clientes")
@Data
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

    public Cliente(ClienteDto clienteDto) {
        this.nome = clienteDto.nome();
        this.email = clienteDto.email();
        this.fone = clienteDto.fone();
        this.endereco = clienteDto.enderecoDto();
    }
}
