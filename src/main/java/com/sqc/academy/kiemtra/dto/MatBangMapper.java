package com.sqc.academy.kiemtra.dto;

import com.sqc.academy.kiemtra.model.MatBang;

public class MatBangMapper {
    public static MatBangDTO toDTO(MatBang mb) {
        if(mb==null) return null;
        return MatBangDTO.builder()
                .code(mb.getCode())
                .name(mb.getName())
                .address(mb.getAddress())
                .area(mb.getArea())
                .typeId(mb.getType().getId())
                .typeName(mb.getType().getName())
                .price(mb.getPrice())
                .rentDate(mb.getRentDate())
                .build();
    }
}
