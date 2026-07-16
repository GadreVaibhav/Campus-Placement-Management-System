package com.placement.portal.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {

    // ==========================
    // Name
    // ==========================

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50,
            message = "Name must be between 3 and 50 characters")
    private String name;

    // ==========================
    // Email
    // ==========================

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    // ==========================
    // Phone
    // ==========================

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit Indian phone number"
    )
    private String phone;

    // ==========================
    // Branch
    // ==========================

    @NotBlank(message = "Branch is required")
    @Size(max = 50,
            message = "Branch cannot exceed 50 characters")
    private String branch;

    // ==========================
    // Graduation Year
    // ==========================

    @NotNull(message = "Graduation year is required")
    @Min(value = 2024,
            message = "Graduation year is invalid")
    @Max(value = 2035,
            message = "Graduation year is invalid")
    private Integer graduationYear;

    // ==========================
    // Primary Language
    // ==========================

    @NotBlank(message = "Primary language is required")
    @Size(max = 30,
            message = "Primary language cannot exceed 30 characters")
    private String primaryLanguage;

    // ==========================
    // CGPA
    // ==========================

    @NotNull(message = "CGPA is required")
    @DecimalMin(
            value = "0.0",
            message = "CGPA cannot be less than 0"
    )
    @DecimalMax(
            value = "10.0",
            message = "CGPA cannot be greater than 10"
    )
    private Float cgpa;

    // ==========================
    // Skill
    // ==========================

    @NotBlank(message = "Skill is required")
    @Size(max = 100,
            message = "Skill cannot exceed 100 characters")
    private String skill;

    // ==========================
    // 10th Percentage
    // ==========================

    @NotNull(message = "10th percentage is required")
    @DecimalMin(
            value = "0.0",
            message = "10th percentage cannot be less than 0"
    )
    @DecimalMax(
            value = "100.0",
            message = "10th percentage cannot exceed 100"
    )
    private Float tenthPercentage;

    // ==========================
    // 12th Percentage
    // ==========================

    @NotNull(message = "12th percentage is required")
    @DecimalMin(
            value = "0.0",
            message = "12th percentage cannot be less than 0"
    )
    @DecimalMax(
            value = "100.0",
            message = "12th percentage cannot exceed 100"
    )
    private Float twelfthPercentage;

    // ==========================
    // Current Backlogs
    // ==========================

    @NotNull(message = "Current backlogs are required")
    @Min(
            value = 0,
            message = "Current backlogs cannot be negative"
    )
    @Max(
            value = 20,
            message = "Current backlogs cannot exceed 20"
    )
    private Integer currentBacklogs;

    // ==========================
    // Total Backlogs
    // ==========================

    @NotNull(message = "Total backlogs are required")
    @Min(
            value = 0,
            message = "Total backlogs cannot be negative"
    )
    @Max(
            value = 20,
            message = "Total backlogs cannot exceed 20"
    )
    private Integer totalBacklogs;

    public StudentRequestDTO() {
    }

   
    // ==========================
    // Getters and Setters
    // ==========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Float getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(Float tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }

    public Float getTwelfthPercentage() {
        return twelfthPercentage;
    }

    public void setTwelfthPercentage(Float twelfthPercentage) {
        this.twelfthPercentage = twelfthPercentage;
    }

    public Integer getCurrentBacklogs() {
        return currentBacklogs;
    }

    public void setCurrentBacklogs(Integer currentBacklogs) {
        this.currentBacklogs = currentBacklogs;
    }

    public Integer getTotalBacklogs() {
        return totalBacklogs;
    }

    public void setTotalBacklogs(Integer totalBacklogs) {
        this.totalBacklogs = totalBacklogs;
    }
}