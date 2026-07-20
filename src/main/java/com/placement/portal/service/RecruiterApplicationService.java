package com.placement.portal.service;

import java.util.List;

import com.placement.portal.dto.RecentApplicationDTO;
import com.placement.portal.dto.RecruiterApplicationResponseDTO;

public interface RecruiterApplicationService {

    List<RecentApplicationDTO> getRecentApplications(
        String recruiterEmail);

    List<RecruiterApplicationResponseDTO> getRecruiterApplications(
            String recruiterEmail);

}