package com.example.service;

import com.example.entity.Pessoa;
import com.example.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> getPessoas() {
        return repository.findAll();
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        validar(pessoa);
        return repository.save(pessoa);
    }

    @Transactional
    public void excluir(Pessoa pessoa) {
        Objects.requireNonNull(pessoa.getId(), "O ID não pode ser nulo");
        repository.delete(pessoa);
    }

    public void validar(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome inválido");
        }
        if (pessoa.getCpf() == null || pessoa.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF inválido");
        }
        if (pessoa.getEmail() == null || pessoa.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email inválido");
        }
    }
}