package com.fiap.restaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AvaliacaoDto(

    @NotBlank
    @Size(min = 1, message = "Nota deve ser preenchido com 1 a 5")
    String nota,

    @Size(min = 5, max = 80, message = "Comentario deve ser preenchido entre 5 a 80 caracteres")
    String comentario

) { }
