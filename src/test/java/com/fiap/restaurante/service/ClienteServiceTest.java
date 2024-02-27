package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Cliente;
import com.fiap.restaurante.domain.dto.ClienteDto;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.serviceImpl.ClienteServiceImpl;
import com.fiap.restaurante.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository);

    }

    @AfterEach
    void tearDow() throws Exception{
        openMocks.close();
    }

    @Test
    void devePermitirCadastrarCliente(){
        //arr
        var cliente = ClienteHelper.gerarRegistro();

        //act
        when(clienteRepository.save(any(Cliente.class)))
                .thenAnswer(i -> i.getArgument(0));

        var clienteRegistrado = clienteService.cadastrarCliente(clienteService.clienteToDto(cliente));
        //assert

        assertThat(clienteRegistrado).isInstanceOf(ClienteDto.class)
                .isNotNull();
        assertThat(clienteRegistrado.getNome())
                .isEqualTo(cliente.getNome());
        assertThat(clienteRegistrado.getFone())
                .isEqualTo(cliente.getFone());
        assertThat(cliente.getId()).isNotNull();
        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

}
