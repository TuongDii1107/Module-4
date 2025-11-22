package com.sqc.academy.bai3.repository;

import com.sqc.academy.bai3.Gender;
import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.model.Employee;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository{
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepositoryImpl() {
        employees.add(new Employee(UUID.randomUUID().toString(), "Nguyen Van A",
                LocalDate.of(1990,1,1), Gender.MALE,5000,"0123",null));
    }

    @Override
    public List<Employee> findAll() { return employees; }

    @Override
    public Optional<Employee> findById(String id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID().toString());
            employees.add(employee);
        } else {
            delete(employee.getId());
            employees.add(employee);
        }
        return employee;
    }

    @Override
    public void delete(String id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<Employee> search(EmployeeSearchRequest req) {
        return employees.stream()
                .filter(e -> req.getName() == null || e.getName().contains(req.getName()))
                .filter(e -> req.getPhone() == null || e.getPhone().contains(req.getPhone()))
                .filter(e -> req.getGender() == null || e.getGender() == req.getGender())
                .collect(Collectors.toList());
    }
}
