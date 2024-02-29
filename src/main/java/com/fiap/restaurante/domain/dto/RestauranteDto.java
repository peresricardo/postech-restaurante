package com.fiap.restaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record RestauranteDto(
        @NotBlank(message = "Razão Social é preenchimento obrigatório")
        @Size(min = 5, max = 80, message = "Razão Social dever ser preenchido entre 5 a 80 caracteres")
        String razaoSocial,
        @NotBlank(message = "Nome Fantasia é preenchimento obrigatório")
        @Size(min = 5, max = 60, message = "Nome Fantasia dever ser preenchido entre 5 a 60 caracteres")
        String nomeFantasia,
        @NotBlank(message = "Tipo de Cozinha é preenchimento obrigatório")
        @Size(min = 5, max = 50, message = "Tipo de cozinha dever ser preenchido entre 5 a 50 caracteres")
        String tipoCozinha,
        @NotNull(message = "Capacidade é preenchimento obrigatório")
        Integer capacidade,
        @NotBlank(message = "Horário de abertura é obrigatório")
        LocalTime horaAbertura,
        @NotBlank(message = "Horário de encerramento é obrigatório")
        LocalTime horaEncerramento,
        EnderecoDto endereco
) {}
