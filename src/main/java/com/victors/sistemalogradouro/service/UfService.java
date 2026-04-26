package com.victors.sistemalogradouro.service;

import com.victors.sistemalogradouro.dto.UfRequestDTO;
import com.victors.sistemalogradouro.dto.UfResponseDTO;
import com.victors.sistemalogradouro.entity.Uf;
import com.victors.sistemalogradouro.exception.RecursoNaoEncontradoException;
import com.victors.sistemalogradouro.exception.RegraNegException;
import com.victors.sistemalogradouro.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Regras de negócio
@RequiredArgsConstructor // Injeta dependências pelo construtor
public class UfService {

    private final UfRepository ufRepository;

    // Salva uma UF
    public UfResponseDTO salvar(UfRequestDTO dto) {

        // Impede cadastro duplicado pela sigla
        ufRepository.findBySigla(dto.sigla())
                .ifPresent(u -> {
                    throw new RegraNegException("UF já cadastrada com essa sigla");
                });

        Uf uf = Uf.builder()
                .sigla(dto.sigla())
                .nome(dto.nome())
                .build();

        Uf salva = ufRepository.save(uf);

        return toResponseDTO(salva);
    }

    // Lista todas as UFs
    public List<UfResponseDTO> listar() {
        return ufRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Busca UF por ID
    public UfResponseDTO buscarPorId(Long id) {
        Uf uf = ufRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("UF não encontrada com esse ID"));

        return toResponseDTO(uf);
    }

    // Converte Entity para DTO de resposta
    private UfResponseDTO toResponseDTO(Uf uf) {
        return new UfResponseDTO(
                uf.getId(),
                uf.getSigla(),
                uf.getNome()
        );
    }

    // Atualiza uma UF existente
    public UfResponseDTO atualizar(Long id, UfRequestDTO dto) {
        Uf uf = ufRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("UF não encontrada com esse ID"));

        // Verifica Duplicidade (se a sigla for diferente)
        ufRepository.findBySigla(dto.sigla())
                .filter (u -> !u.getId().equals(id))
                .ifPresent(u -> {
                        throw new RegraNegException("Outra UF já cadastrada com essa sigla");

                });

        // Atualiza os dados
        uf.setSigla(dto.sigla());
        uf.setNome(dto.nome());

        // Salva no bando de dados
        Uf atualizado = ufRepository.save(uf);

        return new UfResponseDTO(
                atualizado.getId(),
                atualizado.getSigla(),
                atualizado.getNome()
        );
    }

    // Remove uma UF por ID
    public void deletar(Long id) {

        // Busca a UF antes de deletar
        Uf uf =  ufRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("UF não encontrada com esse ID"));

        // Delete do Banco de Dados
        ufRepository.delete(uf);

    }
}