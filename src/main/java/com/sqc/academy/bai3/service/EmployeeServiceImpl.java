package com.sqc.academy.bai3.service;

import com.sqc.academy.bai3.ErrorCode;
import com.sqc.academy.bai3.dto.EmployeeSearchRequest;
import com.sqc.academy.bai3.exception.ApiException;
import com.sqc.academy.bai3.model.Employee;
import com.sqc.academy.bai3.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor

public class EmployeeServiceImpl implements IEmployeeService{

    private final IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        return employeeRepository.search(request);
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(String id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        getById(id);
        employeeRepository.delete(id);
    }
}
