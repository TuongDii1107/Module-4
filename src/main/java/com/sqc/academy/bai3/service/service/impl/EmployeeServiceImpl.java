package com.sqc.academy.bai3.service.service.impl;

import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.exception.AppException;
import com.sqc.academy.bai3.exception.ErrorCode;
import com.sqc.academy.bai3.model.Employee;
import com.sqc.academy.bai3.repository.IDepartmentRepository;
import com.sqc.academy.bai3.repository.IEmployeeRepository;
import com.sqc.academy.bai3.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository repository;

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(String id) {
        return repository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public Employee create(Employee employee) {
        repository.save(employee);
        return employee;
    }

    @Override
    public Employee update(String id, Employee employee) {
        Employee existing = repository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employee.setId(id);
        repository.save(employee);
        return employee;
    }

    @Override
    public void delete(String id) {
        Employee existing = repository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        repository.delete(id);
    }

    @Override
    public List<Employee> search(String name, String dobFrom, String dobTo, String gender,
                                 String salaryRange, String phone, Integer departmentId) {
        return repository.search(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
    }
}
