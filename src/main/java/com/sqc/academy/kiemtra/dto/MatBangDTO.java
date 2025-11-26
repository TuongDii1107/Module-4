package com.sqc.academy.kiemtra.dto;

import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBangDTO {
    String code;
    String name;
    String address;
    double area;
    int typeId;
    String typeName;
    double price;
    LocalDate rentDate;
}
