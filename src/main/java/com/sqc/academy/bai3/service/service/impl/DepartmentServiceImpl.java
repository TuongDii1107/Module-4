package com.sqc.academy.bai3.service.service.impl;

import com.sqc.academy.bai3.exception.AppException;
import com.sqc.academy.bai3.exception.ErrorCode;
import com.sqc.academy.bai3.model.Department;
import com.sqc.academy.bai3.repository.IDepartmentRepository;
import com.sqc.academy.bai3.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository repository;

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
    }

    @Override
    public Department create(Department department) {
        repository.save(department);
        return department;
    }

    @Override
    public Department update(int id, Department department) {
        Department existing = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        department.setId(id);
        repository.save(department);
        return department;
    }

    @Override
    public void delete(int id) {
        Department existing = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        repository.delete(id);
    }
}
