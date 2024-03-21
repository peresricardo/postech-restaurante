package com.fiap.restaurante.exception;

public class ClienteNotFoundException extends RuntimeException  {
    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
