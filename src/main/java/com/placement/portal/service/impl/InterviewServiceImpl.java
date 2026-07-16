package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.placement.portal.mapper.InterviewMapper;
import com.placement.portal.dto.InterviewRequestDTO;
import com.placement.portal.dto.InterviewResponseDTO;
import com.placement.portal.entity.Interview;
import com.placement.portal.entity.StudentApplication;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.repository.StudentApplicationRepository;
import com.placement.portal.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    private final StudentApplicationRepository applicationRepository;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            StudentApplicationRepository applicationRepository) {

        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
    }

   @Override
public InterviewResponseDTO scheduleInterview(
        Long applicationId,
        InterviewRequestDTO requestDTO) {

    StudentApplication application =
            applicationRepository.findById(applicationId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Application not found"));

    // Check if interview already exists
    Interview interview =
            interviewRepository.findByStudentApplicationId(applicationId);

    // Create only if it doesn't exist
    if (interview == null) {

        interview = new Interview();
        interview.setStudentApplication(application);

    }

    interview.setInterviewTime(requestDTO.getInterviewTime());
    interview.setInterviewMode(requestDTO.getInterviewMode());
    interview.setInterviewerName(requestDTO.getInterviewerName());
    interview.setFeedback(requestDTO.getFeedback());

    Interview saved = interviewRepository.save(interview);

    return InterviewMapper.toResponseDTO(saved);
}

    @Override
    public InterviewResponseDTO getInterviewByApplication(
            Long applicationId) {

        Interview interview =
                interviewRepository.findByStudentApplicationId(applicationId);

        if (interview == null) {
            throw new RuntimeException("Interview not found");
        }

        return InterviewMapper.toResponseDTO(interview);
    }
    @Override
public boolean interviewExists(Long applicationId) {

    return interviewRepository
            .findByStudentApplicationId(applicationId) != null;

}

// ==========================================
// Get All Interviews
// ==========================================

@Override
public List<InterviewResponseDTO> getAllInterviews() {

    return interviewRepository.findAll()

            .stream()

            .map(InterviewMapper::toResponseDTO)

            .toList();

}

// ==========================================
// Update Interview
// ==========================================

@Override
public InterviewResponseDTO updateInterview(

        Long interviewId,

        InterviewRequestDTO requestDTO) {

    Interview interview = interviewRepository.findById(interviewId)

            .orElseThrow(() ->

                    new RuntimeException(
                            "Interview not found."));

    interview.setInterviewTime(
            requestDTO.getInterviewTime());

    interview.setInterviewMode(
            requestDTO.getInterviewMode());

    interview.setInterviewerName(
            requestDTO.getInterviewerName());

    interview.setFeedback(
            requestDTO.getFeedback());

    Interview updated =
            interviewRepository.save(interview);

    return InterviewMapper.toResponseDTO(updated);

}

// ==========================================
// Delete Interview
// ==========================================

@Override
public void deleteInterview(Long interviewId) {

    Interview interview =
            interviewRepository.findById(interviewId)

                    .orElseThrow(() ->

                            new RuntimeException(
                                    "Interview not found."));

    interviewRepository.delete(interview);

}

}