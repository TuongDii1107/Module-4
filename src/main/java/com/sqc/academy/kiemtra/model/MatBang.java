package com.sqc.academy.kiemtra.model;

import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBang {
    String code;
    String name;
    String address;
    double area;
    LoaiMB type;
    double price;
    LocalDate rentDate;
}
