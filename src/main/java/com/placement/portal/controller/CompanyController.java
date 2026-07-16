package com.placement.portal.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.CompanyRequestDTO;
import com.placement.portal.dto.CompanyResponseDTO;
import com.placement.portal.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(
            CompanyService companyService) {

        this.companyService = companyService;
    }

    // ==========================================
    // Create Company
    // ==========================================

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(

            @Valid
            @RequestBody CompanyRequestDTO requestDTO) {

        return new ResponseEntity<>(

                companyService.createCompany(requestDTO),

                HttpStatus.CREATED);
    }

    // ==========================================
    // Update Company
    // ==========================================

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(

            @PathVariable Long companyId,

            @Valid
            @RequestBody CompanyRequestDTO requestDTO) {

        return ResponseEntity.ok(

                companyService.updateCompany(
                        companyId,
                        requestDTO));
    }

    // ==========================================
    // Get Company By ID
    // ==========================================

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(

            @PathVariable Long companyId) {

        return ResponseEntity.ok(

                companyService.getCompanyById(companyId));
    }

    // ==========================================
    // Get All Companies
    // ==========================================

    @GetMapping
    public ResponseEntity<Page<CompanyResponseDTO>> getAllCompanies(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(

                companyService.getAllCompanies(

                        page,

                        size,

                        sortBy,

                        direction));
    }

    // ==========================================
    // Search Companies
    // ==========================================

    @GetMapping("/search")
    public ResponseEntity<Page<CompanyResponseDTO>> searchCompanies(

            @RequestParam(required = false)
            String companyName,

            @RequestParam(required = false)
            String industry,

            @RequestParam(required = false)
            String location,

            @RequestParam(required = false)
            Boolean isActive,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(

                companyService.searchCompanies(

                        companyName,

                        industry,

                        location,

                        isActive,

                        page,

                        size,

                        sortBy,

                        direction));
    }

    // ==========================================
    // Delete Company
    // ==========================================

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(

            @PathVariable Long companyId) {

        companyService.deleteCompany(companyId);

        return ResponseEntity.ok(
                "Company deleted successfully.");
    }
}