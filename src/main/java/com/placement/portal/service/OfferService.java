package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.OfferRequestDTO;
import com.placement.portal.dto.OfferResponseDTO;

public interface OfferService {

    // Recruiter
    OfferResponseDTO createOffer(
            OfferRequestDTO requestDTO,
            String recruiterEmail);

    OfferResponseDTO updateOffer(
            Long offerId,
            OfferRequestDTO requestDTO);

    void deleteOffer(Long offerId);

    List<OfferResponseDTO> getRecruiterOffers(
            String recruiterEmail);

    // Student
    List<OfferResponseDTO> getStudentOffers(
            String studentEmail);

    OfferResponseDTO acceptOffer(
            Long offerId);

    OfferResponseDTO rejectOffer(
            Long offerId);

}