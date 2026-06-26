package com.example.service;

import com.example.entity.Item;
import com.example.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // <-- Essa é a linha mágica que faltava!

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItens() {
        return repository.findAll();
    }

    public Item salvar(Item item) {
        return repository.save(item);
    }

    public Optional<Item> getItemById(Long id) {
        return repository.findById(id);
    }

    public void excluir(Item item) {
        repository.delete(item);
    }
}