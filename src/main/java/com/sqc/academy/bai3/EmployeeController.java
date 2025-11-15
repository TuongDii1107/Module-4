package com.sqc.academy.bai3;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeController() {
        employeeList.add(new Employee(UUID.randomUUID().toString(), "Nguyen Van A",
                LocalDate.of(1990, 1, 1), Gender.MALE, 5000, "0123456789", null));

        employeeList.add(new Employee(UUID.randomUUID().toString(), "Tran Thi B",
                LocalDate.of(1992, 5, 10), Gender.FEMALE, 6000, "0987654321", null));
    }

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String departmentId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(required = false) String salaryRange
    ) {

        List<Employee> result = employeeList.stream()
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> phone == null || e.getPhone().contains(phone))
                .filter(e -> gender == null || e.getGender() == gender)
                .filter(e -> departmentId == null || Objects.equals(e.getDepartmentId(), departmentId))
                .filter(e -> dobFrom == null || !e.getDob().isBefore(dobFrom))
                .filter(e -> dobTo == null || !e.getDob().isAfter(dobTo))
                .filter(e -> matchSalaryRange(e.getSalary(), salaryRange))
                .collect(Collectors.toList());

        return JsonResponse.ok(result);
    }

    private boolean matchSalaryRange(double salary, String range) {
        if (range == null) return true;

        return switch (range) {
            case "lt5" -> salary < 5000; //Chỉ chọn nhân viên có lương < 5000.
            case "5-10" -> salary >= 5000 && salary <= 10000; //Lương từ 5.000 đến 10.000
            case "10-20" -> salary >= 10000 && salary <= 20000;//Lương từ 10.000 đến 20.000
            case "gt20" -> salary > 20000;//Lương > 20.000
            default -> true;
        };
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Employee emp = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND));

        return JsonResponse.ok(emp);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employeeList.add(employee);
        return JsonResponse.create(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Employee updated) {
        Employee emp = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND));

        emp.setName(updated.getName());
        emp.setDob(updated.getDob());
        emp.setGender(updated.getGender());
        emp.setSalary(updated.getSalary());
        emp.setPhone(updated.getPhone());
        emp.setDepartmentId(updated.getDepartmentId());

        return JsonResponse.ok(emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Employee emp = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND));

        employeeList.remove(emp);
        return JsonResponse.noContent();
    }
}
