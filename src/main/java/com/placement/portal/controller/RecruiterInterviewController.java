package com.placement.portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.placement.portal.dto.UpcomingInterviewDTO;
import com.placement.portal.service.RecruiterInterviewService;

@RestController
@RequestMapping("/api/recruiter/interviews")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterInterviewController {

    private final RecruiterInterviewService recruiterInterviewService;

    public RecruiterInterviewController(
            RecruiterInterviewService recruiterInterviewService) {

        this.recruiterInterviewService = recruiterInterviewService;
    }

   @GetMapping("/upcoming")
public ResponseEntity<List<UpcomingInterviewDTO>> getUpcomingInterviews(
        Authentication authentication) {

    String recruiterEmail = authentication.getName();

    return ResponseEntity.ok(

            recruiterInterviewService.getUpcomingInterviews(
                    recruiterEmail)

    );
}
}