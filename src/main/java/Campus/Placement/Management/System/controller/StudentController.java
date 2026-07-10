package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.dto.StudentRequestDTO;
import Campus.Placement.Management.System.dto.StudentResponseDTO;
import Campus.Placement.Management.System.repository.UserRepository;
import Campus.Placement.Management.System.service.StudentService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
    public class StudentController {

        private final StudentService studentService;

        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }
    @PutMapping("/profile/{userId}")
    public ResponseEntity<StudentResponseDTO> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody StudentRequestDTO requestDTO) {

        StudentResponseDTO response =
                studentService.updateProfile(userId, requestDTO);

        return ResponseEntity.ok(response);
    }
}