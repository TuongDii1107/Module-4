-- Tạo database
CREATE DATABASE employee_management;
USE employee_management;

-- Bảng department
CREATE TABLE department
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Chèn dữ liệu vào bảng department
INSERT INTO department (id, name)
VALUES (1, 'Quản lý'),
       (2, 'Nhân sự'),
       (3, 'Kế toán');

-- Bảng employee
CREATE TABLE employee
(
    id            CHAR(36) PRIMARY KEY,
    name          VARCHAR(255)            NOT NULL,
    dob           DATE                    NOT NULL,
    gender        ENUM ('MALE', 'FEMALE') NOT NULL,
    salary        DECIMAL(15, 2)          NOT NULL,
    phone         VARCHAR(15),
    department_id INT,
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
            REFERENCES department (id)
);

-- Trigger tự tạo UUID nếu NULL
DELIMITER $$
CREATE TRIGGER before_insert_employee
    BEFORE INSERT
    ON employee
    FOR EACH ROW
BEGIN
    IF NEW.id IS NULL THEN
        SET NEW.id = UUID();
END IF;
END$$
DELIMITER ;

-- Chèn dữ liệu mẫu
INSERT INTO employee (id, name, dob, gender, salary, phone, department_id)
VALUES (UUID(), 'Hoàng Văn Hải', '1990-01-15', 'MALE', 15000000.00, '0975123542', 1);
