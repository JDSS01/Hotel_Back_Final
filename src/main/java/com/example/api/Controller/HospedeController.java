package com.example.api.Controller;

import com.example.entity.Hospede;
import com.example.service.HospedeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/hospedes")
public class HospedeController {

    private final HospedeService service;

    public HospedeController(HospedeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Hospede>> listarTodos() {
        return ResponseEntity.ok(service.getHospedes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> buscarPorId(@PathVariable Long id) {
        Optional<Hospede> hospede = service.getHospedeById(id);
        return hospede.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Hospede hospede) {
        try {
            Hospede hospedeSalvo = service.salvar(hospede);
            return new ResponseEntity<>(hospedeSalvo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Hospede hospede) {
        Optional<Hospede> hospedeExistente = service.getHospedeById(id);
        if (hospedeExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {

            hospede.setId(id);
            Hospede hospedeAtualizado = service.salvar(hospede);
            return ResponseEntity.ok(hospedeAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        Optional<Hospede> hospede = service.getHospedeById(id);
        if (hospede.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.excluir(hospede.get());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}