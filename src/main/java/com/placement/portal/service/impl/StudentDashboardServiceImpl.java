package com.placement.portal.service.impl;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.StudentDashboardResponseDTO;
import com.placement.portal.entity.ApplicationStatus;
import com.placement.portal.entity.Student;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.repository.PlacementDriveRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.StudentDashboardService;

@Service
public class StudentDashboardServiceImpl
        implements StudentDashboardService {

    private final StudentRepository studentRepository;

    private final StudentApplicationRepository applicationRepository;

    private final PlacementDriveRepository placementDriveRepository;

    private final InterviewRepository interviewRepository;

    public StudentDashboardServiceImpl(

            StudentRepository studentRepository,

            StudentApplicationRepository applicationRepository,

            PlacementDriveRepository placementDriveRepository,

            InterviewRepository interviewRepository) {

        this.studentRepository = studentRepository;

        this.applicationRepository = applicationRepository;

        this.placementDriveRepository = placementDriveRepository;

        this.interviewRepository = interviewRepository;
    }

    @Override
    public StudentDashboardResponseDTO
            getStudentDashboard(Long studentId) {

        Student student =
                studentRepository.findById(studentId)

                        .orElseThrow(() ->

                                new StudentNotFoundException(
                                        "Student not found."));

        StudentDashboardResponseDTO dto =
                new StudentDashboardResponseDTO();

        dto.setAppliedDrives(

                applicationRepository.countByStudent(student));

        dto.setSelectedDrives(

                applicationRepository.countByStudentAndStatus(

                        student,

                        ApplicationStatus.SELECTED));

        dto.setUpcomingDrives(

                placementDriveRepository

                        .findByStatusIgnoreCase("UPCOMING")

                        .size());

        dto.setInterviews(

                interviewRepository

                        .countByStudentApplicationStudent(student));

        return dto;

    }

}