package com.example.service;

import com.example.entity.Quarto;
import com.example.repository.QuartoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuartoService {


    private final QuartoRepository repository;

    public QuartoService(QuartoRepository repository) {
        this.repository = repository;
    }

    public List<Quarto> getQuartos() {
        return repository.findAll();
    }

    public Optional<Quarto> getQuartoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Quarto salvar(Quarto quarto) {
        validar(quarto);
        return repository.save(quarto);
    }

    @Transactional
    public void excluir(Quarto quarto) {
        Objects.requireNonNull(quarto.getId(), "O ID do quarto não pode ser nulo");
        repository.delete(quarto);
    }

    public void validar(Quarto quarto) {
        if (quarto.getNumero() == null || quarto.getNumero().trim().isEmpty()) {
            throw new RuntimeException("O número do quarto é obrigatório");
        }

        if (quarto.getTipo() == null || quarto.getTipo().trim().isEmpty()) {
            throw new RuntimeException("O tipo do quarto é obrigatório");
        }

        if (quarto.getCapacidade() == null || quarto.getCapacidade() <= 0) {
            throw new RuntimeException("A capacidade do quarto deve ser maior que zero");
        }

        if (quarto.getValorDiaria() == null || quarto.getValorDiaria().doubleValue() <= 0) {
            throw new RuntimeException("O valor da diária deve ser maior que zero");
        }
    }
}