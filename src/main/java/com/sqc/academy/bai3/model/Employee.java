package com.sqc.academy.bai3.model;

import com.sqc.academy.bai3.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    String id;
    String name;
    LocalDate dob;
    Gender gender;
    double salary;
    String phone;
    String departmentId;

}

