package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.mapper.ClienteMapper;
import com.fiap.restaurante.model.Cliente;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteMapper clienteMapper) {
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ResponseEntity<?> cadastrarCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.fromDto(clienteDto);
        this.clienteRepository.save(clienteMapper.fromDto(clienteDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Page<List<Cliente>> listarTodos(Pageable page) {
        return null;
    }


}
