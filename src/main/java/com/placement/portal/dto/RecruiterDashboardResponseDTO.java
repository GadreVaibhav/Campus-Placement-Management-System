package com.placement.portal.dto;

public class RecruiterDashboardResponseDTO {

    private Long totalDrives;
    private Long activeDrives;
    private Long totalApplications;
    private Long scheduledInterviews;

    public RecruiterDashboardResponseDTO() {
    }

    public Long getTotalDrives() {
        return totalDrives;
    }

    public void setTotalDrives(Long totalDrives) {
        this.totalDrives = totalDrives;
    }

    public Long getActiveDrives() {
        return activeDrives;
    }

    public void setActiveDrives(Long activeDrives) {
        this.activeDrives = activeDrives;
    }

    public Long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public Long getScheduledInterviews() {
        return scheduledInterviews;
    }

    public void setScheduledInterviews(Long scheduledInterviews) {
        this.scheduledInterviews = scheduledInterviews;
    }
}