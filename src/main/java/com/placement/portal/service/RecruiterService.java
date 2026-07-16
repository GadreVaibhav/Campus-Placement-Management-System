package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.RecruiterRequestDTO;
import com.placement.portal.dto.RecruiterResponseDTO;

public interface RecruiterService {

    // ==========================================
    // Create Recruiter
    // ==========================================

    RecruiterResponseDTO createRecruiter(
            RecruiterRequestDTO requestDTO);

    // ==========================================
    // Update Recruiter
    // ==========================================

    RecruiterResponseDTO updateRecruiter(
            Long recruiterId,
            RecruiterRequestDTO requestDTO);

    // ==========================================
    // Get Recruiter By ID
    // ==========================================

    RecruiterResponseDTO getRecruiterById(
            Long recruiterId);

    // ==========================================
    // Get All Recruiters
    // ==========================================

    List<RecruiterResponseDTO> getAllRecruiters();

    // ==========================================
    // Delete Recruiter
    // ==========================================

    void deleteRecruiter(
            Long recruiterId);
}