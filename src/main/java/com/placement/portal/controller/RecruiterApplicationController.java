package com.placement.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.placement.portal.dto.RecruiterApplicationResponseDTO;
import com.placement.portal.dto.RecentApplicationDTO;
import com.placement.portal.service.RecruiterApplicationService;

@RestController
@RequestMapping("/api/recruiter")
@CrossOrigin(origins = "http://localhost:3000")
public class RecruiterApplicationController {

    private final RecruiterApplicationService recruiterApplicationService;

    public RecruiterApplicationController(
            RecruiterApplicationService recruiterApplicationService) {

        this.recruiterApplicationService = recruiterApplicationService;
    }

  @GetMapping("/applications/recent")
public List<RecentApplicationDTO> getRecentApplications(
        Authentication authentication) {

    return recruiterApplicationService.getRecentApplications(
            authentication.getName());

}
    @GetMapping("/applications")
public List<RecruiterApplicationResponseDTO> getRecruiterApplications(

        Authentication authentication) {

    return recruiterApplicationService.getRecruiterApplications(

            authentication.getName());
}


}