create database student_management;
use student_management;

create table student (
                         id int primary key auto_increment,
                         name char(45) not null,
                         score double
);

insert into student(name, score) VALUE ('Loi', 2.0);
insert into student(name, score) VALUE ('Linh', 2.5);

select id, name, score from student;
select id, name, score from student where id = ?;
insert into student(name, score) VALUE (?, ?);