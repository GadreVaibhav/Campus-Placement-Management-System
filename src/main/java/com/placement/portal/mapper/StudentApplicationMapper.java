package com.placement.portal.mapper;

import com.placement.portal.dto.StudentApplicationResponseDTO;
import com.placement.portal.entity.StudentApplication;

public class StudentApplicationMapper {

    private StudentApplicationMapper() {
    }

    public static StudentApplicationResponseDTO toResponseDTO(
            StudentApplication application) {

        StudentApplicationResponseDTO dto =
                new StudentApplicationResponseDTO();

        dto.setId(application.getId());

        dto.setStudentId(
                application.getStudent().getStudentId());

        dto.setStudentName(
                application.getStudent().getName());

        dto.setPlacementDriveId(
                application.getPlacementDrive().getId());

        dto.setCompanyName(
                application.getPlacementDrive()
                        .getCompany()
                        .getCompanyName());

        dto.setJobRole(
                application.getPlacementDrive()
                        .getJobRole());

        dto.setStatus(
                application.getStatus().name());

        dto.setApplicationDate(
                application.getApplicationDate());

        return dto;
    }
}