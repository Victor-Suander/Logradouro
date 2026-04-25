package com.victors.sistemalogradouro.repository;

import com.victors.sistemalogradouro.entity.Uf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Long> {

    // Busca por sigla: "UF"
    Optional<Uf> findBySigla(String sigla);
}
