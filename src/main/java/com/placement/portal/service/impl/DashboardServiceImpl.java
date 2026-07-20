package com.placement.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.placement.portal.dto.ApplicationStatusCountDTO;
import com.placement.portal.dto.BranchCountDTO;
import com.placement.portal.dto.DashboardResponseDTO;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.DocumentRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private static final Logger logger =
            LoggerFactory.getLogger(DashboardServiceImpl.class);

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;
    private final DocumentRepository documentRepository;

    public DashboardServiceImpl(
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            RecruiterRepository recruiterRepository,
            PlacementDriveRepository placementDriveRepository,
            ApplicationRepository applicationRepository,
            DocumentRepository documentRepository) {

        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public DashboardResponseDTO getAdminDashboard() {

        logger.info("Fetching Admin Dashboard");

        DashboardResponseDTO dashboard = new DashboardResponseDTO();

        dashboard.setTotalStudents(studentRepository.count());

        dashboard.setTotalCompanies(companyRepository.count());

        dashboard.setTotalRecruiters(recruiterRepository.count());

        dashboard.setTotalPlacementDrives(placementDriveRepository.count());

        // Total Applications
        dashboard.setTotalApplications(applicationRepository.count());

        // Documents Uploaded
        dashboard.setDocumentsUploaded(documentRepository.count());

        // Selected Students
        long selectedStudents = applicationRepository.findAll()
                .stream()
                .filter(a -> "SELECTED".equalsIgnoreCase(a.getStatus()))
                .count();

        dashboard.setSelectedStudents(selectedStudents);

        // Upcoming Drives
        long upcomingDrives = placementDriveRepository.findAll()
                .stream()
                .filter(d -> "UPCOMING".equalsIgnoreCase(d.getStatus()))
                .count();

        dashboard.setUpcomingDrives(upcomingDrives);

        return dashboard;
    }

    // ==========================================
    // Students By Branch
    // ==========================================

    @Override
    public List<BranchCountDTO> getStudentsByBranch() {

        return studentRepository.countStudentsByBranch();
    }

    // ==========================================
    // Applications By Status
    // ==========================================

    @Override
    public List<ApplicationStatusCountDTO> getApplicationsByStatus() {

        return applicationRepository.findAll()
                .stream()
                .collect(
                        java.util.stream.Collectors.groupingBy(
                                application -> application.getStatus(),
                                java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> {

                    ApplicationStatusCountDTO dto =
                            new ApplicationStatusCountDTO();

                    dto.setStatus(entry.getKey());
                    dto.setCount(entry.getValue());

                    return dto;
                })
                .toList();
    }
}