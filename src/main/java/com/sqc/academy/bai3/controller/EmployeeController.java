package com.sqc.academy.bai3.controller;

import com.sqc.academy.bai3.model.Employee;
import com.sqc.academy.bai3.response.JsonResponse;
import com.sqc.academy.bai3.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping
    public Object search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String dobFrom,
            @RequestParam(required = false) String dobTo,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer departmentId
    ) {
        List<Employee> list = service.search(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
        return JsonResponse.ok(list);
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable String id) {
        return JsonResponse.ok(service.getById(id));
    }

    @PostMapping
    public Object create(@RequestBody Employee employee) {
        return JsonResponse.created(service.create(employee));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id, @RequestBody Employee employee) {
        return JsonResponse.ok(service.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id) {
        service.delete(id);
        return JsonResponse.noContent("Deleted successfully");
    }
}
