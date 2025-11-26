package com.sqc.academy.bai3.repository.repository.impl;

import com.sqc.academy.bai3.BaseRepository;
import com.sqc.academy.bai3.model.Department;
import com.sqc.academy.bai3.repository.IDepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Repository
public class DepartmentRepositoryImpl extends BaseRepository implements IDepartmentRepository {
    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Department> findById(int id) {
        String sql = "SELECT * FROM department WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Department(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Department department) {
        if (department.getId() > 0 && findById(department.getId()).isPresent()) {
            // update
            String sql = "UPDATE department SET name=? WHERE id=?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, department.getName());
                ps.setInt(2, department.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // insert
            String sql = "INSERT INTO department(name) VALUES(?)";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, department.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM department WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
