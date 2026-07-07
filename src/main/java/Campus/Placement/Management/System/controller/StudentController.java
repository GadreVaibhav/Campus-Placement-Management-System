package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.model.Student;
import Campus.Placement.Management.System.model.User;
import Campus.Placement.Management.System.repository.StudentRepository;
import Campus.Placement.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000") // ADD THIS LINE
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/profile/{userId}")
    public String updateProfile(@PathVariable Long userId, @RequestBody Student studentDetails) {
        // ... your existing logic
        return "Profile updated successfully";
    }
}