package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.StudentApplicationRequestDTO;
import com.placement.portal.dto.StudentApplicationResponseDTO;

public interface StudentApplicationService {

    // ==========================================
    // Apply For Placement Drive
    // ==========================================

    StudentApplicationResponseDTO applyForDrive(
            StudentApplicationRequestDTO requestDTO);

    // ==========================================
    // Get Application By ID
    // ==========================================

    StudentApplicationResponseDTO getApplicationById(
            Long applicationId);

    // ==========================================
    // Get Applications Of Student
    // ==========================================

    List<StudentApplicationResponseDTO> getApplicationsByStudent(
            Long studentId);

    // ==========================================
    // Get All Applications
    // ==========================================

    List<StudentApplicationResponseDTO> getAllApplications();

    // ==========================================
    // Update Status
    // ==========================================

    StudentApplicationResponseDTO updateApplicationStatus(
            Long applicationId,
            String status);

    // ==========================================
    // Delete Application
    // ==========================================

    void deleteApplication(
            Long applicationId);
            List<StudentApplicationResponseDTO> getRecentApplications();
}