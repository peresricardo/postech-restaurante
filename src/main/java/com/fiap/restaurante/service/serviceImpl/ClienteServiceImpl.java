package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.mapper.ClienteMapper;
import com.fiap.restaurante.model.Cliente;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente cadastrarCliente(ClienteDto clienteDto) {
        Cliente novoCliente = new Cliente(clienteDto);
        clienteRepository.save(novoCliente);
        return novoCliente;
    }

    @Override
    public Page<Cliente> listarTodos(Pageable page) {
        return clienteRepository.findAll(page);
    }


}
