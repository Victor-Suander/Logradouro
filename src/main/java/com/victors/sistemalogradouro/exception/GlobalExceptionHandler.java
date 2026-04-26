package com.victors.sistemalogradouro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Classe responsavel por tratar erros da aplicação
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratar erros de regra de negócio
    @ExceptionHandler(RegraNegException.class)
    public ResponseEntity<Object> handleRegraNegException(RegraNegException ex) {

        // Retorna um erro 400 com mensagem personalizada
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(ex.getMessage(), 400));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleNotFound(RecursoNaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(ex.getMessage(), 404));
    }
}
