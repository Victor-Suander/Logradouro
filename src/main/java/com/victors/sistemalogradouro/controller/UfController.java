package com.victors.sistemalogradouro.controller;


import com.victors.sistemalogradouro.dto.UfRequestDTO;
import com.victors.sistemalogradouro.dto.UfResponseDTO;
import com.victors.sistemalogradouro.entity.Uf;
import com.victors.sistemalogradouro.service.UfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Permite criar endpoints REST
@RequestMapping("/ufs") //Caminho base da API
@RequiredArgsConstructor // Injeta o service automaticamente
public class UfController {

    private final UfService ufService;

    @PostMapping //POST /ufs
    public ResponseEntity<UfResponseDTO> salvar(@RequestBody @Valid UfRequestDTO dto) {

        // Chama o service passando DTO
        UfResponseDTO ufSalva = ufService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ufSalva);
    }

    @GetMapping //GET /ufs
    public ResponseEntity<List<UfResponseDTO>> listar() {
        return ResponseEntity.ok(ufService.listar());
    }

    @GetMapping("/{id}") //GET /ufs/{id}
    public ResponseEntity<UfResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ufService.buscarPorId(id));
    }

    @PutMapping("/{id}") //PUT /ufs/{id}
    public ResponseEntity<UfResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UfRequestDTO dto) {

        return ResponseEntity.ok(ufService.atualizar(id, dto));
    }
}
