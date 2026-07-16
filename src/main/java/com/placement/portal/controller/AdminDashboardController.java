package com.placement.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.AdminDashboardResponseDTO;
import com.placement.portal.service.AdminDashboardService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(
            AdminDashboardService adminDashboardService) {

        this.adminDashboardService = adminDashboardService;
    }

    // ==========================================
    // Dashboard Statistics
    // ==========================================

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardResponseDTO> getDashboardStatistics() {

        return ResponseEntity.ok(
                adminDashboardService.getDashboardStatistics());

    }

}