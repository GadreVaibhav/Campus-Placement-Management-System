package com.placement.portal.service;

import org.springframework.data.domain.Page;

import com.placement.portal.dto.CompanyRequestDTO;
import com.placement.portal.dto.CompanyResponseDTO;

public interface CompanyService {

    // ==========================================
    // Create Company
    // ==========================================

    CompanyResponseDTO createCompany(
            CompanyRequestDTO requestDTO);

    // ==========================================
    // Update Company
    // ==========================================

    CompanyResponseDTO updateCompany(
            Long companyId,
            CompanyRequestDTO requestDTO);

    // ==========================================
    // Get Company By ID
    // ==========================================

    CompanyResponseDTO getCompanyById(
            Long companyId);

    // ==========================================
    // Get All Companies
    // ==========================================

    Page<CompanyResponseDTO> getAllCompanies(

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Search Companies
    // ==========================================

    Page<CompanyResponseDTO> searchCompanies(

            String companyName,

            String industry,

            String location,

            Boolean isActive,

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Delete Company
    // ==========================================

    void deleteCompany(Long companyId);
}