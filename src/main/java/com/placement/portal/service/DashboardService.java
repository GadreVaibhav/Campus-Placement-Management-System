package com.placement.portal.service;

import com.placement.portal.dto.DashboardResponseDTO;
import java.util.List;

import com.placement.portal.dto.ApplicationStatusCountDTO;
import com.placement.portal.dto.BranchCountDTO;
public interface DashboardService {

    DashboardResponseDTO getAdminDashboard();
    List<BranchCountDTO> getStudentsByBranch();
    List<ApplicationStatusCountDTO> getApplicationsByStatus();
}