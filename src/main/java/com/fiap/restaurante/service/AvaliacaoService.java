package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Avaliacao;
import com.fiap.restaurante.domain.dto.AvaliacaoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {
    Avaliacao cadastrarAvaliacao(AvaliacaoDto avaliacaoDto);

    Page<Avaliacao> listar(Pageable page);
}
