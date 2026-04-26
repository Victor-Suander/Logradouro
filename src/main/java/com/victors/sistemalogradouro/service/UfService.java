package com.victors.sistemalogradouro.service;

import com.victors.sistemalogradouro.dto.UfRequestDTO;
import com.victors.sistemalogradouro.dto.UfResponseDTO;
import com.victors.sistemalogradouro.entity.Uf;
import com.victors.sistemalogradouro.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.victors.sistemalogradouro.exception.RegraNegException;
import java.util.List;

@Service // onde ficam as regras de negócio
@RequiredArgsConstructor // Injeta dependências automaticamente
public class UfService {

    // Repositorio para acessar o banco de dados
    private final UfRepository ufRepository;
    // Salva um UF usando DTO
    public UfResponseDTO salvar(UfRequestDTO dto) {

        // Valida Duplicidade
        ufRepository.findBySigla(dto.sigla())
                .ifPresent(u -> {
                    throw new RegraNegException("UF Já cadastrado com essa sigla");
                });

        // Converte DTO para Entity
        Uf uf = Uf.builder()
                .sigla(dto.sigla())
                .nome(dto.nome())
                .build();

        // Salva no banco de dados
        Uf salva = ufRepository.save(uf);

        // Converte Entity para DTO
        return new UfResponseDTO(
                salva.getId(),
                salva.getSigla(),
                salva.getNome());
    }

    // Lista todos os UFs como DTO
    public List<UfResponseDTO> listar() {
        return ufRepository.findAll()
                .stream()
                .map(uf -> new UfResponseDTO(
                        uf.getId(),
                        uf.getSigla(),
                        uf.getNome()))
                .toList();
    }

    // Busca UF por ID
    public UfResponseDTO buscarPorId(Long id) {
        Uf uf = ufRepository.findById(id)
                .orElseThrow(() -> new RegraNegException("UF não encontrada com esse ID"));
        return new UfResponseDTO(
                uf.getId(),
                uf.getSigla(),
                uf.getNome());
    }
}

