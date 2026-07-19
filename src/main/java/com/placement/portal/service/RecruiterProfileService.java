package com.placement.portal.service;

import com.placement.portal.dto.RecruiterProfileRequestDTO;
import com.placement.portal.dto.RecruiterProfileResponseDTO;

public interface RecruiterProfileService {

    RecruiterProfileResponseDTO getProfile(String email);

    RecruiterProfileResponseDTO updateProfile(
            String email,
            RecruiterProfileRequestDTO requestDTO);

}