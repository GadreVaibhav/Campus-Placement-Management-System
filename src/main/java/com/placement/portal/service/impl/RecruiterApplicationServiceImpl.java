package com.placement.portal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.RecentApplicationDTO;
import com.placement.portal.dto.RecruiterApplicationResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.service.RecruiterApplicationService;

@Service
public class RecruiterApplicationServiceImpl
        implements RecruiterApplicationService {

                private final ApplicationRepository applicationRepository;
                private final StudentApplicationRepository studentApplicationRepository;
                private final RecruiterRepository recruiterRepository;
   public RecruiterApplicationServiceImpl(

                        ApplicationRepository applicationRepository,

                        StudentApplicationRepository studentApplicationRepository,

                        RecruiterRepository recruiterRepository) {

                this.applicationRepository = applicationRepository;

                this.studentApplicationRepository = studentApplicationRepository;

                this.recruiterRepository = recruiterRepository;
                }
    @Override
    public List<RecentApplicationDTO> getRecentApplications() {

                List<Application> applications =
                        applicationRepository.findTop5ByOrderByAppliedAtDesc();

                return applications.stream()

                        .map(application ->

                                new RecentApplicationDTO(

                                        application.getStudent().getName(),

                                        application.getJob()
                                                .getCompany()
                                                .getCompanyName(),

                                        application.getJob().getJobTitle(),

                                        application.getStatus()

                                ))

                        .collect(Collectors.toList());
        }
    @Override
public List<RecruiterApplicationResponseDTO> getRecruiterApplications(
        String recruiterEmail) {

    Recruiter recruiter = recruiterRepository
            .findByEmail(recruiterEmail)
            .orElseThrow(() ->
                    new RuntimeException("Recruiter not found"));

    return studentApplicationRepository.findAll()
            .stream()
            .filter(application ->
                    application.getPlacementDrive()
                            .getCompany()
                            .getId()
                            .equals(recruiter.getCompany().getId()))
            .map(application -> {

                RecruiterApplicationResponseDTO dto =
                        new RecruiterApplicationResponseDTO();

                dto.setApplicationId(application.getId());

                dto.setStudentId(
                        application.getStudent().getStudentId());

                dto.setStudentName(
                        application.getStudent().getName());

                dto.setStudentEmail(
                        application.getStudent().getEmail());

                dto.setCgpa(
                        Double.valueOf(
                                application.getStudent().getCgpa()));

                dto.setJobRole(
                        application.getPlacementDrive().getJobRole());

                dto.setCompanyName(
                        application.getPlacementDrive()
                                .getCompany()
                                .getCompanyName());

                dto.setStatus(
                        application.getStatus().name());

                dto.setApplicationDate(
                        application.getApplicationDate());

                dto.setPackageOffered(
                application.getPlacementDrive().getPackageOffered());

                dto.setMinimumCgpa(
                application.getPlacementDrive().getMinimumCgpa());

                dto.setDriveDate(
                application.getPlacementDrive().getDriveDate());

                dto.setRegistrationDeadline(
                application.getPlacementDrive().getRegistrationDeadline());

                return dto;
                        })
                        .toList();
                }
    
}