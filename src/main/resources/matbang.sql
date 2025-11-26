CREATE DATABASE tmarketplace;
USE tmarketplace;

-- Bảng loại mặt bằng
CREATE TABLE loaimb
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO loaimb(name)
VALUES ('Văn phòng'),
       ('Cửa hàng'),
       ('Nhà ở');

-- Bảng mặt bằng
CREATE TABLE mb
(
    code       VARCHAR(50) PRIMARY KEY,
    name       VARCHAR(200) NOT NULL,
    address    VARCHAR(255),
    area       DOUBLE,
    loai_id    INT,
    rent       DECIMAL(15, 2),
    start_date DATE,
    FOREIGN KEY (loai_id) REFERENCES loaimb (id)
);

INSERT INTO mb (code, name, address, area, loai_id, rent, start_date)
VALUES
    ('MB001', 'Văn phòng 1', '123 Đường ABC, Đà Nẵng', 80, 1, 3300000, '2023-10-01'),
    ('MB002', 'Cửa hàng A', '456 Đường XYZ, Đà Nẵng', 100, 2, 4000000, '2023-10-15'),
    ('MB003', 'Nhà ở 1', '789 Đường DEF, Đà Nẵng', 120, 3, 5500000, '2023-10-20'),
    ('MB004', 'Văn phòng 2', '101 Đường GHI, Đà Nẵng', 60, 1, 2200000, '2023-11-05'),
    ('MB005', 'Cửa hàng B', '202 Đường KLM, Đà Nẵng', 90, 2, 3800000, '2023-11-10');
