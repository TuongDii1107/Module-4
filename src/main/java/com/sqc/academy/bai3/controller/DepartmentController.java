package com.sqc.academy.bai3.controller;

import com.sqc.academy.bai3.model.Department;
import com.sqc.academy.bai3.response.JsonResponse;
import com.sqc.academy.bai3.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @GetMapping
    public Object getAll() {
        List<Department> list = service.getAll();
        return JsonResponse.ok(list);
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable int id) {
        return JsonResponse.ok(service.getById(id));
    }

    @PostMapping
    public Object create(@RequestBody Department department) {
        return JsonResponse.created(service.create(department));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable int id, @RequestBody Department department) {
        return JsonResponse.ok(service.update(id, department));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        service.delete(id);
        return JsonResponse.noContent("Deleted successfully");
    }
}
