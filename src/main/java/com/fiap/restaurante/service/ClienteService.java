package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Cliente;
import com.fiap.restaurante.domain.dto.ClienteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Cliente cadastrarCliente(ClienteDto cLienteDto);

    Page<Cliente> listarTodos(Pageable page);
}
