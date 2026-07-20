package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.dto.RecruiterDashboardResponseDTO;

public interface RecruiterDashboardService {

    RecruiterDashboardResponseDTO getDashboard(String recruiterEmail);

    List<PlacementDriveResponseDTO> getRecentPlacementDrives(String recruiterEmail);

}