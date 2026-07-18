package com.placement.portal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.placement.portal.dto.JobRequestDTO;
import com.placement.portal.dto.JobResponseDTO;

public interface JobService {

    // ==========================================
    // Create Job
    // ==========================================

    JobResponseDTO createJob(
            JobRequestDTO requestDTO);

    // ==========================================
    // Update Job
    // ==========================================

    JobResponseDTO updateJob(
            Long jobId,
            JobRequestDTO requestDTO);

    // ==========================================
    // Get Job By ID
    // ==========================================

    JobResponseDTO getJobById(
            Long jobId);

    // ==========================================
    // Get All Jobs
    // ==========================================

    Page<JobResponseDTO> getAllJobs(

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Search Jobs
    // ==========================================

    Page<JobResponseDTO> searchJobs(

            String jobTitle,

            String location,

            String status,

            Long companyId,

            Long recruiterId,

            Float eligibilityCgpa,

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Delete Job
    // ==========================================

    void deleteJob(
            Long jobId);

            // ==========================================
// Student - Available Jobs
// ==========================================

List<JobResponseDTO> getAvailableJobs(String studentEmail);
}