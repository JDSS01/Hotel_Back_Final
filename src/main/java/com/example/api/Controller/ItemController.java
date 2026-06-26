package com.example.api.Controller;

import com.example.entity.Item;
import com.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin("*")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Item>> listarTodos() {
        return ResponseEntity.ok(service.getItens());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Optional<Item> item = service.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> salvar(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Item item) {
        Optional<Item> itemExistente = service.getItemById(id);
        if (itemExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            item.setId(id);
            Item itemAtualizado = service.salvar(item);
            return ResponseEntity.ok(itemAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        Optional<Item> item = service.getItemById(id);
        if (item.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.excluir(item.get());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}