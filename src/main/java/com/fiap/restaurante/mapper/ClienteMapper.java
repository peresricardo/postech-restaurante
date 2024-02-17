package com.fiap.restaurante.mapper;

import com.fiap.restaurante.dto.ClienteDto;
import com.fiap.restaurante.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public  class ClienteMapper {
    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getFone(),
                cliente.getEndereco()
        );
    }

    public Cliente fromDto(ClienteDto clienteDto) {
        return new Cliente(
                clienteDto.nome(),
                clienteDto.email(),
                clienteDto.fone(),
                clienteDto.enderecoDto()
        );
    }
}
