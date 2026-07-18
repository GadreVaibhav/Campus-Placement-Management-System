package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.ApplicationResponseDTO;

public interface ApplicationService {

    ApplicationResponseDTO applyJob(String studentEmail, Long jobId);

    List<ApplicationResponseDTO> getMyApplications(String studentEmail);

    List<ApplicationResponseDTO> getMyRecentApplications(String studentEmail);

    List<ApplicationResponseDTO> getAllApplications();

    ApplicationResponseDTO getApplicationById(Long applicationId);

    ApplicationResponseDTO updateApplicationStatus(
            Long applicationId,
            String status);

    void deleteApplication(Long applicationId);

    List<ApplicationResponseDTO> getRecentApplications();

}