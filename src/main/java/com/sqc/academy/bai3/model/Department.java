package com.sqc.academy.bai3.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    int id;
    String name;

    public static Department of(String name) {
        return Department.builder()
                .name(name)
                .build();
    }
}
