package com.sqc.academy.bai3.dto;

import com.sqc.academy.bai3.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeSearchRequest {
    private String name;
    private String phone;
    private Gender gender;
    private String departmentId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dobFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dobTo;
    private String salaryRange;
}

