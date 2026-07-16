package com.placement.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.CompanyRequestDTO;
import com.placement.portal.dto.CompanyResponseDTO;
import com.placement.portal.entity.Company;
import com.placement.portal.exception.CompanyNotFoundException;
import com.placement.portal.mapper.CompanyMapper;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.service.CompanyService;
import com.placement.portal.specification.CompanySpecification;

@Service
public class CompanyServiceImpl implements CompanyService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(CompanyServiceImpl.class);

    // ==========================================
    // Repository
    // ==========================================

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(
            CompanyRepository companyRepository) {

        this.companyRepository = companyRepository;
    }

    // ==========================================
    // Create Company
    // ==========================================

    @Override
    public CompanyResponseDTO createCompany(
            CompanyRequestDTO requestDTO) {

        logger.info("Creating company: {}",
                requestDTO.getCompanyName());

        if (companyRepository.existsByCompanyName(
                requestDTO.getCompanyName())) {

            logger.error("Company already exists.");

            throw new IllegalArgumentException(
                    "Company name already exists.");
        }

        if (companyRepository.existsByEmail(
                requestDTO.getEmail())) {

            logger.error("Email already exists.");

            throw new IllegalArgumentException(
                    "Company email already exists.");
        }

        Company company = new Company();

        company.setCompanyName(requestDTO.getCompanyName());
        company.setEmail(requestDTO.getEmail());
        company.setWebsite(requestDTO.getWebsite());
        company.setIndustry(requestDTO.getIndustry());
        company.setLocation(requestDTO.getLocation());
        company.setDescription(requestDTO.getDescription());
        company.setContactPerson(requestDTO.getContactPerson());
        company.setContactNumber(requestDTO.getContactNumber());
        company.setIsActive(true);

        Company savedCompany =
                companyRepository.save(company);

        logger.info(
                "Company created successfully. ID: {}",
                savedCompany.getId());

        return CompanyMapper.toResponseDTO(savedCompany);
    }

        // ==========================================
    // Update Company
    // ==========================================

    @Override
    public CompanyResponseDTO updateCompany(
            Long companyId,
            CompanyRequestDTO requestDTO) {

        logger.info("Updating company ID: {}", companyId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> {

                    logger.error("Company not found with ID: {}", companyId);

                    return new CompanyNotFoundException(
                            "Company not found with ID: " + companyId);
                });

        // Check duplicate company name
        companyRepository.findByCompanyName(requestDTO.getCompanyName())
                .ifPresent(existingCompany -> {

                    if (!existingCompany.getId().equals(companyId)) {

                        logger.error("Company name already exists.");

                        throw new IllegalArgumentException(
                                "Company name already exists.");
                    }
                });

        // Check duplicate email
        companyRepository.findByEmail(requestDTO.getEmail())
                .ifPresent(existingCompany -> {

                    if (!existingCompany.getId().equals(companyId)) {

                        logger.error("Company email already exists.");

                        throw new IllegalArgumentException(
                                "Company email already exists.");
                    }
                });

        company.setCompanyName(requestDTO.getCompanyName());
        company.setEmail(requestDTO.getEmail());
        company.setWebsite(requestDTO.getWebsite());
        company.setIndustry(requestDTO.getIndustry());
        company.setLocation(requestDTO.getLocation());
        company.setDescription(requestDTO.getDescription());
        company.setContactPerson(requestDTO.getContactPerson());
        company.setContactNumber(requestDTO.getContactNumber());

        Company updatedCompany = companyRepository.save(company);

        logger.info("Company updated successfully.");

        return CompanyMapper.toResponseDTO(updatedCompany);
    }

    // ==========================================
    // Get Company By ID
    // ==========================================

    @Override
    public CompanyResponseDTO getCompanyById(
            Long companyId) {

        logger.info("Fetching company ID: {}", companyId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> {

                    logger.error("Company not found with ID: {}", companyId);

                    return new CompanyNotFoundException(
                            "Company not found with ID: " + companyId);
                });

        logger.info("Company fetched successfully.");

        return CompanyMapper.toResponseDTO(company);
    }

        // ==========================================
    // Get All Companies
    // ==========================================

    @Override
    public Page<CompanyResponseDTO> getAllCompanies(

            int page,

            int size,

            String sortBy,

            String direction) {

        logger.info(
                "Fetching companies | page={} size={} sortBy={} direction={}",
                page,
                size,
                sortBy,
                direction);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Company> companies =
                companyRepository.findAll(pageable);

        logger.info(
                "Total companies found: {}",
                companies.getTotalElements());

        return companies.map(CompanyMapper::toResponseDTO);
    }

        // ==========================================
    // Search Companies
    // ==========================================

    @Override
    public Page<CompanyResponseDTO> searchCompanies(

            String companyName,

            String industry,

            String location,

            Boolean isActive,

            int page,

            int size,

            String sortBy,

            String direction) {

        logger.info(
                "Searching companies | companyName={} industry={} location={} active={}",
                companyName,
                industry,
                location,
                isActive);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Company> specification =

                Specification.where(
                        CompanySpecification.hasCompanyName(companyName))

                        .and(
                                CompanySpecification.hasIndustry(industry))

                        .and(
                                CompanySpecification.hasLocation(location))

                        .and(
                                CompanySpecification.isActive(isActive));

        Page<Company> companies =
                companyRepository.findAll(specification, pageable);

        logger.info(
                "Search completed successfully. {} companies found.",
                companies.getTotalElements());

        return companies.map(CompanyMapper::toResponseDTO);
    }

        // ==========================================
    // Delete Company
    // ==========================================

    @Override
    public void deleteCompany(Long companyId) {

        logger.info(
                "Deleting company ID: {}",
                companyId);

        Company company = companyRepository.findById(companyId)

                .orElseThrow(() -> {

                    logger.error(
                            "Company not found with ID: {}",
                            companyId);

                    return new CompanyNotFoundException(
                            "Company not found with ID: "
                                    + companyId);
                });

        companyRepository.delete(company);

        logger.info("Company deleted successfully.");
    }

}

