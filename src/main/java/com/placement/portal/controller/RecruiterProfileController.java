package com.placement.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.RecruiterProfileRequestDTO;
import com.placement.portal.dto.RecruiterProfileResponseDTO;
import com.placement.portal.service.RecruiterProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recruiter/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterProfileController {

    private final RecruiterProfileService recruiterProfileService;

    public RecruiterProfileController(
            RecruiterProfileService recruiterProfileService) {

        this.recruiterProfileService = recruiterProfileService;
    }

    // ==========================================
    // Get Logged-in Recruiter Profile
    // ==========================================

    @GetMapping
    public ResponseEntity<RecruiterProfileResponseDTO> getProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                recruiterProfileService.getProfile(email));
    }

    // ==========================================
    // Update Logged-in Recruiter Profile
    // ==========================================

    @PutMapping
    public ResponseEntity<RecruiterProfileResponseDTO> updateProfile(
            Authentication authentication,

            @Valid
            @RequestBody RecruiterProfileRequestDTO requestDTO) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                recruiterProfileService.updateProfile(
                        email,
                        requestDTO));
    }
}