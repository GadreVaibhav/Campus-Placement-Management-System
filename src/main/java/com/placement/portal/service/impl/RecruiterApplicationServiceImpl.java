package com.placement.portal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.placement.portal.dto.RecentApplicationDTO;
import com.placement.portal.dto.RecruiterApplicationResponseDTO;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.RecruiterApplicationService;

@Service
public class RecruiterApplicationServiceImpl
        implements RecruiterApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RecruiterRepository recruiterRepository;

    public RecruiterApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            RecruiterRepository recruiterRepository) {

        this.applicationRepository = applicationRepository;
        this.recruiterRepository = recruiterRepository;
    }

    // ==========================================
    // Recent Applications
    // ==========================================

   @Override
public List<RecentApplicationDTO> getRecentApplications(
        String recruiterEmail) {

    Recruiter recruiter = recruiterRepository
            .findByEmail(recruiterEmail)
            .orElseThrow(() ->
                    new RuntimeException("Recruiter not found"));

    return applicationRepository
            .findByJobCompanyId(recruiter.getCompany().getId())
            .stream()
            .sorted((a, b) -> b.getAppliedAt().compareTo(a.getAppliedAt()))
            .limit(5)
            .map(application ->
                    new RecentApplicationDTO(
                            application.getStudent().getName(),
                            application.getJob().getCompany().getCompanyName(),
                            application.getJob().getJobTitle(),
                            application.getStatus()))
            .toList();
}

    // ==========================================
    // Recruiter Applications
    // ==========================================

   @Override
public List<RecruiterApplicationResponseDTO> getRecruiterApplications(
        String recruiterEmail) {

    Recruiter recruiter = recruiterRepository
            .findByEmail(recruiterEmail)
            .orElseThrow(() ->
                    new RuntimeException("Recruiter not found"));

    return applicationRepository
            .findByJobCompanyId(recruiter.getCompany().getId())
            .stream()
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
                        Double.valueOf(application.getStudent().getCgpa()));

                dto.setCompanyName(
                        application.getJob().getCompany().getCompanyName());

                dto.setJobRole(
                        application.getJob().getJobTitle());

                dto.setStatus(
                        application.getStatus());

                dto.setApplicationDate(
                        application.getAppliedAt());

                dto.setPackageOffered(
                        application.getJob().getPackageLpa());

                dto.setMinimumCgpa(
                        Double.valueOf(application.getJob().getEligibilityCgpa()));

                // Use lastDate as Drive Date for now
                dto.setDriveDate(
                        application.getJob().getLastDate());

                dto.setRegistrationDeadline(
                        application.getJob().getLastDate());

                return dto;
            })
            .toList();
}
}