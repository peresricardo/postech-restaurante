package com.fiap.restaurante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteDto(
        @NotBlank(message = "Nome é preenchimento obrigatório")
        @Size(min = 5, max = 80, message = "Nome dever ser preenchido entre 5 a 80 caracteres")
        String nome,
        @NotBlank(message = "Email é preenchimento obrigatório")
        @Size(min = 5, max = 80, message = "Email dever ser preenchido entre 5 a 80 caracteres")
        String email,
        @NotBlank(message = "Fone é preenchimento obrigatório")
        @Size(min = 5, max = 80, message = "Fone dever ser preenchido")
        String fone,
        @NotNull
        EnderecoDto enderecoDto
) { }
