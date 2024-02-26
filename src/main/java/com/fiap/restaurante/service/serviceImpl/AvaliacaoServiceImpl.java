package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.domain.Avaliacao;
import com.fiap.restaurante.domain.Restaurante;
import com.fiap.restaurante.domain.dto.AvaliacaoDto;
import com.fiap.restaurante.repository.AvaliacaoRepository;
import com.fiap.restaurante.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public Avaliacao cadastrar(AvaliacaoDto avaliacaoDto) {
        Avaliacao novaAvaliacao = new Avaliacao(avaliacaoDto);
        avaliacaoRepository.save(novaAvaliacao);
        return novaAvaliacao;
    }

    @Override
    public List<Avaliacao> listar() {
        return this.avaliacaoRepository.findAll();
    }

}
