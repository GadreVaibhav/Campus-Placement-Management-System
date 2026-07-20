package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.dto.RecruiterDashboardResponseDTO;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.mapper.PlacementDriveMapper;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.RecruiterDashboardService;

@Service
public class RecruiterDashboardServiceImpl
        implements RecruiterDashboardService {

    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;
    private final RecruiterRepository recruiterRepository;

    public RecruiterDashboardServiceImpl(
            PlacementDriveRepository placementDriveRepository,
            ApplicationRepository applicationRepository,
            InterviewRepository interviewRepository,
            RecruiterRepository recruiterRepository) {

        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public RecruiterDashboardResponseDTO getDashboard(
            String recruiterEmail) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(recruiterEmail)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));

        RecruiterDashboardResponseDTO dto =
                new RecruiterDashboardResponseDTO();

        dto.setTotalDrives(
                placementDriveRepository.countByCompany(
                        recruiter.getCompany()));

        dto.setActiveDrives(
                placementDriveRepository.findByCompanyOrderByDriveDateDesc(
                        recruiter.getCompany())
                        .stream()
                        .filter(d -> "LIVE".equalsIgnoreCase(d.getStatus()))
                        .count());

        dto.setTotalApplications(
                (long) applicationRepository
                        .findByJobCompanyId(
                                recruiter.getCompany().getId())
                        .size());

        dto.setScheduledInterviews(
                interviewRepository
                        .countByApplicationJobRecruiter(
                                recruiter));

        return dto;
    }

    @Override
    public List<PlacementDriveResponseDTO> getRecentPlacementDrives(
            String recruiterEmail) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(recruiterEmail)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));

        return placementDriveRepository
                .findByCompanyOrderByDriveDateDesc(
                        recruiter.getCompany())
                .stream()
                .limit(5)
                .map(PlacementDriveMapper::toResponseDTO)
                .toList();
    }
}