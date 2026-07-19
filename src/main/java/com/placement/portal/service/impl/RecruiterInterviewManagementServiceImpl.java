package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.RecruiterInterviewRequestDTO;
import com.placement.portal.dto.RecruiterInterviewResponseDTO;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Interview;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.exception.InterviewNotFoundException;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.RecruiterInterviewManagementService;

@Service
public class RecruiterInterviewManagementServiceImpl
        implements RecruiterInterviewManagementService {

    private final InterviewRepository interviewRepository;

    private final ApplicationRepository applicationRepository;

    private final RecruiterRepository recruiterRepository;

    public RecruiterInterviewManagementServiceImpl(

            InterviewRepository interviewRepository,

            ApplicationRepository applicationRepository,

            RecruiterRepository recruiterRepository) {

        this.interviewRepository = interviewRepository;

        this.applicationRepository = applicationRepository;

        this.recruiterRepository = recruiterRepository;
    }

    // =======================================================
    // GET ALL INTERVIEWS
    // =======================================================

    @Override
    public List<RecruiterInterviewResponseDTO> getRecruiterInterviews(
            String recruiterEmail) {

        Recruiter recruiter = recruiterRepository
                .findByEmail(recruiterEmail)
                .orElseThrow(() ->
                        new RuntimeException("Recruiter not found"));

        return interviewRepository.findAll()

                .stream()

                .filter(interview ->
                        interview.getApplication()
                                .getJob()
                                .getRecruiter()
                                .getId()
                                .equals(recruiter.getId()))

                .map(this::convertToDTO)

                .toList();
    }

    // =======================================================
    // CREATE
    // =======================================================

    @Override
    public RecruiterInterviewResponseDTO scheduleInterview(

            RecruiterInterviewRequestDTO requestDTO,

            String recruiterEmail) {

        Application application =
                applicationRepository.findById(
                        requestDTO.getApplicationId())

                        .orElseThrow(() ->
                                new InterviewNotFoundException(
                                        "Application not found"));

        Interview interview = new Interview();

        interview.setApplication(application);

        interview.setInterviewTime(
                requestDTO.getInterviewTime());

        interview.setInterviewMode(
                requestDTO.getInterviewMode());

        interview.setInterviewerName(
                requestDTO.getInterviewerName());

        interview.setFeedback(
                requestDTO.getFeedback());

        return convertToDTO(

                interviewRepository.save(interview));
    }

    // =======================================================
    // UPDATE
    // =======================================================

    @Override
    public RecruiterInterviewResponseDTO updateInterview(

            Long interviewId,

            RecruiterInterviewRequestDTO requestDTO) {

        Interview interview =
                interviewRepository.findById(interviewId)

                        .orElseThrow(() ->
                                new InterviewNotFoundException(
                                        "Interview not found"));

        interview.setInterviewTime(
                requestDTO.getInterviewTime());

        interview.setInterviewMode(
                requestDTO.getInterviewMode());

        interview.setInterviewerName(
                requestDTO.getInterviewerName());

        interview.setFeedback(
                requestDTO.getFeedback());

        return convertToDTO(

                interviewRepository.save(interview));
    }

    // =======================================================
    // DELETE
    // =======================================================

    @Override
    public void deleteInterview(Long interviewId) {

        Interview interview =
                interviewRepository.findById(interviewId)

                        .orElseThrow(() ->
                                new InterviewNotFoundException(
                                        "Interview not found"));

        interviewRepository.delete(interview);
    }

    // =======================================================
    // DTO
    // =======================================================

    private RecruiterInterviewResponseDTO convertToDTO(
            Interview interview) {

        RecruiterInterviewResponseDTO dto =
                new RecruiterInterviewResponseDTO();

        dto.setInterviewId(interview.getId());

        dto.setApplicationId(
                interview.getApplication().getId());

        dto.setStudentName(
                interview.getApplication()
                        .getStudent()
                        .getName());

        dto.setStudentEmail(
                interview.getApplication()
                        .getStudent()
                        .getEmail());

        dto.setCompanyName(
                interview.getApplication()
                        .getJob()
                        .getCompany()
                        .getCompanyName());

        dto.setJobTitle(
                interview.getApplication()
                        .getJob()
                        .getJobTitle());

        dto.setInterviewTime(
                interview.getInterviewTime());

        dto.setInterviewMode(
                interview.getInterviewMode());

        dto.setInterviewerName(
                interview.getInterviewerName());

        dto.setFeedback(
                interview.getFeedback());

        return dto;
    }
}