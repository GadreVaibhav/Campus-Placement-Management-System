package com.placement.portal.dto;

public class AdminDashboardResponseDTO {

    private long totalStudents;

    private long totalCompanies;

    private long totalRecruiters;

    private long totalPlacementDrives;

    private long totalApplications;

    private long placedStudents;

    private double placementPercentage;

    public AdminDashboardResponseDTO() {
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

    public long getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(long placedStudents) {
        this.placedStudents = placedStudents;
    }

    public double getPlacementPercentage() {
        return placementPercentage;
    }

    public void setPlacementPercentage(double placementPercentage) {
        this.placementPercentage = placementPercentage;
    }
}