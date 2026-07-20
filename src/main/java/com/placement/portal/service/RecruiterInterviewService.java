package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.UpcomingInterviewDTO;

public interface RecruiterInterviewService {

    List<UpcomingInterviewDTO> getUpcomingInterviews(
            String recruiterEmail);

}