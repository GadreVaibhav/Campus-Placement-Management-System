package com.placement.portal.mapper;

import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.entity.PlacementDrive;

public class PlacementDriveMapper {

    private PlacementDriveMapper() {
    }

    public static PlacementDriveResponseDTO toResponseDTO(
            PlacementDrive drive) {

        PlacementDriveResponseDTO dto =
                new PlacementDriveResponseDTO();

        dto.setId(drive.getId());

        dto.setCompanyId(
        drive.getCompany().getId());
        dto.setCompanyName(
                drive.getCompany().getCompanyName());

        dto.setJobRole(drive.getJobRole());

        dto.setPackageOffered(
                drive.getPackageOffered());

        dto.setMinimumCgpa(
                drive.getMinimumCgpa());

        dto.setDriveDate(
                drive.getDriveDate());

        dto.setRegistrationDeadline(
                drive.getRegistrationDeadline());

        dto.setStatus(
                drive.getStatus());

        return dto;
    }
}