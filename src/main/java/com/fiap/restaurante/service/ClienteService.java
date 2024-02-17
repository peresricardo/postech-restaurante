package com.fiap.restaurante.service;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    public ResponseEntity<?> cadastrarCliente(ClienteDto cLienteDto);

    public Page<List<Cliente>> listarTodos(Pageable page);
}
