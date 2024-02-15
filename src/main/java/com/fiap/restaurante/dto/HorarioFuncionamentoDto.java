package com.fiap.restaurante.dto;

import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record HorarioFuncionamentoDto(
        @NotBlank(message = "Dia da Semana é preenchimento obrigatório")
        @Size(min = 5, max = 15, message = "Dia da Semana dever ser preenchido entre 5 a 15 caracteres")
        String razaoSocial,
        @NotNull(message = "Horário de abertura é preenchimento obrigatório")
        LocalTime horaAbertura,
        @NotNull(message = "Horário de fechamento é preenchimento obrigatório")
        LocalTime horaFechamento
) {}
