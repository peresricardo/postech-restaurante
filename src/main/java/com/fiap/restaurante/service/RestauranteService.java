package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Restaurante;
import com.fiap.restaurante.domain.dto.RestauranteDto;

import java.util.List;
import java.util.UUID;

public interface RestauranteService {

    Restaurante cadastrar(RestauranteDto restauranteDto);

    List<Restaurante> listar();

    Restaurante atualizarRestaurante(UUID id, RestauranteDto restauranteDto);

    void deletarRestaurante(UUID id);
}
