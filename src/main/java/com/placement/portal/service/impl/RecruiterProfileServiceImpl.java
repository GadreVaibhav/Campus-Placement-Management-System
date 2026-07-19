package com.placement.portal.service.impl;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.RecruiterProfileRequestDTO;
import com.placement.portal.dto.RecruiterProfileResponseDTO;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.RecruiterProfileService;

@Service
public class RecruiterProfileServiceImpl
        implements RecruiterProfileService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterProfileServiceImpl(
            RecruiterRepository recruiterRepository) {

        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public RecruiterProfileResponseDTO getProfile(String email) {
System.out.println("JWT Email = " + email);
        Recruiter recruiter = recruiterRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));

        RecruiterProfileResponseDTO dto =
                new RecruiterProfileResponseDTO();

        dto.setId(recruiter.getId());
        dto.setName(recruiter.getName());
        dto.setEmail(recruiter.getEmail());
        dto.setDesignation(recruiter.getDesignation());
        dto.setPhone(recruiter.getPhone());
        dto.setCompanyName(recruiter.getCompany().getCompanyName());
        dto.setIsActive(recruiter.getIsActive());

        return dto;
    }

    @Override
    public RecruiterProfileResponseDTO updateProfile(
            String email,
            RecruiterProfileRequestDTO requestDTO) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));

        recruiter.setName(requestDTO.getName());
        recruiter.setDesignation(requestDTO.getDesignation());
        recruiter.setPhone(requestDTO.getPhone());

        recruiterRepository.save(recruiter);

        RecruiterProfileResponseDTO dto =
                new RecruiterProfileResponseDTO();

        dto.setId(recruiter.getId());
        dto.setName(recruiter.getName());
        dto.setEmail(recruiter.getEmail());
        dto.setDesignation(recruiter.getDesignation());
        dto.setPhone(recruiter.getPhone());
        dto.setCompanyName(recruiter.getCompany().getCompanyName());
        dto.setIsActive(recruiter.getIsActive());

        return dto;
    }

    
}