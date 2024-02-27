package com.fiap.restaurante.service.serviceImpl;

import com.fiap.restaurante.domain.Reserva;
import com.fiap.restaurante.domain.Restaurante;
import com.fiap.restaurante.domain.exceptions.RestauranteNotFoundException;
import com.fiap.restaurante.repository.ReservaRepository;
import com.fiap.restaurante.repository.RestauranteRepository;
import com.fiap.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final RestauranteRepository restauranteRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository, RestauranteRepository restauranteRepository) {
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Reserva cadastrar(Reserva reserva) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(reserva.getIdRestaurante());
        if(!restaurante.isPresent()) {
            throw new RestauranteNotFoundException("Para fazer uma reserva indique um restaurante existente");
        }
        if(!restaurante.get().getMesas().contains(reserva.getIdMesa())) {
            throw new IllegalArgumentException("Indique uma mesa existente");
        }

        List<Reserva> reservas = reservaRepository.findAll();
        String erro = verificaNovaReserva(reservas, reserva);

        if(!erro.isEmpty()) {
            throw new IllegalArgumentException(erro);
        }

        return reservaRepository.save(reserva);
    }

    public String verificaNovaReserva(List<Reserva> reservas, Reserva reserva) {
        String check = "";
        for (Reserva r : reservas) {
            if (r.getIdRestaurante().equals(reserva.getIdRestaurante()) &&
                    r.getIdMesa().equals(reserva.getIdMesa()) &&
                    r.getHorario().equals(reserva.getHorario())) {
                check = "Reserva já existente";
                break;
            }
        }
        return check;
    }

    @Override
    public List<Reserva> listar() {
        return this.reservaRepository.findAll();
    }

    @Override
    public Reserva atualizarReserva(UUID id, Reserva reserva) {
        if (!reservaRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Indique uma reserva existente");
        }

        Optional<Restaurante> restaurante = restauranteRepository.findById(reserva.getIdRestaurante());
        if(!restaurante.isPresent()) {
            throw new RestauranteNotFoundException("Para fazer uma reserva indique um restaurante existente");
        }
        if(!restaurante.get().getMesas().contains(reserva.getIdMesa())) {
            throw new IllegalArgumentException("Indique uma mesa existente");
        }

        List<Reserva> reservas = reservaRepository.findAll();
        String erro = verificaNovaReserva(reservas, reserva);

        if(!erro.isEmpty()) {
            throw new IllegalArgumentException(erro);
        }

        reserva.setId(id);

        return reservaRepository.save(reserva);
    }

    @Override
    public void deletarReserva(UUID id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva inexistente"));

        reservaRepository.delete(reserva);
    }

}
