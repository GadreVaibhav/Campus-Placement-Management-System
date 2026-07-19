package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.RecruiterDashboardResponseDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;

public interface RecruiterDashboardService {

    RecruiterDashboardResponseDTO getDashboard();

    List<PlacementDriveResponseDTO> getRecentPlacementDrives();
}