package com.sqc.academy.bai3.service;

import com.sqc.academy.bai3.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();
    Department getById(int id);
    Department create(Department department);
    Department update(int id, Department department);
    void delete(int id);
}
