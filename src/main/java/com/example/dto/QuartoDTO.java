package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {

    private Long id;
    private String numero;
    private String tipo;
    private Integer capacidade;
    private BigDecimal valorDiaria;

}