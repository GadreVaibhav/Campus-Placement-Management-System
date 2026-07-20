package com.placement.portal.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.placement.portal.dto.ApplicationResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Job;
import com.placement.portal.entity.Student;
import com.placement.portal.exception.JobNotFoundException;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.mapper.ApplicationMapper;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.JobRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.ApplicationService;
import com.placement.portal.entity.PlacementDrive;
import com.placement.portal.entity.StudentApplication;
import com.placement.portal.entity.ApplicationStatus;

import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.StudentApplicationRepository;
@Service
public class ApplicationServiceImpl
        implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final StudentRepository studentRepository;

    private final JobRepository jobRepository;
                private final PlacementDriveRepository placementDriveRepository;

private final StudentApplicationRepository studentApplicationRepository;
    public ApplicationServiceImpl(

        ApplicationRepository applicationRepository,

        StudentRepository studentRepository,

        JobRepository jobRepository,

        PlacementDriveRepository placementDriveRepository,

        StudentApplicationRepository studentApplicationRepository) {

    this.applicationRepository = applicationRepository;
    this.studentRepository = studentRepository;
    this.jobRepository = jobRepository;
    this.placementDriveRepository = placementDriveRepository;
    this.studentApplicationRepository = studentApplicationRepository;
}

    // ==========================================
    // Apply Job
    // ==========================================

    @Override
    public ApplicationResponseDTO applyJob(

            String studentEmail,

            Long jobId) {

        Student student = studentRepository
                .findByUserEmail(studentEmail)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found"));

        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"));

        // ----------------------------
        // Already Applied
        // ----------------------------

        if (applicationRepository.existsByStudentAndJob(
                student,
                job)) {

            throw new RuntimeException(
                    "You have already applied for this job.");
        }

        // ----------------------------
        // Job Closed
        // ----------------------------

        if (!"OPEN".equalsIgnoreCase(job.getStatus())) {

            throw new RuntimeException(
                    "Job is closed.");
        }

        // ----------------------------
        // Last Date Passed
        // ----------------------------

        if (job.getLastDate().isBefore(LocalDate.now())) {

            throw new RuntimeException(
                    "Application deadline has passed.");
        }

        // ----------------------------
        // Eligibility
        // ----------------------------

        if (student.getCgpa() <
                job.getEligibilityCgpa()) {

            throw new RuntimeException(
                    "You are not eligible for this job.");
        }

        // ----------------------------
        // Save Application
        // ----------------------------

        Application application =
                new Application();

        application.setStudent(student);

        application.setJob(job);

        application.setStatus("APPLIED");

        Application saved =
                applicationRepository.save(application);
                
// =====================================
// ALSO SAVE INTO student_applications
// =====================================

// Find Placement Drive that belongs to the same company
PlacementDrive drive =
        placementDriveRepository
                .findFirstByCompanyAndJobRole(
                        job.getCompany(),
                        job.getJobTitle())
                .orElse(null);

if (drive != null) {

    boolean alreadyExists =
            studentApplicationRepository

                    .findAll()

                    .stream()

                    .anyMatch(app ->

                            app.getStudent().getStudentId()

                                    .equals(student.getStudentId())

                                    &&

                                    app.getPlacementDrive().getId()

                                            .equals(drive.getId()));

    if (!alreadyExists) {

        StudentApplication studentApplication =
                new StudentApplication();

        studentApplication.setStudent(student);

        studentApplication.setPlacementDrive(drive);

        studentApplication.setStatus(ApplicationStatus.APPLIED);

        studentApplicationRepository.save(studentApplication);
    }
}
        return ApplicationMapper.toResponseDTO(saved);
    }

    // ==========================================
    // My Applications
    // ==========================================

    @Override
    public List<ApplicationResponseDTO>
            getMyApplications(String studentEmail) {

        Student student = studentRepository
                .findByUserEmail(studentEmail)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found"));

        return applicationRepository

                .findByStudent(student)

                .stream()

                .map(ApplicationMapper::toResponseDTO)

                .toList();
    }


@Override
public ApplicationResponseDTO getApplicationById(Long applicationId) {

    Application application = applicationRepository

            .findById(applicationId)

            .orElseThrow(() ->
                    new RuntimeException("Application not found"));

    return ApplicationMapper.toResponseDTO(application);
}

@Override
public void deleteApplication(Long applicationId) {

    Application application = applicationRepository

            .findById(applicationId)

            .orElseThrow(() ->
                    new RuntimeException("Application not found"));

    applicationRepository.delete(application);
}
@Override
public List<ApplicationResponseDTO> getRecentApplications() {

    return applicationRepository.findAll()

            .stream()

            .sorted((a, b) ->
                    b.getAppliedAt().compareTo(a.getAppliedAt()))

            .limit(5)

            .map(ApplicationMapper::toResponseDTO)

            .toList();
}
@Override
public List<ApplicationResponseDTO> getAllApplications() {

    return applicationRepository.findAll()

            .stream()

            .map(ApplicationMapper::toResponseDTO)

            .toList();
}
@Override
public ApplicationResponseDTO updateApplicationStatus(

        Long applicationId,

        String status) {

    Application application =

            applicationRepository.findById(applicationId)

                    .orElseThrow(() ->

                            new RuntimeException(
                                    "Application not found."));

    application.setStatus(status);

    Application updated =

            applicationRepository.save(application);

    return ApplicationMapper.toResponseDTO(updated);

}
@Override
public List<ApplicationResponseDTO> getMyRecentApplications(String studentEmail) {

    Student student = studentRepository
            .findByUserEmail(studentEmail)
            .orElseThrow(() ->
                    new StudentNotFoundException("Student not found"));

    return applicationRepository

            .findTop5ByStudentOrderByAppliedAtDesc(student)

            .stream()

            .map(ApplicationMapper::toResponseDTO)

            .toList();
}
}