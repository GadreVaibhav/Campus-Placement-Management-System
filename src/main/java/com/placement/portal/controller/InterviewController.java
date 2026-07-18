package com.placement.portal.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import com.placement.portal.dto.InterviewRequestDTO;
import com.placement.portal.dto.InterviewResponseDTO;
import com.placement.portal.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(
            InterviewService interviewService) {

        this.interviewService = interviewService;
    }

    // ==========================================
    // Schedule Interview
    // ==========================================

    @PostMapping("/{applicationId}")
    public ResponseEntity<InterviewResponseDTO> scheduleInterview(

            @PathVariable Long applicationId,

            @Valid
            @RequestBody InterviewRequestDTO requestDTO) {

        return new ResponseEntity<>(

                interviewService.scheduleInterview(

                        applicationId,

                        requestDTO),

                HttpStatus.CREATED);
    }

    // ==========================================
    // Get Interview By Application
    // ==========================================

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<InterviewResponseDTO> getInterview(

            @PathVariable Long applicationId) {

        return ResponseEntity.ok(

                interviewService.getInterviewByApplication(

                        applicationId));
    }

    @GetMapping("/exists/{applicationId}")
public ResponseEntity<Boolean> interviewExists(
        @PathVariable Long applicationId) {

    return ResponseEntity.ok(
            interviewService.interviewExists(applicationId));

}

// ==========================================
// Get All Interviews
// ==========================================

@GetMapping
public ResponseEntity<List<InterviewResponseDTO>>
getAllInterviews() {

    return ResponseEntity.ok(

            interviewService.getAllInterviews());

}

// ==========================================
// Update Interview
// ==========================================

@PutMapping("/{interviewId}")
public ResponseEntity<InterviewResponseDTO>
updateInterview(

        @PathVariable Long interviewId,

        @Valid
        @RequestBody InterviewRequestDTO requestDTO) {

    return ResponseEntity.ok(

            interviewService.updateInterview(

                    interviewId,

                    requestDTO));

}

// ==========================================
// Delete Interview
// ==========================================

@DeleteMapping("/{interviewId}")
public ResponseEntity<String> deleteInterview(

        @PathVariable Long interviewId) {

    interviewService.deleteInterview(interviewId);

    return ResponseEntity.ok(
            "Interview deleted successfully.");

}
// ==========================================
// My Interviews
// ==========================================

@GetMapping("/my")
public ResponseEntity<List<InterviewResponseDTO>>
getMyInterviews(Authentication authentication) {

    String email = authentication.getName();

    return ResponseEntity.ok(

            interviewService.getMyInterviews(email)

    );
}
}