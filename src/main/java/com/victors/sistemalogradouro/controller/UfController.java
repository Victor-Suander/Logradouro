package com.victors.sistemalogradouro.controller;


import com.victors.sistemalogradouro.entity.Uf;
import com.victors.sistemalogradouro.service.UfService;
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
    public ResponseEntity<Uf> salvar(@RequestBody Uf uf) {
        Uf ufSalva = ufService.salvar(uf);
        return ResponseEntity.status(HttpStatus.CREATED).body(ufSalva);
    }

    @GetMapping //GET /ufs
    public ResponseEntity<List<Uf>> listar() {
        List<Uf> ufs = ufService.listar();
        return ResponseEntity.ok(ufs);
    }

}
