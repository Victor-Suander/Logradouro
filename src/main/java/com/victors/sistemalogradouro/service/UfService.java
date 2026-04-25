package com.victors.sistemalogradouro.service;

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
    // Salva um UF com validacao
    public Uf salvar(Uf uf) {

        // Verifica se já existe um UF com a mesma sigla
        ufRepository.findBySigla(uf.getSigla()).ifPresent(existing -> {
            throw new RegraNegException("UF já cadastado com essa sigla");
        });
        // Salva o UF
        return ufRepository.save(uf);
    }
    // Lista todos os UFs
    public List<Uf> listar() {
        return ufRepository.findAll();
    }
}
