package com.placement.portal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.placement.portal.entity.StudentApplication;
import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Application
    // ==========================================

 @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_application_id", nullable = false)
private StudentApplication studentApplication;

    // ==========================================
    // Offer Details
    // ==========================================

    @Column(nullable = false)
    private Double packageOffered;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate joiningDate;

    @Column(nullable = false)
    private String status;

    // OFFERED
    // ACCEPTED
    // REJECTED

    // ==========================================
    // Created Time
    // ==========================================

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();

        if (status == null) {

            status = "OFFERED";

        }
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

    public StudentApplication getStudentApplication(){
        return studentApplication;
    }

   public void setStudentApplication(StudentApplication studentApplication) {
        this.studentApplication = studentApplication;
    }

    public Double getPackageOffered() {
        return packageOffered;
    }

    public void setPackageOffered(Double packageOffered) {
        this.packageOffered = packageOffered;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}