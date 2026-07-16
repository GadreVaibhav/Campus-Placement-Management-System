package com.placement.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.PlacementDriveRequestDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.entity.Company;
import com.placement.portal.entity.PlacementDrive;
import com.placement.portal.exception.CompanyNotFoundException;
import com.placement.portal.exception.PlacementDriveNotFoundException;
import com.placement.portal.mapper.PlacementDriveMapper;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.service.PlacementDriveService;

@Service
public class PlacementDriveServiceImpl
        implements PlacementDriveService {

    private static final Logger logger =
            LoggerFactory.getLogger(PlacementDriveServiceImpl.class);

    private final PlacementDriveRepository placementDriveRepository;

    private final CompanyRepository companyRepository;

    public PlacementDriveServiceImpl(
            PlacementDriveRepository placementDriveRepository,
            CompanyRepository companyRepository) {

        this.placementDriveRepository = placementDriveRepository;
        this.companyRepository = companyRepository;
    }

    // ==========================================
    // Create Placement Drive
    // ==========================================

    @Override
    public PlacementDriveResponseDTO createPlacementDrive(
            PlacementDriveRequestDTO requestDTO) {

        logger.info("Creating placement drive for company ID: {}",
                requestDTO.getCompanyId());

        Company company = companyRepository.findById(
                requestDTO.getCompanyId())
                .orElseThrow(() -> {

                    logger.error("Company not found with ID: {}",
                            requestDTO.getCompanyId());

                    return new CompanyNotFoundException(
                            "Company not found with ID: "
                                    + requestDTO.getCompanyId());
                });

        PlacementDrive drive = new PlacementDrive();

        drive.setCompany(company);
        drive.setJobRole(requestDTO.getJobRole());
        drive.setPackageOffered(requestDTO.getPackageOffered());
        drive.setMinimumCgpa(requestDTO.getMinimumCgpa());
        drive.setDriveDate(requestDTO.getDriveDate());
        drive.setRegistrationDeadline(
                requestDTO.getRegistrationDeadline());
        drive.setStatus(requestDTO.getStatus());

        PlacementDrive savedDrive =
                placementDriveRepository.save(drive);

        logger.info(
                "Placement Drive created successfully. ID: {}",
                savedDrive.getId());

        return PlacementDriveMapper.toResponseDTO(savedDrive);
    }

    // ==========================================
    // Update Placement Drive
    // ==========================================

    @Override
    public PlacementDriveResponseDTO updatePlacementDrive(
            Long driveId,
            PlacementDriveRequestDTO requestDTO) {

        logger.info("Updating placement drive ID: {}", driveId);

        PlacementDrive drive = placementDriveRepository.findById(driveId)
                .orElseThrow(() -> {

                    logger.error("Placement Drive not found with ID: {}", driveId);

                    return new PlacementDriveNotFoundException(
                            "Placement Drive not found with ID: " + driveId);
                });

        Company company = companyRepository.findById(
                requestDTO.getCompanyId())
                .orElseThrow(() -> {

                    logger.error("Company not found with ID: {}",
                            requestDTO.getCompanyId());

                    return new CompanyNotFoundException(
                            "Company not found with ID: "
                                    + requestDTO.getCompanyId());
                });

        drive.setCompany(company);
        drive.setJobRole(requestDTO.getJobRole());
        drive.setPackageOffered(requestDTO.getPackageOffered());
        drive.setMinimumCgpa(requestDTO.getMinimumCgpa());
        drive.setDriveDate(requestDTO.getDriveDate());
        drive.setRegistrationDeadline(
                requestDTO.getRegistrationDeadline());
        drive.setStatus(requestDTO.getStatus());

        PlacementDrive updatedDrive =
                placementDriveRepository.save(drive);

        logger.info("Placement Drive updated successfully.");

        return PlacementDriveMapper.toResponseDTO(updatedDrive);
    }

    // ==========================================
// Get Placement Drive By ID
// ==========================================

@Override
public PlacementDriveResponseDTO getPlacementDriveById(
        Long driveId) {

    logger.info("Fetching placement drive ID: {}", driveId);

    PlacementDrive drive = placementDriveRepository.findById(driveId)
            .orElseThrow(() -> {

                logger.error("Placement Drive not found with ID: {}", driveId);

                return new PlacementDriveNotFoundException(
                        "Placement Drive not found with ID: " + driveId);
            });

    logger.info("Placement Drive fetched successfully.");

    return PlacementDriveMapper.toResponseDTO(drive);
}

// ==========================================
// Get All Placement Drives
// ==========================================

@Override
public Page<PlacementDriveResponseDTO> getAllPlacementDrives(

        int page,

        int size,

        String sortBy,

        String direction) {

    logger.info(
            "Fetching placement drives | page={} size={} sortBy={} direction={}",
            page,
            size,
            sortBy,
            direction);

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    Page<PlacementDrive> drives =
            placementDriveRepository.findAll(pageable);

    logger.info(
            "Total placement drives found: {}",
            drives.getTotalElements());

    return drives.map(PlacementDriveMapper::toResponseDTO);
}

@Override
public List<PlacementDriveResponseDTO> getUpcomingPlacementDrives() {

    logger.info("Fetching upcoming placement drives.");

    return placementDriveRepository

            .findByStatusIgnoreCase("UPCOMING")

            .stream()

            .map(PlacementDriveMapper::toResponseDTO)

            .toList();

}

// ==========================================
// Delete Placement Drive
// ==========================================

@Override
public void deletePlacementDrive(Long driveId) {

    logger.info("Deleting placement drive ID: {}", driveId);

    PlacementDrive drive = placementDriveRepository.findById(driveId)
            .orElseThrow(() -> {

                logger.error("Placement Drive not found with ID: {}", driveId);

                return new PlacementDriveNotFoundException(
                        "Placement Drive not found with ID: " + driveId);
            });

    placementDriveRepository.delete(drive);

    logger.info("Placement Drive deleted successfully.");
}
}