package com.placement.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

import com.placement.portal.dto.ApplicationStatusCountDTO;
import com.placement.portal.dto.BranchCountDTO;
import com.placement.portal.dto.DashboardResponseDTO;
import com.placement.portal.entity.ApplicationStatus;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.DocumentRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(DashboardServiceImpl.class);

    // ==========================================
    // Repositories
    // ==========================================

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final StudentApplicationRepository studentApplicationRepository;
    private final DocumentRepository documentRepository;

    public DashboardServiceImpl(
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            RecruiterRepository recruiterRepository,
            PlacementDriveRepository placementDriveRepository,
            StudentApplicationRepository studentApplicationRepository,
            DocumentRepository documentRepository) {

        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.studentApplicationRepository = studentApplicationRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public DashboardResponseDTO getAdminDashboard() {

        logger.info("Fetching admin dashboard.");

        DashboardResponseDTO dashboard =
                new DashboardResponseDTO();

        dashboard.setTotalStudents(
                studentRepository.count());

        dashboard.setTotalCompanies(
                companyRepository.count());

        dashboard.setTotalRecruiters(
                recruiterRepository.count());

        dashboard.setTotalPlacementDrives(
                placementDriveRepository.count());

        dashboard.setTotalApplications(
                studentApplicationRepository.count());

        dashboard.setDocumentsUploaded(
                documentRepository.count());

        long selectedStudents =
                studentApplicationRepository.findAll()

                        .stream()

                        .filter(application ->

                                application.getStatus()
                                        == ApplicationStatus.SELECTED)

                        .count();

        dashboard.setSelectedStudents(selectedStudents);

        long upcomingDrives =
                placementDriveRepository.findAll()

                        .stream()

                        .filter(drive ->

                                "UPCOMING".equalsIgnoreCase(
                                        drive.getStatus()))

                        .count();

        dashboard.setUpcomingDrives(upcomingDrives);

        logger.info("Dashboard loaded successfully.");

        return dashboard;
    }
         // ==========================================
        // Students by Branch
        // ==========================================

        @Override
        public List<BranchCountDTO> getStudentsByBranch() {

        logger.info("Fetching students grouped by branch.");

        return studentRepository.countStudentsByBranch();

        }

        @Override
public List<ApplicationStatusCountDTO> getApplicationsByStatus() {

    logger.info("Fetching application status statistics.");

    return studentApplicationRepository.countApplicationsByStatus();

}
}