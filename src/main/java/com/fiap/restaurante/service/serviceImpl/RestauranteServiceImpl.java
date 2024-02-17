package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.domain.Restaurante;
import com.fiap.restaurante.domain.dto.EnderecoDto;
import com.fiap.restaurante.domain.dto.RestauranteDto;
import com.fiap.restaurante.domain.embedded.Endereco;
import com.fiap.restaurante.domain.exceptions.RestauranteNotFoundException;
import com.fiap.restaurante.repository.RestauranteRepository;
import com.fiap.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Autowired
    public RestauranteServiceImpl (RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Restaurante cadastrar(RestauranteDto restauranteDto) {
        Restaurante novoRestaurante = new Restaurante(restauranteDto);
        restauranteRepository.save(novoRestaurante);
        return novoRestaurante;
    }

    @Override
    public List<Restaurante> listar() {
        return this.restauranteRepository.findAll();
    }

    @Override
    public Restaurante atualizarRestaurante(UUID id, RestauranteDto restauranteDto) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(RestauranteNotFoundException::new);

        restaurante.setRazaoSocial(restauranteDto.razaoSocial());
        restaurante.setNomeFantasia(restauranteDto.nomeFantasia());
        restaurante.setTipoCozinha(restauranteDto.tipoCozinha());
        restaurante.setCapacidade(restauranteDto.capacidade());

        Optional.ofNullable(restauranteDto.enderecoDto())
                .ifPresent(e -> updateEndereco(restaurante.getEndereco(), e));

        return restauranteRepository.save(restaurante);
    }

    @Override
    public void deletarRestaurante(UUID id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(RestauranteNotFoundException::new);

        restauranteRepository.delete(restaurante);
    }

    private void updateEndereco(Endereco endereco, EnderecoDto novoEndereco) {
        endereco.setLogradouro(novoEndereco.logradouro());
        endereco.setNumero(novoEndereco.numero());
        endereco.setComplemento(novoEndereco.complemento());
        endereco.setBairro(novoEndereco.bairro());
        endereco.setCidade(novoEndereco.cidade());
        endereco.setUf(novoEndereco.uf());
        endereco.setCep(novoEndereco.cep());
    }
}
