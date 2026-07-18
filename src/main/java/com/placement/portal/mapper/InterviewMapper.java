package com.placement.portal.mapper;

import com.placement.portal.dto.InterviewResponseDTO;
import com.placement.portal.entity.Interview;

public class InterviewMapper {

    public static InterviewResponseDTO toResponseDTO(Interview interview) {

        InterviewResponseDTO dto = new InterviewResponseDTO();

        dto.setId(interview.getId());

         dto.setApplicationId(
        interview.getApplication().getId());

        dto.setCompanyName(
                interview.getApplication()
                        .getJob()
                        .getCompany()
                        .getCompanyName());

        dto.setJobRole(
                interview.getApplication()
                        .getJob()
                        .getJobTitle());

        dto.setInterviewTime(
                interview.getInterviewTime());

        dto.setInterviewMode(
                interview.getInterviewMode());

        dto.setInterviewerName(
                interview.getInterviewerName());

        dto.setFeedback(
                interview.getFeedback());

        return dto;
    }

}