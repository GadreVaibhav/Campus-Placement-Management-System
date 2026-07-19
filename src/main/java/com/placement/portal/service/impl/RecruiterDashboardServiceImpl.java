package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.RecruiterDashboardResponseDTO;
import com.placement.portal.dto.PlacementDriveResponseDTO;
import com.placement.portal.mapper.PlacementDriveMapper;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.service.RecruiterDashboardService;

@Service
public class RecruiterDashboardServiceImpl
        implements RecruiterDashboardService {

    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;

    public RecruiterDashboardServiceImpl(
            PlacementDriveRepository placementDriveRepository,
            ApplicationRepository applicationRepository,
            InterviewRepository interviewRepository) {

        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public RecruiterDashboardResponseDTO getDashboard() {

        RecruiterDashboardResponseDTO dto =
                new RecruiterDashboardResponseDTO();

        dto.setTotalDrives(
                placementDriveRepository.count());

        dto.setActiveDrives(
                placementDriveRepository.countByStatusIgnoreCase("LIVE"));

        dto.setTotalApplications(
                applicationRepository.count());

        dto.setScheduledInterviews(
                interviewRepository.count());

        return dto;
    }
@Override
public List<PlacementDriveResponseDTO> getRecentPlacementDrives() {

    return placementDriveRepository
            .findTop5ByOrderByDriveDateDesc()
            .stream()
            .map(PlacementDriveMapper::toResponseDTO)
            .toList();
}
}