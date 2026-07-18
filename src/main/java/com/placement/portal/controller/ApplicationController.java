package com.placement.portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    // ==========================================
    // Apply for Job
    // ==========================================

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<ApplicationResponseDTO> applyJob(

            Authentication authentication,

            @PathVariable Long jobId) {

        String email = authentication.getName();

        return ResponseEntity.ok(

                applicationService.applyJob(
                        email,
                        jobId)

        );
    }

    // ==========================================
    // My Applications
    // ==========================================

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponseDTO>>
            getMyApplications(

                    Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(

                applicationService.getMyApplications(
                        email)

        );
    }


@GetMapping("/{applicationId}")
public ResponseEntity<ApplicationResponseDTO>
getApplicationById(

        @PathVariable Long applicationId) {

    return ResponseEntity.ok(

            applicationService.getApplicationById(applicationId));

}
@PutMapping("/{applicationId}/status")
public ResponseEntity<ApplicationResponseDTO>
updateStatus(

        @PathVariable Long applicationId,

        @RequestParam String status) {

    return ResponseEntity.ok(

            applicationService.updateApplicationStatus(

                    applicationId,

                    status));

}
@DeleteMapping("/{applicationId}")
public ResponseEntity<String>
deleteApplication(

        @PathVariable Long applicationId) {

    applicationService.deleteApplication(applicationId);

    return ResponseEntity.ok("Application deleted successfully.");

}
@GetMapping("/recent")
public ResponseEntity<List<ApplicationResponseDTO>>
getRecentApplications() {

    return ResponseEntity.ok(

            applicationService.getRecentApplications());

}
@GetMapping("/my/recent")
public ResponseEntity<List<ApplicationResponseDTO>>
getMyRecentApplications(

        Authentication authentication) {

    String email = authentication.getName();

    return ResponseEntity.ok(

            applicationService.getMyRecentApplications(email));

}

@GetMapping
public ResponseEntity<List<ApplicationResponseDTO>>
getAllApplications() {
 System.out.println("===== GET ALL APPLICATIONS CONTROLLER HIT =====");
    return ResponseEntity.ok(
            applicationService.getAllApplications());

}



}