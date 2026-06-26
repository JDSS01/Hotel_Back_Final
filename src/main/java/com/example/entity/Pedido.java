package com.example.entity;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora = LocalDateTime.now();


    private String status;


    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;


    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;


    @ManyToMany
    @JoinTable(
            name = "pedido_item",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itens;


    @ManyToMany
    @JoinTable(
            name = "pedido_servico",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;


    private BigDecimal valorTotal;
}