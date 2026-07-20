package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.OfferRequestDTO;
import com.placement.portal.dto.OfferResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Offer;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.StudentApplication;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.OfferRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
   private final ApplicationRepository applicationRepository;
    private final RecruiterRepository recruiterRepository;
    private final StudentRepository studentRepository;

    public OfferServiceImpl(
            OfferRepository offerRepository,
            ApplicationRepository applicationRepository,
            RecruiterRepository recruiterRepository,
            StudentRepository studentRepository) {

        this.offerRepository = offerRepository;
        this.applicationRepository = applicationRepository;
        this.recruiterRepository = recruiterRepository;
        this.studentRepository = studentRepository;
    }

    // =====================================================
    // CREATE OFFER
    // =====================================================

    @Override
    public OfferResponseDTO createOffer(
            OfferRequestDTO requestDTO,
            String recruiterEmail) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

       Application application =
    applicationRepository
        .findById(requestDTO.getApplicationId())
        .orElseThrow(() ->
            new RuntimeException("Application not found"));

       if (!application.getJob()
        .getCompany()
        .getId()
        .equals(recruiter.getCompany().getId())){

            throw new RuntimeException("Unauthorized");
        }

        if (offerRepository.findByApplication(application).isPresent()) {
            throw new RuntimeException("Offer already exists");
        }

        Offer offer = new Offer();

        offer.setApplication(application);
        offer.setPackageOffered(requestDTO.getPackageOffered());
        offer.setLocation(requestDTO.getLocation());
        offer.setJoiningDate(requestDTO.getJoiningDate());
        offer.setStatus("OFFERED");

        Offer saved = offerRepository.save(offer);

        return mapToDTO(saved);
    }

    // =====================================================
    // RECRUITER OFFERS
    // =====================================================

    @Override
    public List<OfferResponseDTO> getRecruiterOffers(
            String recruiterEmail) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getApplication()
        .getJob()
        .getCompany()
                        .getId()
                        .equals(recruiter.getCompany().getId()))
                .map(this::mapToDTO)
                .toList();
    }

    // =====================================================
    // UPDATE OFFER
    // =====================================================

    @Override
    public OfferResponseDTO updateOffer(
            Long offerId,
            OfferRequestDTO requestDTO) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setPackageOffered(requestDTO.getPackageOffered());
        offer.setLocation(requestDTO.getLocation());
        offer.setJoiningDate(requestDTO.getJoiningDate());

        return mapToDTO(offerRepository.save(offer));
    }

    // =====================================================
    // DELETE OFFER
    // =====================================================

    @Override
    public void deleteOffer(Long offerId) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offerRepository.delete(offer);
    }

    // =====================================================
    // STUDENT OFFERS
    // =====================================================

    @Override
    public List<OfferResponseDTO> getStudentOffers(
            String studentEmail) {

        Student student = studentRepository.findByUserEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return offerRepository.findByApplicationStudent(student)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =====================================================
    // ACCEPT OFFER
    // =====================================================

    @Override
    public OfferResponseDTO acceptOffer(Long offerId) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setStatus("ACCEPTED");

        return mapToDTO(offerRepository.save(offer));
    }

    // =====================================================
    // REJECT OFFER
    // =====================================================

    @Override
    public OfferResponseDTO rejectOffer(Long offerId) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setStatus("REJECTED");

        return mapToDTO(offerRepository.save(offer));
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private OfferResponseDTO mapToDTO(Offer offer) {

        OfferResponseDTO dto = new OfferResponseDTO();

        dto.setOfferId(offer.getId());

        dto.setApplicationId(
        offer.getApplication().getId());

       dto.setStudentName(
        offer.getApplication()
                .getStudent()
                .getName());


        offer.getApplication()
        .getStudent();


       dto.setJobTitle(
        offer.getApplication()
                .getJob()
                .getJobTitle());

        dto.setPackageOffered(
                offer.getPackageOffered());

        dto.setLocation(
                offer.getLocation());

        dto.setJoiningDate(
                offer.getJoiningDate());

        dto.setStatus(
                offer.getStatus());

        dto.setCreatedAt(
                offer.getCreatedAt());

        return dto;
    }
}