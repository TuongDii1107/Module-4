package com.sqc.academy.bai3.controller;

import com.sqc.academy.bai3.exception.ApiException;
import com.sqc.academy.bai3.model.Department;
import com.sqc.academy.bai3.ErrorCode;
import com.sqc.academy.bai3.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final List<Department> departmentList = new ArrayList<>();

    public DepartmentController() {
        departmentList.add(new Department(UUID.randomUUID().toString(), "Human Resources"));
        departmentList.add(new Department(UUID.randomUUID().toString(), "IT"));
        departmentList.add(new Department(UUID.randomUUID().toString(), "Finance"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return JsonResponse.ok(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Department dept = departmentList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED));

        return JsonResponse.ok(dept);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        department.setId(UUID.randomUUID().toString());
        departmentList.add(department);
        return JsonResponse.create(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Department updated) {
        Department dept = departmentList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED));

        dept.setName(updated.getName());
        return JsonResponse.ok(dept);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Department dept = departmentList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTED));

        departmentList.remove(dept);
        return JsonResponse.noContent();
    }
}
