package com.placement.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<UpcomingInterviewDTO> getUpcomingInterviews() {

        return recruiterInterviewService.getUpcomingInterviews();
    }
}