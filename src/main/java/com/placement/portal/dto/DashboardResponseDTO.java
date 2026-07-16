package com.placement.portal.dto;

public class DashboardResponseDTO {

    private long totalStudents;

    private long totalCompanies;

    private long totalRecruiters;

    private long totalPlacementDrives;

    private long totalApplications;

    private long selectedStudents;

    private long upcomingDrives;

    private long documentsUploaded;

    public DashboardResponseDTO() {
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(long totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public long getTotalRecruiters() {
        return totalRecruiters;
    }

    public void setTotalRecruiters(long totalRecruiters) {
        this.totalRecruiters = totalRecruiters;
    }

    public long getTotalPlacementDrives() {
        return totalPlacementDrives;
    }

    public void setTotalPlacementDrives(long totalPlacementDrives) {
        this.totalPlacementDrives = totalPlacementDrives;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(long selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public long getUpcomingDrives() {
        return upcomingDrives;
    }

    public void setUpcomingDrives(long upcomingDrives) {
        this.upcomingDrives = upcomingDrives;
    }

    public long getDocumentsUploaded() {
        return documentsUploaded;
    }

    public void setDocumentsUploaded(long documentsUploaded) {
        this.documentsUploaded = documentsUploaded;
    }
}