package com.victors.sistemalogradouro.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity //mapeia a classe para uma tabela
@Table(name = "ufs") // nome da tabela
@Getter
@Setter
@NoArgsConstructor // gera um construtor sem argumentos(necessario para JPA)
@AllArgsConstructor
@Builder

public class Uf {

    @Id // chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera o id automaticamente
    private Long id;
    @Column(nullable = false, length = 2, unique = true) // Ex: SP, RJ
    private String sigla;
    @Column(nullable = false, length = 100) // Nome completo
    private String nome;

}
