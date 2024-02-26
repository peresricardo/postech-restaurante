package com.fiap.restaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AvaliacaoDto(
    @NotBlank(message = "Nota é preenchimento obrigatório")
    @Size(min = 5, max = 80, message = "Nome dever ser preenchido entre 5 a 80 caracteres")
    String nota,

    @Size(min = 5, max = 80, message = "Comentario deve ser preenchido entre 5 a 80 caracteres")
    String comentario

//    ReservaDto reserva;
) {}
