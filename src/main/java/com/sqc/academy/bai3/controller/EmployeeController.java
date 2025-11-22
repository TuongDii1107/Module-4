package com.sqc.academy.bai3.controller;

import com.sqc.academy.bai3.JsonResponse;
import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.model.Employee;
import com.sqc.academy.bai3.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {

    IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> search(EmployeeSearchRequest request) {
        return JsonResponse.ok(employeeService.search(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return JsonResponse.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        return JsonResponse.create(employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Employee employee) {
        return JsonResponse.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        employeeService.delete(id);
        return JsonResponse.noContent();
    }
}

