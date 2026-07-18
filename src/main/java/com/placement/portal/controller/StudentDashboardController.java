package com.placement.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.StudentDashboardResponseDTO;
import com.placement.portal.service.StudentDashboardService;

@RestController
@RequestMapping("/api/student-dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentDashboardController {

    private final StudentDashboardService studentDashboardService;

    public StudentDashboardController(StudentDashboardService studentDashboardService) {
        this.studentDashboardService = studentDashboardService;
    }

    @GetMapping
    public ResponseEntity<StudentDashboardResponseDTO> getDashboard(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                studentDashboardService.getStudentDashboard(email));
    }
}