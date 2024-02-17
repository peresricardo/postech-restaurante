package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente cadastrarCliente(ClienteDto cLienteDto) {
        return null;
    }

    @Override
    public Page<List<Cliente>> listarTodos(Pageable page) {
        return null;
    }


}
