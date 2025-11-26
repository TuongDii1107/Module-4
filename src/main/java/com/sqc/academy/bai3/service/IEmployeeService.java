package com.sqc.academy.bai3.service;

import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAll();
    Employee getById(String id);
    Employee create(Employee employee);
    Employee update(String id, Employee employee);
    void delete(String id);
    List<Employee> search(String name, String dobFrom, String dobTo,
                          String gender, String salaryRange,
                          String phone, Integer departmentId);
}
