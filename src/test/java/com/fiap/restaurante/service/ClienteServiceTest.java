package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Cliente;
import com.fiap.restaurante.domain.dto.ClienteDto;
import com.fiap.restaurante.domain.embedded.Endereco;
import com.fiap.restaurante.domain.exceptions.ClienteNotFoundException;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.serviceImpl.ClienteServiceImpl;
import com.fiap.restaurante.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        var id = UUID.randomUUID();
        var cliente = ClienteHelper.gerarRegistro();
        cliente.setId(id);

        //act
        when(clienteRepository.save(any(Cliente.class)))
                .thenAnswer(i -> i.getArgument(0));

        var clienteRegistrado = clienteService.cadastrarCliente(clienteService.clienteToDto(cliente));
        //assert

        assertThat(clienteRegistrado).isInstanceOf(Cliente.class)
                .isNotNull();
        assertThat(clienteRegistrado.getEndereco()).isInstanceOf(Endereco.class)
                .isNotNull();
        assertThat(clienteRegistrado.getNome())
                .isEqualTo(cliente.getNome());
        assertThat(clienteRegistrado.getEmail())
                .isEqualTo(cliente.getEmail());
        assertThat(clienteRegistrado.getFone())
                .isEqualTo(cliente.getFone());
        assertThat(cliente.getId()).isNotNull();
        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
    void devePermitirListarClientes() {
        // Arrange
        var reg1 = clienteService.clienteToDto(ClienteHelper.gerarRegistro());
        var reg2 = clienteService.clienteToDto(ClienteHelper.gerarRegistro());
        var lista = new PageImpl<>(Arrays.asList(reg1, reg2));

        when(clienteService.listarTodos(Pageable.unpaged())).thenReturn(lista);
        // Action
        var listaRecebida = clienteService.listarTodos(Pageable.unpaged());
        // Assert
        assertThat(listaRecebida).hasSizeGreaterThan(1);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void devePermitirBuscarPorId(){
        //arr
        var id = UUID.randomUUID();
        var cliente = ClienteHelper.gerarRegistro();
        cliente.setId(id);
        var clienteDto = clienteService.clienteToDto(cliente);


        when(clienteRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(cliente));

        //act

        var clienteObtido = clienteService
                .buscarPorId(cliente.getId());

        //assert

        assertThat(clienteObtido).isEqualTo(clienteDto);
        verify(clienteRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    void deveGerarExcecao_QuandoBuscarPorId_NaoExiste(){
        var id = UUID.randomUUID();

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> clienteService.buscarPorId(id))
                .isInstanceOf(ClienteNotFoundException.class)
                .hasMessage("Cliente não encontrado com o ID: "+id);
        verify(clienteRepository, times(1)).findById(id);
    }

}
