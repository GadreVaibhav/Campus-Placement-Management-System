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

    List<InterviewResponseDTO> getAllInterviews();

    InterviewResponseDTO updateInterview(
            Long interviewId,
            InterviewRequestDTO requestDTO);

    void deleteInterview(Long interviewId);

    // NEW
    List<InterviewResponseDTO> getMyInterviews(String studentEmail);

}