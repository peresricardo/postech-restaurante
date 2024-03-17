package com.fiap.restaurante.service;

import com.fiap.restaurante.domain.Reserva;
import com.fiap.restaurante.repository.MesaRepository;
import com.fiap.restaurante.repository.ReservaRepository;
import com.fiap.restaurante.repository.RestauranteRepository;
import com.fiap.restaurante.service.serviceImpl.ReservaServiceImpl;
import com.fiap.restaurante.utils.ReservaHelper;
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
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private RestauranteRepository restauranteRepository;
    @Mock
    private MesaRepository mesaRepository;

    private ReservaService reservaService;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        reservaService = new ReservaServiceImpl(reservaRepository,restauranteRepository, mesaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    //implementar teste padrão e exceções
//    @Test
//    void devePermitirRegistrarReserva() {
//        var reserva = ReservaHelper.gerarReserva();
//
//        when(restauranteRepository.findById(any(UUID.class))).thenReturn(Optional.of(RestauranteHelper.gerarRegistro()));
//        when(mesaRepository.findById())
//        when(reservaReporitory.save(any(Reserva.class)))
//                .thenAnswer(i -> i.getArgument(0));
//
//        var reservaRegistrada = reservaService.cadastrar(reserva);
//
//        assertThat(reservaRegistrada).isInstanceOf(Reserva.class).isNotNull();
//        assertThat(reservaRegistrada.getIdRestaurante()).isEqualTo(reserva.getIdRestaurante());
//        assertThat(reservaRegistrada.getId()).isEqualTo(reserva.getId());
//        assertThat(reservaRegistrada.getIdMesa()).isEqualTo(reserva.getIdMesa());
//
//        verify(reservaReporitory, times(1)).save(any(Reserva.class));
//    }

    @Test
    void devePermitirAlterarReserva() {
        fail("implementar teste padrão e exceções");
    }

    @Test
    void devePermitirRemoverReserva() {
        var id = UUID.fromString("51fa607a-1e61-11ee-be56-0242ac120002");
        var reserva = ReservaHelper.gerarReserva();
        reserva.setId(id);

        when(reservaRepository.findById(id))
                .thenReturn(Optional.of(reserva));

        doNothing().when(reservaRepository).deleteById(id);

        var resultado = reservaService.deletarReserva(id);

        assertThat(resultado).isTrue();

        verify(reservaRepository, times(1)).findById(any(UUID.class));
        verify(reservaRepository, times(1)).delete(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_removeIdInexistente() {
        var id = UUID.randomUUID();

        when(reservaRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservaService.deletarReserva(id)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Reserva inexistente");

        verify(reservaRepository, times(1)).findById(any(UUID.class));
        verify(reservaRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    void devePermitirListaReserva() {
        Page<Reserva> listaMensagens = new PageImpl<>(Arrays.asList(
                ReservaHelper.gerarReserva(),
                ReservaHelper.gerarReserva()
        ));

        when(reservaRepository.findAll(any(Pageable.class))).thenReturn(listaMensagens);

        var listaObtida = reservaService.listar(Pageable.unpaged());

        assertThat(listaObtida).hasSize(2);
        assertThat(listaObtida.getContent()).asList().allSatisfy(mensagem -> {
            assertThat(mensagem).isNotNull().isInstanceOf(Reserva.class);
        });

        verify(reservaRepository, times(1)).findAll(any(Pageable.class));
    }

}
