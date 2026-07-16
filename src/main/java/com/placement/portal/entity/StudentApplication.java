package com.placement.portal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(
        name = "student_applications",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id",
                                "drive_id"
                        })
        })
public class StudentApplication {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Student
    // ==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false)
    private Student student;

    // ==========================================
    // Placement Drive
    // ==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "drive_id",
            nullable = false)
    private PlacementDrive placementDrive;

    // ==========================================
    // Application Status
    // ==========================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    // ==========================================
    // Applied Time
    // ==========================================

    @Column(nullable = false, updatable = false)
    private LocalDateTime applicationDate;

    // ==========================================
    // Automatically Set Date
    // ==========================================

    @PrePersist
    public void prePersist() {

        applicationDate = LocalDateTime.now();

        if (status == null) {
            status = ApplicationStatus.APPLIED;
        }
    }

    // ==========================================
    // Constructors
    // ==========================================

    public StudentApplication() {
    }

    // ==========================================
    // Getters & Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PlacementDrive getPlacementDrive() {
        return placementDrive;
    }

    public void setPlacementDrive(
            PlacementDrive placementDrive) {
        this.placementDrive = placementDrive;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(
            ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(
            LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}