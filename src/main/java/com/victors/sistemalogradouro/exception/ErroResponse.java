package com.victors.sistemalogradouro.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

// Classe para retornar erros
@Getter
@AllArgsConstructor
public class ErroResponse {

    private String erro;
    private int status;

}
