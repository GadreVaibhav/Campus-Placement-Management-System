package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.placement.portal.entity.StudentApplication;
import com.placement.portal.dto.OfferRequestDTO;
import com.placement.portal.dto.OfferResponseDTO;
import com.placement.portal.entity.Offer;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.entity.Student;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.OfferRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

private final StudentApplicationRepository studentApplicationRepository;

private final RecruiterRepository recruiterRepository;

private final StudentRepository studentRepository;

public OfferServiceImpl(

        OfferRepository offerRepository,

        StudentApplicationRepository studentApplicationRepository,

        RecruiterRepository recruiterRepository,

        StudentRepository studentRepository) {

    this.offerRepository = offerRepository;

    this.studentApplicationRepository = studentApplicationRepository;

    this.recruiterRepository = recruiterRepository;

    this.studentRepository = studentRepository;
}
@Override

public OfferResponseDTO createOffer(

        OfferRequestDTO requestDTO,

        String recruiterEmail) {
            
            System.out.println("Logged In Email = " + recruiterEmail);
    // ==========================================
    // Recruiter
    // ==========================================

    Recruiter recruiter = recruiterRepository

            .findByEmail(recruiterEmail)

            .orElseThrow(() ->

                    new RuntimeException("Recruiter not found"));
System.out.println("Recruiter Company = " + recruiter.getCompany().getId());
    // ==========================================
    // Application
    // ==========================================
System.out.println("Application ID Received = " + requestDTO.getApplicationId());
   StudentApplication application = studentApplicationRepository

        .findById(requestDTO.getApplicationId())

        .orElseThrow(() ->

                new RuntimeException("Application not found"));

System.out.println("Application Company = "
        + application.getPlacementDrive()
                     .getCompany()
                     .getId());
    // ==========================================
    // Security Check
    // Recruiter can issue offer only for own company
    // ==========================================

   if (!application.getPlacementDrive()

        .getCompany()

        .getId()

        .equals(recruiter.getCompany().getId())) {

    throw new RuntimeException("Unauthorized");
}

    // ==========================================
    // Prevent Duplicate Offer
    // ==========================================

    if (offerRepository

        .findByStudentApplication(application)

        .isPresent()) {

        throw new RuntimeException(

                "Offer already exists");

    }

    // ==========================================
    // Save Offer
    // ==========================================

    Offer offer = new Offer();

    offer.setStudentApplication(application);

    offer.setPackageOffered(

            requestDTO.getPackageOffered());

    offer.setLocation(

            requestDTO.getLocation());

    offer.setJoiningDate(

            requestDTO.getJoiningDate());

    offer.setStatus("OFFERED");

    Offer savedOffer =

            offerRepository.save(offer);

    // ==========================================
    // Response DTO
    // ==========================================

    OfferResponseDTO dto =

            new OfferResponseDTO();

    dto.setOfferId(savedOffer.getId());
dto.setApplicationId(application.getId());

dto.setStudentName(

        application.getStudent().getName());

dto.setCompanyName(

        application.getPlacementDrive()

                .getCompany()

                .getCompanyName());

dto.setJobTitle(

        application.getPlacementDrive()

                .getJobRole());

    dto.setPackageOffered(

            savedOffer.getPackageOffered());

    dto.setLocation(

            savedOffer.getLocation());

    dto.setJoiningDate(

            savedOffer.getJoiningDate());

    dto.setStatus(

            savedOffer.getStatus());

    dto.setCreatedAt(

            savedOffer.getCreatedAt());

    return dto;

}
@Override
public List<OfferResponseDTO> getRecruiterOffers(
        String recruiterEmail) {

    Recruiter recruiter = recruiterRepository

            .findByEmail(recruiterEmail)

            .orElseThrow(() ->
                    new RuntimeException("Recruiter not found"));

    return offerRepository.findAll()

            .stream()

            .filter(offer ->

                    offer.getStudentApplication()

                            .getPlacementDrive()

                            .getCompany()

                            .getId()

                            .equals(recruiter.getCompany().getId()))

            .map(offer -> {

                OfferResponseDTO dto =
                        new OfferResponseDTO();

                dto.setOfferId(offer.getId());

                dto.setApplicationId(
                        offer.getStudentApplication().getId());

                dto.setStudentName(
                        offer.getStudentApplication()
                                .getStudent()
                                .getName());

                dto.setCompanyName(
                        offer.getStudentApplication()
                                .getPlacementDrive()
                                .getCompany()
                                .getCompanyName());

                dto.setJobTitle(
                        offer.getStudentApplication()
                                .getPlacementDrive()
                                .getJobRole());

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

            })

            .toList();
}
@Override
public OfferResponseDTO updateOffer(

        Long offerId,

        OfferRequestDTO requestDTO) {

    Offer offer = offerRepository

            .findById(offerId)

            .orElseThrow(() ->

                    new RuntimeException("Offer not found"));

    offer.setPackageOffered(
            requestDTO.getPackageOffered());

    offer.setLocation(
            requestDTO.getLocation());

    offer.setJoiningDate(
            requestDTO.getJoiningDate());

    Offer updatedOffer =
            offerRepository.save(offer);

    OfferResponseDTO dto =
            new OfferResponseDTO();

    dto.setOfferId(updatedOffer.getId());

    dto.setApplicationId(
            updatedOffer.getStudentApplication().getId());

    dto.setStudentName(
            updatedOffer.getStudentApplication()
                    .getStudent()
                    .getName());

    dto.setCompanyName(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getCompany()
                    .getCompanyName());

    dto.setJobTitle(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getJobRole());

    dto.setPackageOffered(
            updatedOffer.getPackageOffered());

    dto.setLocation(
            updatedOffer.getLocation());

    dto.setJoiningDate(
            updatedOffer.getJoiningDate());

    dto.setStatus(
            updatedOffer.getStatus());

    dto.setCreatedAt(
            updatedOffer.getCreatedAt());

    return dto;
}
@Override
public void deleteOffer(Long offerId) {

    Offer offer = offerRepository

            .findById(offerId)

            .orElseThrow(() ->

                    new RuntimeException("Offer not found"));

    offerRepository.delete(offer);
}
@Override
public List<OfferResponseDTO> getStudentOffers(
        String studentEmail) {

    Student student = studentRepository

            .findByUserEmail(studentEmail)

            .orElseThrow(() ->

                    new RuntimeException("Student not found"));

    return offerRepository

            .findByStudentApplicationStudent(student)

            .stream()

            .map(offer -> {

                OfferResponseDTO dto =
                        new OfferResponseDTO();

                dto.setOfferId(offer.getId());

                dto.setApplicationId(
                        offer.getStudentApplication().getId());

                dto.setStudentName(
                        student.getName());

                dto.setCompanyName(
                        offer.getStudentApplication()
                                .getPlacementDrive()
                                .getCompany()
                                .getCompanyName());

                dto.setJobTitle(
                        offer.getStudentApplication()
                                .getPlacementDrive()
                                .getJobRole());

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

            })

            .toList();
}
@Override
public OfferResponseDTO acceptOffer(Long offerId) {

    Offer offer = offerRepository

            .findById(offerId)

            .orElseThrow(() ->
                    new RuntimeException("Offer not found"));

    offer.setStatus("ACCEPTED");

    Offer updatedOffer = offerRepository.save(offer);

    OfferResponseDTO dto = new OfferResponseDTO();

    dto.setOfferId(updatedOffer.getId());

    dto.setApplicationId(
            updatedOffer.getStudentApplication().getId());

    dto.setStudentName(
            updatedOffer.getStudentApplication()
                    .getStudent()
                    .getName());

    dto.setCompanyName(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getCompany()
                    .getCompanyName());

    dto.setJobTitle(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getJobRole());

    dto.setPackageOffered(
            updatedOffer.getPackageOffered());

    dto.setLocation(
            updatedOffer.getLocation());

    dto.setJoiningDate(
            updatedOffer.getJoiningDate());

    dto.setStatus(
            updatedOffer.getStatus());

    dto.setCreatedAt(
            updatedOffer.getCreatedAt());

    return dto;
}
@Override
public OfferResponseDTO rejectOffer(Long offerId) {

    Offer offer = offerRepository

            .findById(offerId)

            .orElseThrow(() ->
                    new RuntimeException("Offer not found"));

    offer.setStatus("REJECTED");

    Offer updatedOffer = offerRepository.save(offer);

    OfferResponseDTO dto = new OfferResponseDTO();

    dto.setOfferId(updatedOffer.getId());

    dto.setApplicationId(
            updatedOffer.getStudentApplication().getId());

    dto.setStudentName(
            updatedOffer.getStudentApplication()
                    .getStudent()
                    .getName());

    dto.setCompanyName(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getCompany()
                    .getCompanyName());

    dto.setJobTitle(
            updatedOffer.getStudentApplication()
                    .getPlacementDrive()
                    .getJobRole());

    dto.setPackageOffered(
            updatedOffer.getPackageOffered());

    dto.setLocation(
            updatedOffer.getLocation());

    dto.setJoiningDate(
            updatedOffer.getJoiningDate());

    dto.setStatus(
            updatedOffer.getStatus());

    dto.setCreatedAt(
            updatedOffer.getCreatedAt());

    return dto;
}
}