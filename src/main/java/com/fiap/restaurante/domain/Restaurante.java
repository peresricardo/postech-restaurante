package com.fiap.restaurante.domain;

import com.fiap.restaurante.domain.dto.RestauranteDto;
import com.fiap.restaurante.domain.embedded.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


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
    @OneToMany
    private List<Mesa> mesas;

    public Restaurante(RestauranteDto restauranteDto) {
        this.razaoSocial = restauranteDto.razaoSocial();
        this.nomeFantasia = restauranteDto.nomeFantasia();
        this.tipoCozinha = restauranteDto.tipoCozinha();
        this.capacidade = restauranteDto.capacidade();
        this.endereco = new Endereco(restauranteDto.enderecoDto());
    }
}
