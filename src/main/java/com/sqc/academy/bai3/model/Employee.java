package com.sqc.academy.bai3.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    String id;
    String name;
    LocalDate dob;
    Gender gender;
    BigDecimal salary;
    String phone;
    Integer departmentId;

    public static Employee sample(String name, LocalDate dob, Gender gender, Double salary, String phone, Integer departmentId) {
        return Employee.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .dob(dob)
                .gender(gender)
                .salary(BigDecimal.valueOf(salary))
                .phone(phone)
                .departmentId(departmentId)
                .build();
    }
    public enum Gender {
        MALE, FEMALE, OTHER
    }

}
