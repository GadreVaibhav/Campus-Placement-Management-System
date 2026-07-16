package com.placement.portal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.placement.portal.dto.RecruiterRequestDTO;
import com.placement.portal.dto.RecruiterResponseDTO;
import com.placement.portal.entity.Company;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.exception.CompanyNotFoundException;
import com.placement.portal.mapper.RecruiterMapper;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.RecruiterService;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(RecruiterServiceImpl.class);

    // ==========================================
    // Repositories
    // ==========================================

    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;

    public RecruiterServiceImpl(
            RecruiterRepository recruiterRepository,
            CompanyRepository companyRepository) {

        this.recruiterRepository = recruiterRepository;
        this.companyRepository = companyRepository;
    }

    // ==========================================
    // Create Recruiter
    // ==========================================

    @Override
    public RecruiterResponseDTO createRecruiter(
            RecruiterRequestDTO requestDTO) {

        logger.info("Creating recruiter: {}", requestDTO.getEmail());

        if (recruiterRepository.existsByEmail(requestDTO.getEmail())) {

            throw new IllegalArgumentException(
                    "Recruiter email already exists.");
        }

        Company company = companyRepository.findById(requestDTO.getCompanyId())
                .orElseThrow(() ->

                        new CompanyNotFoundException(
                                "Company not found with ID: "
                                        + requestDTO.getCompanyId()));

        Recruiter recruiter = new Recruiter();

        recruiter.setName(requestDTO.getName());
        recruiter.setEmail(requestDTO.getEmail());
        recruiter.setDesignation(requestDTO.getDesignation());
        recruiter.setPhone(requestDTO.getPhone());
        recruiter.setCompany(company);
        recruiter.setIsActive(true);

        Recruiter savedRecruiter =
                recruiterRepository.save(recruiter);

        logger.info("Recruiter created successfully.");

        return RecruiterMapper.toResponseDTO(savedRecruiter);
    }

    // ==========================================
    // Update Recruiter
    // ==========================================

    @Override
    public RecruiterResponseDTO updateRecruiter(
            Long recruiterId,
            RecruiterRequestDTO requestDTO) {

        Recruiter recruiter =
                recruiterRepository.findById(recruiterId)

                        .orElseThrow(() ->

                                new IllegalArgumentException(
                                        "Recruiter not found."));

        recruiterRepository.findByEmail(requestDTO.getEmail())

                .ifPresent(existingRecruiter -> {

                    if (!existingRecruiter.getId().equals(recruiterId)) {

                        throw new IllegalArgumentException(
                                "Recruiter email already exists.");
                    }
                });

        Company company =
                companyRepository.findById(requestDTO.getCompanyId())

                        .orElseThrow(() ->

                                new CompanyNotFoundException(
                                        "Company not found."));

        recruiter.setName(requestDTO.getName());
        recruiter.setEmail(requestDTO.getEmail());
        recruiter.setDesignation(requestDTO.getDesignation());
        recruiter.setPhone(requestDTO.getPhone());
        recruiter.setCompany(company);

        Recruiter updatedRecruiter =
                recruiterRepository.save(recruiter);

        return RecruiterMapper.toResponseDTO(updatedRecruiter);
    }

    // ==========================================
    // Get Recruiter By ID
    // ==========================================

    @Override
    public RecruiterResponseDTO getRecruiterById(
            Long recruiterId) {

        Recruiter recruiter =
                recruiterRepository.findById(recruiterId)

                        .orElseThrow(() ->

                                new IllegalArgumentException(
                                        "Recruiter not found."));

        return RecruiterMapper.toResponseDTO(recruiter);
    }

    // ==========================================
    // Get All Recruiters
    // ==========================================

    @Override
    public List<RecruiterResponseDTO> getAllRecruiters() {

        return recruiterRepository.findAll()

                .stream()

                .map(RecruiterMapper::toResponseDTO)

                .collect(Collectors.toList());
    }

    // ==========================================
    // Delete Recruiter
    // ==========================================

    @Override
    public void deleteRecruiter(Long recruiterId) {

        Recruiter recruiter =
                recruiterRepository.findById(recruiterId)

                        .orElseThrow(() ->

                                new IllegalArgumentException(
                                        "Recruiter not found."));

        recruiterRepository.delete(recruiter);

        logger.info("Recruiter deleted successfully.");
    }
}