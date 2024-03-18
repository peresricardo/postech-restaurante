package com.fiap.restaurante.exception;

public class TrataMensagem {
    public String TrataMensagemErro(String message) {
        String smensagem = "\"" + message + "\"";
        String retorno = "{ \"message\": " + smensagem + " }";
        return retorno;
    }
}
