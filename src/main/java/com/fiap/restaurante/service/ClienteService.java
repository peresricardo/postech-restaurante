package com.fiap.restaurante.service;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    public Cliente cadastrarCliente(ClienteDto cLienteDto);

    public Page<List<Cliente>> listarTodos(Pageable page);
}
