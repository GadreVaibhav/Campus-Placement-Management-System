package com.placement.portal.mapper;

import com.placement.portal.dto.CompanyResponseDTO;
import com.placement.portal.entity.Company;

public class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyResponseDTO toResponseDTO(Company company) {

        CompanyResponseDTO dto = new CompanyResponseDTO();

        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setEmail(company.getEmail());
        dto.setWebsite(company.getWebsite());
        dto.setIndustry(company.getIndustry());
        dto.setLocation(company.getLocation());
        dto.setDescription(company.getDescription());
        dto.setContactPerson(company.getContactPerson());
        dto.setContactNumber(company.getContactNumber());
        dto.setIsActive(company.getIsActive());
        dto.setCreatedAt(company.getCreatedAt());

        return dto;
    }
}