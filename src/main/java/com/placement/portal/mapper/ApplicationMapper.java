package com.placement.portal.mapper;

import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.entity.Application;

public class ApplicationMapper {

    private ApplicationMapper() {
    }

    public static ApplicationResponseDTO toResponseDTO(
            Application application) {

        ApplicationResponseDTO dto = new ApplicationResponseDTO();

        dto.setId(application.getId());

        dto.setStudentId(
                application.getStudent().getStudentId());

        dto.setStudentName(
                application.getStudent().getName());

        dto.setJobId(
                application.getJob().getId());

        dto.setJobTitle(
                application.getJob().getJobTitle());

        dto.setCompanyName(
                application.getJob()
                        .getCompany()
                        .getCompanyName());

        dto.setPackageLpa(
        application.getJob().getPackageLpa());
        
        dto.setStatus(
                application.getStatus());

        dto.setAppliedAt(
                application.getAppliedAt());

        return dto;
    }
}