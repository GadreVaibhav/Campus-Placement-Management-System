package com.placement.portal.controller;

import com.placement.portal.dto.RecruiterDashboardResponseDTO;
import com.placement.portal.service.RecruiterDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiter/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterDashboardController {

    private final RecruiterDashboardService dashboardService;

    public RecruiterDashboardController(
            RecruiterDashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<RecruiterDashboardResponseDTO> getDashboard() {

        return ResponseEntity.ok(
                dashboardService.getDashboard());
    }
}