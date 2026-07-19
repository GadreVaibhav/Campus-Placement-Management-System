package com.placement.portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.RecruiterDashboardResponseDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.service.RecruiterDashboardService;

@RestController
@RequestMapping("/api/recruiter/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterDashboardController {

    private final RecruiterDashboardService recruiterDashboardService;

    public RecruiterDashboardController(
            RecruiterDashboardService recruiterDashboardService) {

        this.recruiterDashboardService = recruiterDashboardService;
    }

    @GetMapping
    public ResponseEntity<RecruiterDashboardResponseDTO> getDashboard() {

        return ResponseEntity.ok(
                recruiterDashboardService.getDashboard());
    }

    @GetMapping("/placement-drives")
public ResponseEntity<List<PlacementDriveResponseDTO>>
getRecentPlacementDrives() {

    return ResponseEntity.ok(
            recruiterDashboardService.getRecentPlacementDrives());

}
}