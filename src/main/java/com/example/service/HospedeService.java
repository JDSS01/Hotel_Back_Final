package com.example.service;

import com.example.entity.Hospede;
import com.example.repository.HospedeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HospedeService {

    private final HospedeRepository repository;
    private final PessoaService pessoaService;

    public HospedeService(HospedeRepository repository, PessoaService pessoaService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
    }

    public List<Hospede> getHospedes() {
        return repository.findAll();
    }

    public Optional<Hospede> getHospedeById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Hospede salvar(Hospede hospede) {
        validar(hospede);
        return repository.save(hospede);
    }

    @Transactional
    public void excluir(Hospede hospede) {
        Objects.requireNonNull(hospede.getId(), "O ID do hóspede não pode ser nulo");
        repository.delete(hospede);
    }

    public void validar(Hospede hospede) {

        pessoaService.validar(hospede);


        if (hospede.getTelefone() == null || hospede.getTelefone().trim().isEmpty()) {
            throw new RuntimeException("O telefone de contato é obrigatório para hóspedes");
        }
    }
}