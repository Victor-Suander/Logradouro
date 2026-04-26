package com.victors.sistemalogradouro.dto;


// DTO usado para devolver dados de UF na respota da API
public record UfResponseDTO(

        Long id,
        String sigla,
        String nome

) {
}
