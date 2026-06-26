package com.example.entity;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Hospede extends Pessoa {

    private String telefone;

    private String endereco;


}