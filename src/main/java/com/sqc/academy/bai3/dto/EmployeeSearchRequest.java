package com.sqc.academy.bai3.dto;

import com.sqc.academy.bai3.model.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;
    LocalDate dobFrom;
    LocalDate dobTo;
    Gender gender;
    String salaryRange;
    String phone;
    String departmentId;
}
