package Campus.Placement.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // Add this import
import org.springframework.web.bind.annotation.RequestBody; // Add this import
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. GET Route: Fetches data from DB to browser
    @GetMapping("/api/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. POST Route: Receives new student data and saves it to DB
    @PostMapping("/api/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
}