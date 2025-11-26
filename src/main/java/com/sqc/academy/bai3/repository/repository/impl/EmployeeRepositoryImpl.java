package com.sqc.academy.bai3.repository.repository.impl;

import com.sqc.academy.bai3.BaseRepository;
import com.sqc.academy.bai3.model.Employee;
import com.sqc.academy.bai3.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.sql.Date;

@Repository
public class EmployeeRepositoryImpl extends BaseRepository implements IEmployeeRepository {

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Employee> findById(String id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() != null && findById(employee.getId()).isPresent()) {

            String sql = "UPDATE employee SET name=?, dob=?, gender=?, salary=?, phone=?, department_id=? WHERE id=?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, employee.getName());
                ps.setDate(2, Date.valueOf(employee.getDob()));
                ps.setString(3, employee.getGender().name());
                ps.setBigDecimal(4, employee.getSalary());
                ps.setString(5, employee.getPhone());

                if (employee.getDepartmentId() != null) {
                    ps.setInt(6, employee.getDepartmentId());
                } else {
                    ps.setNull(6, Types.INTEGER);
                }

                ps.setString(7, employee.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {

            String sql = "INSERT INTO employee(id,name,dob,gender,salary,phone,department_id) VALUES(?,?,?,?,?,?,?)";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                String id = employee.getId() != null ? employee.getId() : UUID.randomUUID().toString();

                ps.setString(1, id);
                ps.setString(2, employee.getName());
                ps.setDate(3, Date.valueOf(employee.getDob()));
                ps.setString(4, employee.getGender().name());
                ps.setBigDecimal(5, employee.getSalary());
                ps.setString(6, employee.getPhone());

                if (employee.getDepartmentId() != null) {
                    ps.setInt(7, employee.getDepartmentId());
                } else {
                    ps.setNull(7, Types.INTEGER);
                }

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> search(String name, String dobFrom, String dobTo,
                                 String gender, String salaryRange,
                                 String phone, Integer departmentId) {

        List<Employee> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append("AND name LIKE ? ");
            params.add("%" + name + "%");
        }
        if (dobFrom != null && !dobFrom.isEmpty()) {
            sql.append("AND dob >= ? ");
            params.add(Date.valueOf(dobFrom));
        }
        if (dobTo != null && !dobTo.isEmpty()) {
            sql.append("AND dob <= ? ");
            params.add(Date.valueOf(dobTo));
        }
        if (gender != null && !gender.isEmpty()) {
            sql.append("AND gender = ? ");
            params.add(gender);
        }
        if (phone != null && !phone.isEmpty()) {
            sql.append("AND phone LIKE ? ");
            params.add("%" + phone + "%");
        }
        if (departmentId != null) {
            sql.append("AND department_id = ? ");
            params.add(departmentId);
        }
        if (salaryRange != null && !salaryRange.isEmpty()) {
            switch (salaryRange) {
                case "lt5":
                    sql.append("AND salary < 5000000 ");
                    break;
                case "5-10":
                    sql.append("AND salary BETWEEN 5000000 AND 10000000 ");
                    break;
                case "10-20":
                    sql.append("AND salary BETWEEN 10000000 AND 20000000 ");
                    break;
                case "gt20":
                    sql.append("AND salary > 20000000 ");
                    break;
            }
        }

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getString("id"));
        e.setName(rs.getString("name"));
        e.setDob(rs.getDate("dob").toLocalDate());
        e.setGender(Employee.Gender.valueOf(rs.getString("gender")));
        e.setSalary(rs.getBigDecimal("salary"));
        e.setPhone(rs.getString("phone"));

        int deptId = rs.getInt("department_id");
        e.setDepartmentId(rs.wasNull() ? null : deptId);

        return e;
    }
}
