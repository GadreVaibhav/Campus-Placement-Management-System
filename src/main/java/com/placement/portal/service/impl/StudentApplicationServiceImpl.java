package com.placement.portal.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.placement.portal.mapper.StudentApplicationMapper;
import com.placement.portal.dto.StudentApplicationRequestDTO;
import com.placement.portal.dto.StudentApplicationResponseDTO;
import com.placement.portal.entity.ApplicationStatus;
import com.placement.portal.entity.PlacementDrive;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.StudentApplication;
import com.placement.portal.exception.PlacementDriveNotFoundException;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.StudentApplicationService;

@Service
public class StudentApplicationServiceImpl
        implements StudentApplicationService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(
                    StudentApplicationServiceImpl.class);

    // ==========================================
    // Repositories
    // ==========================================

    private final StudentApplicationRepository studentApplicationRepository;

    private final StudentRepository studentRepository;

    private final PlacementDriveRepository placementDriveRepository;

    public StudentApplicationServiceImpl(

            StudentApplicationRepository studentApplicationRepository,

            StudentRepository studentRepository,

            PlacementDriveRepository placementDriveRepository) {

        this.studentApplicationRepository =
                studentApplicationRepository;

        this.studentRepository =
                studentRepository;

        this.placementDriveRepository =
                placementDriveRepository;
    }

// ==========================================
// Apply For Placement Drive
// ==========================================

@Override
public StudentApplicationResponseDTO applyForDrive(
        StudentApplicationRequestDTO requestDTO) {

    logger.info("Student {} applying for drive {}",
            requestDTO.getStudentId(),
            requestDTO.getPlacementDriveId());

    Student student = studentRepository.findById(
            requestDTO.getStudentId())
            .orElseThrow(() -> {

                logger.error("Student not found.");

                return new StudentNotFoundException(
                        "Student not found with ID: "
                                + requestDTO.getStudentId());
            });

    PlacementDrive drive = placementDriveRepository.findById(
            requestDTO.getPlacementDriveId())
            .orElseThrow(() -> {

                logger.error("Placement Drive not found.");

                return new PlacementDriveNotFoundException(
                        "Placement Drive not found with ID: "
                                + requestDTO.getPlacementDriveId());
            });

    // Check duplicate application

    studentApplicationRepository
            .findByStudentAndPlacementDrive(student, drive)
            .ifPresent(application -> {

                throw new IllegalArgumentException(
                        "Student has already applied for this placement drive.");
            });

    // Check registration deadline

    if (LocalDate.now().isAfter(
            drive.getRegistrationDeadline())) {

        throw new IllegalArgumentException(
                "Registration deadline has passed.");
    }

    // Check minimum CGPA

    if (student.getCgpa() < drive.getMinimumCgpa()) {

        throw new IllegalArgumentException(
                "Student is not eligible for this placement drive.");
    }

    StudentApplication application =
            new StudentApplication();

    application.setStudent(student);

    application.setPlacementDrive(drive);

    application.setStatus(ApplicationStatus.APPLIED);

    StudentApplication savedApplication =
            studentApplicationRepository.save(application);

    logger.info("Application submitted successfully.");

    return StudentApplicationMapper.toResponseDTO(
            savedApplication);
}

// ==========================================
// Get Application By ID
// ==========================================

@Override
public StudentApplicationResponseDTO getApplicationById(
        Long applicationId) {

    logger.info("Fetching application ID: {}", applicationId);

    StudentApplication application =
            studentApplicationRepository.findById(applicationId)
                    .orElseThrow(() ->

                            new IllegalArgumentException(
                                    "Application not found with ID: "
                                            + applicationId));

    return StudentApplicationMapper.toResponseDTO(application);
}

// ==========================================
// Get Applications By Student
// ==========================================

@Override
public List<StudentApplicationResponseDTO> getApplicationsByStudent(
        Long studentId) {

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->

                    new StudentNotFoundException(
                            "Student not found with ID: "
                                    + studentId));

    return studentApplicationRepository
            .findByStudent(student)

            .stream()

            .map(StudentApplicationMapper::toResponseDTO)

            .collect(Collectors.toList());
}

// ==========================================
// Get All Applications
// ==========================================

@Override
public List<StudentApplicationResponseDTO> getAllApplications() {

    return studentApplicationRepository.findAll()

            .stream()

            .map(StudentApplicationMapper::toResponseDTO)

            .collect(Collectors.toList());
}

// ==========================================
// Update Application Status
// ==========================================

@Override
public StudentApplicationResponseDTO updateApplicationStatus(

        Long applicationId,

        String status) {

    StudentApplication application =
            studentApplicationRepository.findById(applicationId)

                    .orElseThrow(() ->

                            new IllegalArgumentException(
                                    "Application not found."));

    application.setStatus(

            ApplicationStatus.valueOf(
                    status.toUpperCase()));

    StudentApplication updated =
            studentApplicationRepository.save(application);

    return StudentApplicationMapper.toResponseDTO(updated);
}

// ==========================================
// Delete Application
// ==========================================

@Override
public void deleteApplication(Long applicationId) {

    StudentApplication application =
            studentApplicationRepository.findById(applicationId)

                    .orElseThrow(() ->

                            new IllegalArgumentException(
                                    "Application not found."));

    studentApplicationRepository.delete(application);
}

@Override
public List<StudentApplicationResponseDTO> getRecentApplications() {

    Pageable pageable = PageRequest.of(0, 5);

    return studentApplicationRepository

            .findAllByOrderByApplicationDateDesc(pageable)

            .stream()

            .map(StudentApplicationMapper::toResponseDTO)

            .toList();
}
}