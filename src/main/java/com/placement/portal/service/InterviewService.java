package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.InterviewRequestDTO;
import com.placement.portal.dto.InterviewResponseDTO;

public interface InterviewService {

    InterviewResponseDTO scheduleInterview(
            Long applicationId,
            InterviewRequestDTO requestDTO);

    InterviewResponseDTO getInterviewByApplication(
            Long applicationId);

    boolean interviewExists(Long applicationId);
    // ==========================================
// Get All Interviews
// ==========================================

List<InterviewResponseDTO> getAllInterviews();

// ==========================================
// Update Interview
// ==========================================

InterviewResponseDTO updateInterview(
        Long interviewId,
        InterviewRequestDTO requestDTO);

// ==========================================
// Delete Interview
// ==========================================

void deleteInterview(Long interviewId);

}