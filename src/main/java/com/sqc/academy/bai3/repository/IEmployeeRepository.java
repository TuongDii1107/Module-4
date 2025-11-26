package com.sqc.academy.bai3.repository;

import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository {
    List<Employee> findAll();
    Optional<Employee> findById(String id);
    void save(Employee employee);
    void delete(String id);
    List<Employee> search(String name, String dobFrom, String dobTo,
                          String gender, String salaryRange,
                          String phone, Integer departmentId);
}
