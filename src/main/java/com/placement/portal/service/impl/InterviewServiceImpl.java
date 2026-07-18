package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.placement.portal.mapper.InterviewMapper;
import com.placement.portal.dto.InterviewRequestDTO;
import com.placement.portal.dto.InterviewResponseDTO;
import com.placement.portal.entity.Interview;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.service.InterviewService;
import com.placement.portal.entity.Application;
import com.placement.portal.repository.ApplicationRepository;
import com.placement.portal.entity.Student;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.repository.StudentRepository;
@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

   private final ApplicationRepository applicationRepository;

   private final StudentRepository studentRepository;

    public InterviewServiceImpl(
        InterviewRepository interviewRepository,
        ApplicationRepository applicationRepository,
        StudentRepository studentRepository) {

    this.interviewRepository = interviewRepository;
    this.applicationRepository = applicationRepository;
    this.studentRepository = studentRepository;
}

                @Override
                public InterviewResponseDTO scheduleInterview(
                        Long applicationId,
                        InterviewRequestDTO requestDTO) {

                Application application =
                        applicationRepository.findById(applicationId)
                                .orElseThrow(() ->
                                        new IllegalArgumentException("Application not found"));

                // Check if interview already exists
                Interview interview = interviewRepository.findByApplicationId(applicationId);

                // Create only if it doesn't exist
                if (interview == null) {
                        interview = new Interview();
                        interview.setApplication(application);
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
        interviewRepository.findByApplicationId(applicationId);

        if (interview == null) {
            throw new RuntimeException("Interview not found");
        }

        return InterviewMapper.toResponseDTO(interview);
    }
    @Override
public boolean interviewExists(Long applicationId) {

    return interviewRepository
        .findByApplicationId(applicationId) != null;

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
@Override
public List<InterviewResponseDTO> getMyInterviews(String studentEmail) {

    Student student = studentRepository
            .findByUserEmail(studentEmail)
            .orElseThrow(() ->
                    new StudentNotFoundException(
                            "Student not found"));

    return interviewRepository

            .findByApplicationStudent(student)

            .stream()

            .map(InterviewMapper::toResponseDTO)

            .toList();
}

}