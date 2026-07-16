package com.placement.portal.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "companies")
public class Company {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Company Details
    // ==========================================

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String email;

    private String website;

    @Column(nullable = false)
    private String industry;

    @Column(nullable = false)
    private String location;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ==========================================
    // Company -> Recruiters
    // ==========================================

    // @OneToMany(
    //         mappedBy = "company",
    //         cascade = CascadeType.ALL,
    //         orphanRemoval = true)
    // @JsonManagedReference
    // private List<Recruiter> recruiters = new ArrayList<>();

    // ==========================================
    // Automatically set Created Time
    // ==========================================

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // ==========================================
    // Constructors
    // ==========================================

    public Company() {
    }

    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // public List<Recruiter> getRecruiters() {
    //     return recruiters;
    // }

    // public void setRecruiters(List<Recruiter> recruiters) {
    //     this.recruiters = recruiters;
    // }
}