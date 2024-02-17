package com.fiap.restaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDto(
        @NotBlank(message = "Endereço é preenchimento obrigatório")
        @Size(min = 5, max = 80, message = "Logradouro dever ser preenchido entre 5 a 80 caracteres")
        String logradouro,
        @NotBlank(message = "Número é preenchimento obrigatório")
        @Size(min = 1, max = 10, message = "Número deve ser preenchido entre 1 a 10 caracteres")
        String numero,
        @Size(max = 40, message = "Complemento deve ser preenchido utilizando até 40 caracteres")
        String complemento,
        @NotBlank(message = "Bairro é preenchimento obrigatório")
        @Size(min = 5, max = 60, message = "Bairro deve ser preenchido entre 5 a 60 caracteres")
        String bairro,
        @NotBlank(message = "Cidade é preenchimento obrigatório")
        @Size(min = 5, max = 60, message = "Cidade deve ser preenchido entre 5 a 60 caracteres")
        String cidade,
        @NotBlank(message = "UF é preenchimento obrigatório")
        @Size(min = 2, max = 2, message = "UF deve ser preenchido utilizando 2 caracteres")
        String uf,
        @NotBlank(message = "Cep é preenchimento obrigatório")
        @Size(min = 9, max = 9, message = "Cep deve ser preenchido utilizando 9 caracteres")
        String cep
) {}
