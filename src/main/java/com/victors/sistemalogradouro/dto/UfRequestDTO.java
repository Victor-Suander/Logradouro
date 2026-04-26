package com.victors.sistemalogradouro.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO usado para receber dados ao criar uma UF
public record UfRequestDTO(

        @NotBlank(message = "A sigla é Obrigatória")
        @Size(max = 2, message = "A sigla deve conter no máximo 2 caracteres")
        String sigla,

        @NotBlank(message = "O nome é Obrigatório")
        @Size(max = 100, message = "O nome deve conter no máximo 100 caracteres")
        String nome
) {
}
