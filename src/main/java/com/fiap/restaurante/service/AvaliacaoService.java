package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Avaliacao;
import com.fiap.restaurante.domain.dto.AvaliacaoDto;

import java.util.List;

public interface AvaliacaoService {
    Avaliacao cadastrar(AvaliacaoDto avaliacaoDto);

    List<Avaliacao> listar();
}
