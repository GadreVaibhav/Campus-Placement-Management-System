package com.placement.portal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.placement.portal.dto.PlacementDriveRequestDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;

public interface PlacementDriveService {

    // ==========================================
    // Create Placement Drive
    // ==========================================

   PlacementDriveResponseDTO createPlacementDrive(

        PlacementDriveRequestDTO requestDTO,

        String recruiterEmail);;

    // ==========================================
    // Update Placement Drive
    // ==========================================

    PlacementDriveResponseDTO updatePlacementDrive(
            Long driveId,
            PlacementDriveRequestDTO requestDTO);

    // ==========================================
    // Get Placement Drive By ID
    // ==========================================

    PlacementDriveResponseDTO getPlacementDriveById(
            Long driveId);

    // ==========================================
    // Get All Placement Drives
    // ==========================================

    Page<PlacementDriveResponseDTO> getAllPlacementDrives(

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Delete Placement Drive
    // ==========================================

    void deletePlacementDrive(Long driveId);
    List<PlacementDriveResponseDTO> getUpcomingPlacementDrives();
}