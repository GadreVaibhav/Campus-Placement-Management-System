package com.placement.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.placement.portal.dto.ApplicationStatusCountDTO;
import com.placement.portal.dto.BranchCountDTO;
import com.placement.portal.dto.DashboardResponseDTO;
import com.placement.portal.service.DashboardService;
import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.service.StudentService;
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    private final DashboardService dashboardService;
    private final StudentService studentService;

    public DashboardController(

        DashboardService dashboardService,

        StudentService studentService) {

    this.dashboardService = dashboardService;

    this.studentService = studentService;
}

    @GetMapping("/admin")
    public ResponseEntity<DashboardResponseDTO> getAdminDashboard() {

        return ResponseEntity.ok(

                dashboardService.getAdminDashboard());
    }
    // ==========================================
    // Students By Branch
    // ==========================================

    @GetMapping("/admin/students-by-branch")
    public ResponseEntity<List<BranchCountDTO>> getStudentsByBranch() {

        return ResponseEntity.ok(
                dashboardService.getStudentsByBranch());

    }

    @GetMapping("/recent-students")
public ResponseEntity<List<StudentResponseDTO>> getRecentStudents() {

    return ResponseEntity.ok(

            studentService.getRecentStudents()

    );

}

@GetMapping("/admin/applications-by-status")
public ResponseEntity<List<ApplicationStatusCountDTO>>
getApplicationsByStatus() {

    return ResponseEntity.ok(

            dashboardService.getApplicationsByStatus());

}
}