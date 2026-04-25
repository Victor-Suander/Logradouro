package com.victors.sistemalogradouro.exception;

// Tratar erros : regra de negócio
public class RegraNegException extends RuntimeException {

    public RegraNegException(String message){
        super(message);
    }
}
