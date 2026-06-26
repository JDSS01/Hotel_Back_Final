package com.example.dto;

import com.example.entity.Item; // Importação da sua entidade Item
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;


    public static ItemDTO create(Item item) {
        ModelMapper modelMapper = new ModelMapper();
        ItemDTO dto = modelMapper.map(item, ItemDTO.class);
        return dto;
    }
}