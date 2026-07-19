package com.placement.portal.dto;

public class RecentApplicationDTO {

    private String studentName;
    private String companyName;
    private String jobRole;
    private String status;

    public RecentApplicationDTO() {
    }

    public RecentApplicationDTO(
            String studentName,
            String companyName,
            String jobRole,
            String status) {

        this.studentName = studentName;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}