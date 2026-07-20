package com.placement.portal.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequestDTO {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Package is required")
    @DecimalMin(value = "0.0", message = "Package cannot be negative")
    private Double packageLpa;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Eligibility CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA cannot be less than 0")
    @DecimalMax(value = "10.0", message = "CGPA cannot be greater than 10")
    private Float eligibilityCgpa;

    @NotBlank(message = "Skills are required")
    private String skillsRequired;

    @NotNull(message = "Last date is required")
    @Future(message = "Last date must be in the future")
  
    private LocalDate lastDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Company is required")
    private Long companyId;

    @NotNull(message = "Recruiter is required")
    private Long recruiterId;

    public JobRequestDTO() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPackageLpa() {
        return packageLpa;
    }

    public void setPackageLpa(Double packageLpa) {
        this.packageLpa = packageLpa;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getEligibilityCgpa() {
        return eligibilityCgpa;
    }

    public void setEligibilityCgpa(Float eligibilityCgpa) {
        this.eligibilityCgpa = eligibilityCgpa;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }
}