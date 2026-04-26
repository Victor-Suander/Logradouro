package com.victors.sistemalogradouro.exception;

// Exception para quando um recurso não é encontrado
public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
