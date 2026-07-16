package com.placement.portal.mapper;

import com.placement.portal.dto.InterviewResponseDTO;
import com.placement.portal.entity.Interview;

public class InterviewMapper {

    public static InterviewResponseDTO toResponseDTO(Interview interview) {

        InterviewResponseDTO dto = new InterviewResponseDTO();

        dto.setId(interview.getId());

       dto.setApplicationId(
        interview.getStudentApplication().getId());

dto.setCompanyName(
        interview.getStudentApplication()
                .getPlacementDrive()
                .getCompany()
                .getCompanyName());

dto.setJobRole(
        interview.getStudentApplication()
                .getPlacementDrive()
                .getJobRole());

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