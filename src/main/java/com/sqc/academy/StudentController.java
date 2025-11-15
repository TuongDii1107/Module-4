package com.sqc.academy;

import com.sqc.academy.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    Student.builder().ID(1).name("Linh").score(2.0).build(),
                    Student.builder().ID(2).name("Lợi").score(3.0).build()
            )
    );

   // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(ApiRespone.builder().data(students).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Student>> getByID(@PathVariable("id") Integer id) {
        for (Student student : students) {
            if(student.getID() == id) {
                //return ResponseEntity.status(HttpStatus.OK).body(student);
                return ResponseEntity.ok(ApiRespone.<Student>builder()
                        .code(20001)
                        .message("Get Data")
                        .data(student)
                        .build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_FOUND);

    }
//    @RequestMapping(value = "/students", method = RequestMethod.POST )
    @PostMapping
    public ResponseEntity<ApiRespone<Student>> save(@RequestBody Student student) {
        student.setID((int) (Math.random() * 10000) + 1);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiRespone.<Student>builder()
                        .data(student).build());
    }
}
