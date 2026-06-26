package com.example.api.Controller;

import com.example.entity.Funcionario;
import com.example.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin("*")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(service.getFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioSalvo = service.salvar(funcionario);
            return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> existente = service.getFuncionarioById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            funcionario.setId(id);
            Funcionario atualizado = service.salvar(funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (funcionario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.excluir(funcionario.get());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao excluir: " + e.getMessage());
        }
    }
}