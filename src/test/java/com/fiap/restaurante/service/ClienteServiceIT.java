package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Cliente;
import com.fiap.restaurante.domain.embedded.Endereco;
import com.fiap.restaurante.domain.exceptions.ClienteNotFoundException;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.serviceImpl.ClienteServiceImpl;
import com.fiap.restaurante.utils.ClienteHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ClienteServiceIT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Nested
    class RegistrarCliente {
        @Test
        void devePermitirCadastrarCliente() {
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = clienteService.clienteToDto(cliente);

            var clienteRegistrado = clienteService.cadastrarCliente(clienteDto);
            assertThat(clienteRegistrado).isInstanceOf(Cliente.class)
                    .isNotNull();
            assertThat(clienteRegistrado.getEndereco()).isInstanceOf(Endereco.class)
                    .isNotNull();
            assertThat(clienteRegistrado.getNome())
                    .isNotNull()
                    .isNotEmpty()
                    .isEqualTo(cliente.getNome());
            assertThat(clienteRegistrado.getEmail())
                    .isNotNull()
                    .isNotEmpty()
                    .isEqualTo(cliente.getEmail());
            assertThat(clienteRegistrado.getFone())
                    .isNotNull()
                    .isNotEmpty()
                    .isEqualTo(cliente.getFone());


        }
    }

    @Nested
    class RemoverCliente {

        @Test
        void devePermitirApagarMensagem() {

            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = clienteService.clienteToDto(cliente);

            var clienteRegistrado = clienteService.cadastrarCliente(clienteDto);

            var resultado = clienteService.deletarCliente(clienteRegistrado.getId());

            assertThat(resultado).isTrue();

        }
    }

    @Nested
    class ListarClientes {
        @Test
        void devePermitirListarClientes() {
            // Arrange
            var reg1 = clienteService.clienteToDto(ClienteHelper.gerarRegistro());
            clienteService.cadastrarCliente(reg1);
            var reg2 = clienteService.clienteToDto(ClienteHelper.gerarRegistro());
            clienteService.cadastrarCliente(reg2);



            // Action
            var listaRecebida = clienteService.listarTodos(Pageable.unpaged());
            // Assert
            assertThat(listaRecebida).hasSizeGreaterThan(1);
            //verify(clienteRepository, times(1)).findAll(any(Pageable.class));
        }

    }

    @Nested
    class EditarCliente {

        @Test
        void devePermitirAlterarCliente(){
            //arr

            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = clienteService.clienteToDto(cliente);
            var clienteRegistrado = clienteService.cadastrarCliente(clienteDto);

            var clienteNovo = clienteRegistrado;
            clienteNovo.setNome("Teste");
            clienteNovo.setFone("5499199999");
            clienteNovo.setEmail("teste2@teste.com");
            clienteNovo.setEndereco(cliente.getEndereco());


            var clienteObtidoDto = clienteService.editarCliente(clienteRegistrado.getId(), clienteService.clienteToDto(clienteNovo));
            var clienteObtido = new Cliente(clienteObtidoDto);
            // Asserts
            assertThat(clienteObtido).isInstanceOf(Cliente.class).isNotNull();


            assertThat(clienteObtido.getNome()).isEqualTo(clienteNovo.getNome());
            assertThat(clienteObtido.getFone()).isEqualTo(clienteNovo.getFone());
            assertThat(clienteObtido.getEmail()).isEqualTo(clienteNovo.getEmail());



        }

    }

    @Nested
    class BuscarPorId {
        @Test
        void devePermitirBuscarPorId() {
            //arr
            var cliente = ClienteHelper.gerarRegistro();
            var clienteDto = clienteService.clienteToDto(cliente);
            var clienteRegistrado = clienteService.cadastrarCliente(clienteDto);


            //act

            var clienteObtido = clienteService
                    .buscarPorId(clienteRegistrado.getId());

            //assert

            assertThat(clienteObtido).isEqualTo(clienteService.clienteToDto(clienteRegistrado));
        }

        @Test
        void deveGerarExcecao_QuandoBuscarPorId_NaoExiste() {
            var id = UUID.randomUUID();

            assertThatThrownBy(() -> clienteService.buscarPorId(id))
                    .isInstanceOf(ClienteNotFoundException.class)
                    .hasMessage("Cliente não encontrado com o ID: " + id);

        }

    }
}

