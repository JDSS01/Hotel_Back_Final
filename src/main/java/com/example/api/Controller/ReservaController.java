package com.example.api.Controller;

import com.example.entity.Reserva;
import com.example.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin("*")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodos() {
        return ResponseEntity.ok(service.getReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = service.getReservaById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Reserva reserva) {
        try {
            Reserva reservaSalva = service.salvar(reserva);
            return new ResponseEntity<>(reservaSalva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Reserva reserva) {
        Optional<Reserva> reservaExistente = service.getReservaById(id);
        if (reservaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            reserva.setId(id);
            Reserva reservaAtualizada = service.salvar(reserva);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        Optional<Reserva> reserva = service.getReservaById(id);
        if (reserva.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.excluir(reserva.get());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}