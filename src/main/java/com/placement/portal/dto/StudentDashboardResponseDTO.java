package com.placement.portal.dto;
public class StudentDashboardResponseDTO {

    private long appliedDrives;

    private long upcomingDrives;

    private long interviews;

    private long selectedDrives;

    public StudentDashboardResponseDTO() {
    }

    public long getAppliedDrives() {
        return appliedDrives;
    }

    public void setAppliedDrives(long appliedDrives) {
        this.appliedDrives = appliedDrives;
    }

    public long getUpcomingDrives() {
        return upcomingDrives;
    }

    public void setUpcomingDrives(long upcomingDrives) {
        this.upcomingDrives = upcomingDrives;
    }

    public long getInterviews() {
        return interviews;
    }

    public void setInterviews(long interviews) {
        this.interviews = interviews;
    }

    public long getSelectedDrives() {
        return selectedDrives;
    }

    public void setSelectedDrives(long selectedDrives) {
        this.selectedDrives = selectedDrives;
    }

}