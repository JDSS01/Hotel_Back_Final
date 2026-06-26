package com.example.service;

import com.example.entity.Item;
import com.example.entity.Pedido;
import com.example.entity.Servico;
import com.example.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> getPedidos() {
        return repository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pedido salvar(Pedido pedido) {
        validar(pedido);

        calcularValorTotal(pedido);

        if (pedido.getId() == null) {
            pedido.setStatus("ABERTO");
        }

        return repository.save(pedido);
    }

    @Transactional
    public void excluir(Pedido pedido) {
        Objects.requireNonNull(pedido.getId(), "O ID do pedido não pode ser nulo");
        repository.delete(pedido);
    }

    public void validar(Pedido pedido) {
        if (pedido.getHospede() == null || pedido.getHospede().getId() == null) {
            throw new RuntimeException("O pedido deve estar vinculado a um hóspede");
        }
        if (pedido.getQuarto() == null || pedido.getQuarto().getId() == null) {
            throw new RuntimeException("O pedido deve ser entregue em um quarto");
        }
    }

    private void calcularValorTotal(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;

        if (pedido.getItens() != null) {
            for (Item item : pedido.getItens()) {
                if (item.getValor() != null) {

                    total = total.add(BigDecimal.valueOf(item.getValor()));
                }
            }
        }

        if (pedido.getServicos() != null) {
            for (Servico servico : pedido.getServicos()) {
                if (servico.getValor() != null) {

                    total = total.add(BigDecimal.valueOf(servico.getValor()));
                }
            }
        }

        pedido.setValorTotal(total);
    }
}