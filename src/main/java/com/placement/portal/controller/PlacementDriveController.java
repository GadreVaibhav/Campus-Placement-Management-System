package com.placement.portal.controller;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.PlacementDriveRequestDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.service.PlacementDriveService;

@RestController
@RequestMapping("/api/placement-drives")
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementDriveController {

    private final PlacementDriveService placementDriveService;

    public PlacementDriveController(
            PlacementDriveService placementDriveService) {

        this.placementDriveService = placementDriveService;
    }

    // ==========================================
    // Create Placement Drive
    // ==========================================

   @PostMapping
public ResponseEntity<PlacementDriveResponseDTO> createPlacementDrive(

        @Valid
        @RequestBody PlacementDriveRequestDTO requestDTO,

        Authentication authentication) {

    return new ResponseEntity<>(

            placementDriveService.createPlacementDrive(

                    requestDTO,

                    authentication.getName()),

            HttpStatus.CREATED);
}

    // ==========================================
    // Update Placement Drive
    // ==========================================

    @PutMapping("/{driveId}")
    public ResponseEntity<PlacementDriveResponseDTO> updatePlacementDrive(

            @PathVariable Long driveId,

            @Valid
            @RequestBody PlacementDriveRequestDTO requestDTO) {

        return ResponseEntity.ok(

                placementDriveService.updatePlacementDrive(

                        driveId,

                        requestDTO));
    }

    // ==========================================
    // Get Placement Drive By ID
    // ==========================================

    @GetMapping("/{driveId}")
    public ResponseEntity<PlacementDriveResponseDTO> getPlacementDriveById(

            @PathVariable Long driveId) {

        return ResponseEntity.ok(

                placementDriveService.getPlacementDriveById(driveId));
    }

    // ==========================================
    // Get All Placement Drives
    // ==========================================

    @GetMapping
    public ResponseEntity<Page<PlacementDriveResponseDTO>>
    getAllPlacementDrives(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(

                placementDriveService.getAllPlacementDrives(

                        page,

                        size,

                        sortBy,

                        direction));
    }

    @GetMapping("/upcoming")
public ResponseEntity<List<PlacementDriveResponseDTO>>
getUpcomingPlacementDrives() {

    return ResponseEntity.ok(

            placementDriveService.getUpcomingPlacementDrives());

}
    // ==========================================
    // Delete Placement Drive
    // ==========================================

    @DeleteMapping("/{driveId}")
    public ResponseEntity<String> deletePlacementDrive(

            @PathVariable Long driveId) {

        placementDriveService.deletePlacementDrive(driveId);

        return ResponseEntity.ok(
                "Placement Drive deleted successfully.");
    }
}