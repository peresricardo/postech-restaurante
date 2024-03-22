package com.fiap.restaurante.controller;

import com.callibrity.logging.test.LogTracker;
import com.callibrity.logging.test.LogTrackerStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurante.controller.ClienteController;
import com.fiap.restaurante.domain.dto.ClienteDto;
import com.fiap.restaurante.domain.dto.EnderecoDto;
import com.fiap.restaurante.dto.ClienteRequest;
import com.fiap.restaurante.handler.GlobalExceptionHandler;
import com.fiap.restaurante.repository.ClienteRepository;
import com.fiap.restaurante.service.ClienteService;
import com.fiap.restaurante.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClienteControllerTest {

    private MockMvc mockMvc;

    @RegisterExtension
    LogTrackerStub logTracker = LogTrackerStub.create().recordForLevel(LogTracker.LogLevel.INFO)
            .recordForType(ClienteController.class);

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ClienteController clienteController = new ClienteController(clienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarCliente {
        @Test
        void devePermitirCadastrarCliente() throws Exception {
            var clienteRequest = ClienteHelper.gerarRegistroRequest();
            when(clienteService.cadastrarCliente(any(ClienteDto.class)))
                .thenAnswer(i -> i.getArgument(0));

            mockMvc.perform(post("/clientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(clienteRequest)))
                    .andExpect(status().isCreated());

            verify(clienteService, times(1))
                    .cadastrarCliente(any(ClienteDto.class));


        }
    }

    @Nested
    class BuscarCliente{

        @Test
        void devePermetirBuscarClientePorId() throws Exception {
            var id = UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002");
            var cliente = ClienteHelper.gerarRegistro();
            cliente.setId(id);
            var clienteDto = clienteToDto(cliente);

            when(clienteService.buscarPorId(any(UUID.class))).thenReturn(clienteDto);

            mockMvc.perform(get("/clientes/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(cliente.getId().toString()))
                    .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                    .andExpect(jsonPath("$.email").value(cliente.getEmail()))
                    .andExpect(jsonPath("$.endereco").exists());
        }

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public ClienteDto clienteToDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getFone(),
                new EnderecoDto(
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getUf(),
                        cliente.getEndereco().getCep()
                )
        );

    }

    public ClienteDto clienteToDto(ClienteRequest cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getFone(),
                new EnderecoDto(
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getUf(),
                        cliente.getEndereco().getCep()
                )
        );

    }
}
