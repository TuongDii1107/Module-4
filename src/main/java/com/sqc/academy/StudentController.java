package com.sqc.academy;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Linh", 2),
                    new Student(2, "Lợi", 3)
            )
    );

   // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }
    @GetMapping("/{id}")
    public Student getByID(@PathVariable("id") Integer id) {
        for (Student student : students) {
            if(student.getID() == id) {
                return student;
            }
        }
        return null;
    }
//    @RequestMapping(value = "/students", method = RequestMethod.POST )
    @PostMapping
    public List<Student> save(@RequestBody Student student) {
        student.setID((int) (Math.random() * 10000) + 1);
        students.add(student);
        return students;
    }
}
