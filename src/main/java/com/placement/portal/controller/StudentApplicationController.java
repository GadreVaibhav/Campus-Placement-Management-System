package com.placement.portal.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.StudentApplicationRequestDTO;
import com.placement.portal.dto.StudentApplicationResponseDTO;
import com.placement.portal.service.StudentApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentApplicationController {

    private final StudentApplicationService studentApplicationService;

    public StudentApplicationController(
            StudentApplicationService studentApplicationService) {

        this.studentApplicationService = studentApplicationService;
    }

    // ==========================================
    // Apply For Placement Drive
    // ==========================================

    @PostMapping("/apply")
    public ResponseEntity<StudentApplicationResponseDTO> applyForDrive(

            @Valid
            @RequestBody StudentApplicationRequestDTO requestDTO) {

        return new ResponseEntity<>(

                studentApplicationService.applyForDrive(requestDTO),

                HttpStatus.CREATED);
    }

    // ==========================================
    // Get Application By ID
    // ==========================================

    @GetMapping("/{applicationId}")
    public ResponseEntity<StudentApplicationResponseDTO> getApplicationById(

            @PathVariable Long applicationId) {

        return ResponseEntity.ok(

                studentApplicationService.getApplicationById(applicationId));
    }

    // ==========================================
    // Get Student Applications
    // ==========================================

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentApplicationResponseDTO>>
            getApplicationsByStudent(

                    @PathVariable Long studentId) {

        return ResponseEntity.ok(

                studentApplicationService
                        .getApplicationsByStudent(studentId));
    }

    // ==========================================
    // Get All Applications
    // ==========================================

    @GetMapping
    public ResponseEntity<List<StudentApplicationResponseDTO>>
            getAllApplications() {

        return ResponseEntity.ok(

                studentApplicationService.getAllApplications());
    }

    // ==========================================
    // Update Status
    // ==========================================

   @PutMapping("/{applicationId}/status")
        public ResponseEntity<StudentApplicationResponseDTO> updateStatus(
                @PathVariable Long applicationId,
                @RequestParam String status) {

        return ResponseEntity.ok(
                studentApplicationService.updateApplicationStatus(
                        applicationId,
                        status));
        }

    // ==========================================
    // Delete Application
    // ==========================================

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(

            @PathVariable Long applicationId) {

        studentApplicationService.deleteApplication(applicationId);

        return ResponseEntity.ok(
                "Application deleted successfully.");
    }
    @GetMapping("/recent")
public ResponseEntity<List<StudentApplicationResponseDTO>>
getRecentApplications() {

    return ResponseEntity.ok(

            studentApplicationService.getRecentApplications()

    );
}
   
}