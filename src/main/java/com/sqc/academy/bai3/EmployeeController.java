package com.sqc.academy.bai3;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeController() {
//        employeeList.add(new Employee("Nguyen Van A", LocalDate.of(1990, 1, 1), Gender.MALE, 5000, "0123456789"));
//        employeeList.add(new Employee("Tran Thi B", LocalDate.of(1992, 5, 10), Gender.FEMALE, 6000, "0987654321"));
//    }
    }

    // GET /employees - lấy tất cả nhân viên
    @GetMapping
    public List<Employee> getAll() {
        return employeeList;
    }

    // GET /employees/{id} - lấy nhân viên theo id
    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        Optional<Employee> emp = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        return emp.orElse(null);
    }

    // POST /employees - tạo nhân viên mới
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employeeList.add(employee);
        return employee;
    }

    // PUT /employees/{id} - cập nhật nhân viên
    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> empOpt = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            emp.setName(updatedEmployee.getName());
            emp.setDob(updatedEmployee.getDob());
            emp.setGender(updatedEmployee.getGender());
            emp.setSalary(updatedEmployee.getSalary());
            emp.setPhone(updatedEmployee.getPhone());
            return emp;
        }
        return null; // hoặc trả thông báo không tìm thấy
    }

    // DELETE /employees/{id} - xóa nhân viên
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        Optional<Employee> empOpt = employeeList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (empOpt.isPresent()) {
            employeeList.remove(empOpt.get());
            return "Xóa nhân viên thành công!";
        }
        return "Không tìm thấy nhân viên với id: " + id;
    }
}
