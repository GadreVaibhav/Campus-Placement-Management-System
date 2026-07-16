package com.placement.portal.service.impl;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.AdminDashboardResponseDTO;
import com.placement.portal.entity.ApplicationStatus;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.AdminDashboardService;

@Service
public class AdminDashboardServiceImpl
        implements AdminDashboardService {

    private final StudentRepository studentRepository;

    private final CompanyRepository companyRepository;

    private final RecruiterRepository recruiterRepository;

    private final PlacementDriveRepository placementDriveRepository;

    private final StudentApplicationRepository applicationRepository;

    public AdminDashboardServiceImpl(

            StudentRepository studentRepository,

            CompanyRepository companyRepository,

            RecruiterRepository recruiterRepository,

            PlacementDriveRepository placementDriveRepository,

            StudentApplicationRepository applicationRepository) {

        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public AdminDashboardResponseDTO getDashboardStatistics() {

        AdminDashboardResponseDTO dto =
                new AdminDashboardResponseDTO();

        long totalStudents =
                studentRepository.count();

        long totalCompanies =
                companyRepository.count();

        long totalRecruiters =
                recruiterRepository.count();

        long totalDrives =
                placementDriveRepository.count();

        long totalApplications =
                applicationRepository.count();

        long placedStudents =
                applicationRepository
                        .findAll()
                        .stream()
                        .filter(application ->
                                application.getStatus()
                                        == ApplicationStatus.SELECTED)
                        .count();

        double placementPercentage = 0;

        if (totalStudents > 0) {

            placementPercentage =
                    (placedStudents * 100.0)
                            / totalStudents;
        }

        dto.setTotalStudents(totalStudents);

        dto.setTotalCompanies(totalCompanies);

        dto.setTotalRecruiters(totalRecruiters);

        dto.setTotalPlacementDrives(totalDrives);

        dto.setTotalApplications(totalApplications);

        dto.setPlacedStudents(placedStudents);

        dto.setPlacementPercentage(placementPercentage);

        return dto;
    }
}