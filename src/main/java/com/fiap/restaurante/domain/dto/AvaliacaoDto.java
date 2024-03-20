package com.fiap.restaurante.domain.dto;

import org.hibernate.validator.constraints.Length;

public record AvaliacaoDto(

    @Length(min = 1, max = 2, message = "Nome dever ser preenchido entre 5 a 80 caracteres")
    String nota,

    @Length(min = 5, max = 80, message = "Comentario deve ser preenchido entre 5 a 80 caracteres")
    String comentario

) { }
