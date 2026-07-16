package com.placement.portal.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.RecruiterRequestDTO;
import com.placement.portal.dto.RecruiterResponseDTO;
import com.placement.portal.service.RecruiterService;

@RestController
@RequestMapping("/api/recruiters")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(
            RecruiterService recruiterService) {

        this.recruiterService = recruiterService;
    }

    // ==========================================
    // Create Recruiter
    // ==========================================

    @PostMapping
    public ResponseEntity<RecruiterResponseDTO> createRecruiter(

            @Valid
            @RequestBody RecruiterRequestDTO requestDTO) {

        return new ResponseEntity<>(

                recruiterService.createRecruiter(requestDTO),

                HttpStatus.CREATED);
    }

    // ==========================================
    // Update Recruiter
    // ==========================================

    @PutMapping("/{recruiterId}")
    public ResponseEntity<RecruiterResponseDTO> updateRecruiter(

            @PathVariable Long recruiterId,

            @Valid
            @RequestBody RecruiterRequestDTO requestDTO) {

        return ResponseEntity.ok(

                recruiterService.updateRecruiter(
                        recruiterId,
                        requestDTO));
    }

    // ==========================================
    // Get Recruiter By ID
    // ==========================================

    @GetMapping("/{recruiterId}")
    public ResponseEntity<RecruiterResponseDTO> getRecruiterById(

            @PathVariable Long recruiterId) {

        return ResponseEntity.ok(

                recruiterService.getRecruiterById(recruiterId));
    }

    // ==========================================
    // Get All Recruiters
    // ==========================================

    @GetMapping
    public ResponseEntity<List<RecruiterResponseDTO>> getAllRecruiters() {

        return ResponseEntity.ok(

                recruiterService.getAllRecruiters());
    }

    // ==========================================
    // Delete Recruiter
    // ==========================================

    @DeleteMapping("/{recruiterId}")
    public ResponseEntity<String> deleteRecruiter(

            @PathVariable Long recruiterId) {

        recruiterService.deleteRecruiter(recruiterId);

        return ResponseEntity.ok(
                "Recruiter deleted successfully.");
    }
}