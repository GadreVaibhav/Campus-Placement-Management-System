package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.RecruiterInterviewRequestDTO;
import com.placement.portal.dto.RecruiterInterviewResponseDTO;

public interface RecruiterInterviewManagementService {

    List<RecruiterInterviewResponseDTO> getRecruiterInterviews(
            String recruiterEmail);

    RecruiterInterviewResponseDTO scheduleInterview(
            RecruiterInterviewRequestDTO requestDTO,
            String recruiterEmail);

    RecruiterInterviewResponseDTO updateInterview(
            Long interviewId,
            RecruiterInterviewRequestDTO requestDTO);

    void deleteInterview(Long interviewId);

}