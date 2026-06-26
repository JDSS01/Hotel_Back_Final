package com.example.service;

import com.example.entity.Reserva;
import com.example.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> getReservas() {
        return repository.findAll();
    }

    public Optional<Reserva> getReservaById(Long id) {
        return repository.findById(id);
    }

    public Reserva salvar(Reserva reserva) {
        return repository.save(reserva);
    }

    public void excluir(Reserva reserva) {
        repository.delete(reserva);
    }
}