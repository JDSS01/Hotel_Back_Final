package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioDTO extends com.example.dto.PessoaDTO {

    private String matricula;
    private BigDecimal salario;
    private LocalDate dataAdmissao;


    private Long cargoId;
}