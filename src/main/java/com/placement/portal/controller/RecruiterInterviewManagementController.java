package com.placement.portal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.RecruiterInterviewRequestDTO;
import com.placement.portal.dto.RecruiterInterviewResponseDTO;
import com.placement.portal.service.RecruiterInterviewManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recruiter/interview-management")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterInterviewManagementController {

    private final RecruiterInterviewManagementService recruiterInterviewManagementService;

    public RecruiterInterviewManagementController(
            RecruiterInterviewManagementService recruiterInterviewManagementService) {

        this.recruiterInterviewManagementService =
                recruiterInterviewManagementService;
    }

    // ==========================================
    // GET ALL INTERVIEWS
    // ==========================================

    @GetMapping
    public List<RecruiterInterviewResponseDTO> getRecruiterInterviews(

            Authentication authentication) {

        return recruiterInterviewManagementService
                .getRecruiterInterviews(
                        authentication.getName());
    }

    // ==========================================
    // CREATE
    // ==========================================

    @PostMapping
    public ResponseEntity<RecruiterInterviewResponseDTO> scheduleInterview(

            @Valid
            @RequestBody RecruiterInterviewRequestDTO requestDTO,

            Authentication authentication) {

        return new ResponseEntity<>(

                recruiterInterviewManagementService
                        .scheduleInterview(

                                requestDTO,

                                authentication.getName()),

                HttpStatus.CREATED);
    }

    // ==========================================
    // UPDATE
    // ==========================================

    @PutMapping("/{interviewId}")
    public RecruiterInterviewResponseDTO updateInterview(

            @PathVariable Long interviewId,

            @Valid
            @RequestBody RecruiterInterviewRequestDTO requestDTO) {

        return recruiterInterviewManagementService
                .updateInterview(
                        interviewId,
                        requestDTO);
    }

    // ==========================================
    // DELETE
    // ==========================================

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<String> deleteInterview(

            @PathVariable Long interviewId) {

        recruiterInterviewManagementService
                .deleteInterview(interviewId);

        return ResponseEntity.ok("Interview deleted successfully.");
    }
}