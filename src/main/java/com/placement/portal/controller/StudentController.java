package com.placement.portal.controller;

import jakarta.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.StudentRequestDTO;
import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.dto.StudentUpdateDTO;
import com.placement.portal.service.StudentService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;
        
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    
    // =====================================
    // Logged-in Student
    // =====================================

  @GetMapping("/me")
public ResponseEntity<StudentResponseDTO> getLoggedInStudent(
        Authentication authentication) {

    System.out.println("===== CONTROLLER HIT =====");
    System.out.println(authentication);
    System.out.println(authentication.getAuthorities());

    String email = authentication.getName();

    return ResponseEntity.ok(
            studentService.getLoggedInStudent(email)
    );
}

    // =====================================
    // Get Student By ID
    // =====================================

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                studentService.getStudentById(studentId)
        );
    }

    // =====================================
    // Update Profile
    // =====================================
@PutMapping("/me")
public ResponseEntity<StudentResponseDTO> updateMyProfile(
        Authentication authentication,
        @Valid @RequestBody StudentRequestDTO dto) {

    String email = authentication.getName();

    return ResponseEntity.ok(
            studentService.updateLoggedInStudent(email, dto)
    );
}

// =====================================
// Upload Resume
// =====================================

@PostMapping("/resume")
public ResponseEntity<StudentResponseDTO> uploadResume(

        Authentication authentication,

        @RequestParam("file") MultipartFile file) {

    String email = authentication.getName();

    return ResponseEntity.ok(

            studentService.uploadResume(email, file)

    );
}

// =====================================
// Download Resume
// =====================================

@GetMapping("/resume")
public ResponseEntity<Resource> downloadResume(

        Authentication authentication) {

    String email = authentication.getName();

    Resource resource = studentService.downloadResume(email);

    return ResponseEntity.ok()

            .contentType(MediaType.APPLICATION_PDF)

            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"resume.pdf\"")

            .body(resource);

}

// =====================================
// Delete Resume
// =====================================

@DeleteMapping("/resume")
public ResponseEntity<String> deleteResume(

        Authentication authentication) {

    String email = authentication.getName();

    studentService.deleteResume(email);

    return ResponseEntity.ok("Resume deleted successfully.");

}
    // =====================================
    // Get All Students
    // =====================================

    @GetMapping
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "studentId") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(

                studentService.getAllStudents(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    // =====================================
    // Search Students
    // =====================================

    @GetMapping("/search")
    public ResponseEntity<Page<StudentResponseDTO>> searchStudents(

            @RequestParam(required = false) String name,

            @RequestParam(required = false) String branch,

            @RequestParam(required = false) String skill,

            @RequestParam(required = false) Float cgpa,

            @RequestParam(required = false) Integer graduationYear,

            @RequestParam(required = false) Boolean placed,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "studentId") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(

                studentService.searchStudents(
                        name,
                        branch,
                        skill,
                        cgpa,
                        graduationYear,
                        placed,
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    // =====================================
    // Delete Student
    // =====================================

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long studentId) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.ok("Student deleted successfully.");
    }

   @PutMapping("/{studentId}")
public ResponseEntity<StudentResponseDTO> updateStudent(

        @PathVariable Long studentId,

        @Valid @RequestBody StudentUpdateDTO dto) {

    return ResponseEntity.ok(

            studentService.updateStudent(studentId, dto)

    );
}
    
}