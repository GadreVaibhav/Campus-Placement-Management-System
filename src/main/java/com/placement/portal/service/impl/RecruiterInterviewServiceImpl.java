package com.placement.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.UpcomingInterviewDTO;
import com.placement.portal.entity.Interview;
import com.placement.portal.repository.InterviewRepository;
import com.placement.portal.service.RecruiterInterviewService;

@Service
public class RecruiterInterviewServiceImpl
        implements RecruiterInterviewService {

    private final InterviewRepository interviewRepository;

    public RecruiterInterviewServiceImpl(
            InterviewRepository interviewRepository) {

        this.interviewRepository = interviewRepository;
    }

    @Override
    public List<UpcomingInterviewDTO> getUpcomingInterviews() {

        return interviewRepository
                .findTop5ByOrderByInterviewTimeAsc()
                .stream()
                .map(this::convert)
                .toList();
    }

    private UpcomingInterviewDTO convert(Interview interview) {

        UpcomingInterviewDTO dto =
                new UpcomingInterviewDTO();

        dto.setInterviewId(interview.getId());

        dto.setInterviewTime(interview.getInterviewTime());

        dto.setInterviewMode(interview.getInterviewMode());

        dto.setStudentName(
                interview.getApplication()
                        .getStudent()
                        .getName());

        dto.setCompanyName(
                interview.getApplication()
                        .getJob()
                        .getCompany()
                        .getCompanyName());

        dto.setJobTitle(
                interview.getApplication()
                        .getJob()
                        .getJobTitle());

        return dto;
    }
}