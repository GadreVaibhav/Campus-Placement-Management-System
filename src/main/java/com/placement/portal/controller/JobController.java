package com.placement.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.JobRequestDTO;
import com.placement.portal.dto.JobResponseDTO;
import com.placement.portal.service.JobService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // ============================
    // Create Job
    // ============================

    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(
            @Valid @RequestBody JobRequestDTO requestDTO) {

        return new ResponseEntity<>(
                jobService.createJob(requestDTO),
                HttpStatus.CREATED);
    }

    // ============================
    // Update Job
    // ============================

    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable Long jobId,
            @Valid @RequestBody JobRequestDTO requestDTO) {

        return ResponseEntity.ok(
                jobService.updateJob(jobId, requestDTO));
    }

    // ============================
    // Get Job By ID
    // ============================

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> getJobById(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(
                jobService.getJobById(jobId));
    }

    // ============================
    // Get All Jobs
    // ============================

    @GetMapping
    public ResponseEntity<Page<JobResponseDTO>> getAllJobs(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                jobService.getAllJobs(
                        page,
                        size,
                        sortBy,
                        direction));
    }

    // ============================
    // Search Jobs
    // ============================

   @GetMapping("/search")
public ResponseEntity<Page<JobResponseDTO>> searchJobs(

        @RequestParam(required = false) String jobTitle,

        @RequestParam(required = false) String location,

        @RequestParam(required = false) String status,

        @RequestParam(required = false) Long companyId,

        @RequestParam(required = false) Long recruiterId,

        @RequestParam(required = false) Float eligibilityCgpa,

        @RequestParam(defaultValue = "0") int page,

        @RequestParam(defaultValue = "5") int size,

        @RequestParam(defaultValue = "id") String sortBy,

        @RequestParam(defaultValue = "asc") String direction) {

    return ResponseEntity.ok(

            jobService.searchJobs(

                    jobTitle,

                    location,

                    status,

                    companyId,

                    recruiterId,

                    eligibilityCgpa,

                    page,

                    size,

                    sortBy,

                    direction));
}

// ============================
// Available Jobs for Student
// ============================

@GetMapping("/available")
public ResponseEntity<List<JobResponseDTO>> getAvailableJobs(
        Authentication authentication) {

    System.out.println(authentication.getName());

    return ResponseEntity.ok(
            jobService.getAvailableJobs(authentication.getName())
    );
}

    // ============================
    // Delete Job
    // ============================

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long jobId) {

        jobService.deleteJob(jobId);

        return ResponseEntity.ok(
                "Job deleted successfully.");
    }

    
}