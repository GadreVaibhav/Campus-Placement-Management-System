package com.placement.portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.placement.portal.dto.OfferRequestDTO;
import com.placement.portal.dto.OfferResponseDTO;
import com.placement.portal.service.OfferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // ==========================================
    // Recruiter
    // ==========================================

    @PostMapping
    public ResponseEntity<OfferResponseDTO> createOffer(

            @Valid
            @RequestBody OfferRequestDTO requestDTO,

            Authentication authentication) {

        return ResponseEntity.ok(

                offerService.createOffer(

                        requestDTO,

                        authentication.getName()));
    }

    @GetMapping("/recruiter")
    public ResponseEntity<List<OfferResponseDTO>> getRecruiterOffers(

            Authentication authentication) {

        return ResponseEntity.ok(

                offerService.getRecruiterOffers(

                        authentication.getName()));
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> updateOffer(

            @PathVariable Long offerId,

            @Valid
            @RequestBody OfferRequestDTO requestDTO) {

        return ResponseEntity.ok(

                offerService.updateOffer(

                        offerId,

                        requestDTO));
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<String> deleteOffer(

            @PathVariable Long offerId) {

        offerService.deleteOffer(offerId);

        return ResponseEntity.ok("Offer deleted successfully.");
    }

    // ==========================================
    // Student
    // ==========================================

    @GetMapping("/student")
    public ResponseEntity<List<OfferResponseDTO>> getStudentOffers(

            Authentication authentication) {

        return ResponseEntity.ok(

                offerService.getStudentOffers(

                        authentication.getName()));
    }

    @PutMapping("/{offerId}/accept")
    public ResponseEntity<OfferResponseDTO> acceptOffer(

            @PathVariable Long offerId) {

        return ResponseEntity.ok(

                offerService.acceptOffer(offerId));
    }

    @PutMapping("/{offerId}/reject")
    public ResponseEntity<OfferResponseDTO> rejectOffer(

            @PathVariable Long offerId) {

        return ResponseEntity.ok(

                offerService.rejectOffer(offerId));
    }

}