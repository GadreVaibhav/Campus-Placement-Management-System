package com.placement.portal.mapper;

import com.placement.portal.dto.JobResponseDTO;
import com.placement.portal.entity.Job;

public class JobMapper {

    private JobMapper() {
    }

    public static JobResponseDTO toResponseDTO(Job job) {

        JobResponseDTO dto = new JobResponseDTO();

        dto.setId(job.getId());
        dto.setJobTitle(job.getJobTitle());
        dto.setDescription(job.getDescription());
        dto.setPackageLpa(job.getPackageLpa());
        dto.setLocation(job.getLocation());
        dto.setEligibilityCgpa(job.getEligibilityCgpa());
        dto.setSkillsRequired(job.getSkillsRequired());
        dto.setLastDate(job.getLastDate());
        dto.setStatus(job.getStatus());
        dto.setCreatedAt(job.getCreatedAt());

        dto.setCompanyId(job.getCompany().getId());
        dto.setCompanyName(job.getCompany().getCompanyName());

        dto.setRecruiterId(job.getRecruiter().getId());
        dto.setRecruiterName(job.getRecruiter().getName());

        return dto;
    }
}