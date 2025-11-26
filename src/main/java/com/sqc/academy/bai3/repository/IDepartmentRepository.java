package com.sqc.academy.bai3.repository;

import com.sqc.academy.bai3.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository {
    List<Department> findAll();
    Optional<Department> findById(int id);
    void save(Department department);
    void delete(int id);
}
