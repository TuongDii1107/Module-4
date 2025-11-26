//package com.sqc.academy.service;
//
//import com.sqc.academy.model.Student;
//import com.sqc.academy.repository.IStudentRepository;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//@AllArgsConstructor //Tiêm thông qua Constructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class StudentService implements IStudentService {
//    //bước 1: tiêm vào thuộc tính
//    //@Autowired
//    //private IStudentRepository studentRepository = new StudentRepository();
//    //bước 2: Tiêm vào constructor
//    private IStudentRepository studentRepository;
////    @Autowired
////    public void setStudentRepository(IStudentRepository studentRepository) {
////        this.studentRepository = studentRepository;
////    }
//    //Bước 3: Tiêm vào setter
////    private IStudentRepository studentRepository;
////
////    @Autowired
////    public void setStudentService(IStudentService studentService) {
////        this.studentRepository = studentRepository;
////    }
//
//    @Override
//    public List<Student> findAll() {
//        return studentRepository.findAll();
//    }
//    @Override
//    public Student findById(int id) {
//        return studentRepository.findById(id);
//    }
//    @Override
//    public Student save(Student student) {
//        return studentRepository.save(student);
//    }
//}
