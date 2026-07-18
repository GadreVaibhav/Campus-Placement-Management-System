package com.placement.portal.service;

import com.placement.portal.dto.StudentDashboardResponseDTO;

public interface StudentDashboardService {

    StudentDashboardResponseDTO getStudentDashboard(String email);

}