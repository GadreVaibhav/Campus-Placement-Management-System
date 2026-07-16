package com.placement.portal.mapper;

import com.placement.portal.dto.RecruiterResponseDTO;
import com.placement.portal.entity.Recruiter;

public class RecruiterMapper {

    private RecruiterMapper() {
    }

    public static RecruiterResponseDTO toResponseDTO(
            Recruiter recruiter) {

        RecruiterResponseDTO dto =
                new RecruiterResponseDTO();

        dto.setId(recruiter.getId());
        dto.setName(recruiter.getName());
        dto.setEmail(recruiter.getEmail());
        dto.setDesignation(recruiter.getDesignation());
        dto.setPhone(recruiter.getPhone());
        dto.setIsActive(recruiter.getIsActive());
        dto.setCreatedAt(recruiter.getCreatedAt());

        dto.setCompanyId(
                recruiter.getCompany().getId());

        dto.setCompanyName(
                recruiter.getCompany().getCompanyName());

        return dto;
    }
}