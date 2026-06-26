package com.example.service;

import com.example.entity.Servico;
import com.example.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> getServicos() {
        return repository.findAll();
    }

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public Optional<Servico> getServicoById(Long id) {
        return repository.findById(id);
    }

    public void excluir(Servico servico) {
        repository.delete(servico);
    }
}