package com.fiap.restaurante.service;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Cliente cadastrarCliente(ClienteDto cLienteDto);

    Page<Cliente> listarTodos(Pageable page);
}
