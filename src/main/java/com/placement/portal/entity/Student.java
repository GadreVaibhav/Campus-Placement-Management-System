package com.placement.portal.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    private String email;

    private String phone;

    private String branch;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @Column(name = "primary_language")
    private String primaryLanguage;

    private float cgpa;

    private String skill;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name = "tenth_percentage")
    private Float tenthPercentage;

    @Column(name = "twelfth_percentage")
    private Float twelfthPercentage;

    @Column(name = "current_backlogs")
    private Integer currentBacklogs;

    @Column(name = "total_backlogs")
    private Integer totalBacklogs;

    @Column(name = "is_placed")
    private Boolean isPlaced;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(
    mappedBy = "student",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
@JsonManagedReference
private List<Document> documents;

    public Student() {
    }

    // -------------------------
    // Student ID
    // -------------------------

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    // -------------------------
    // Name
    // -------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -------------------------
    // Email
    // -------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // -------------------------
    // Phone
    // -------------------------

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // -------------------------
    // Branch
    // -------------------------

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // -------------------------
    // Graduation Year
    // -------------------------

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    // -------------------------
    // Primary Language
    // -------------------------

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    // -------------------------
    // CGPA
    // -------------------------

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    // -------------------------
    // Skill
    // -------------------------

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    // -------------------------
    // Resume URL
    // -------------------------

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    // -------------------------
    // 10th Percentage
    // -------------------------

    public Float getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(Float tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }

    // -------------------------
    // 12th Percentage
    // -------------------------

    public Float getTwelfthPercentage() {
        return twelfthPercentage;
    }

    public void setTwelfthPercentage(Float twelfthPercentage) {
        this.twelfthPercentage = twelfthPercentage;
    }

    // -------------------------
    // Current Backlogs
    // -------------------------

    public Integer getCurrentBacklogs() {
        return currentBacklogs;
    }

    public void setCurrentBacklogs(Integer currentBacklogs) {
        this.currentBacklogs = currentBacklogs;
    }

    // -------------------------
    // Total Backlogs
    // -------------------------

    public Integer getTotalBacklogs() {
        return totalBacklogs;
    }

    public void setTotalBacklogs(Integer totalBacklogs) {
        this.totalBacklogs = totalBacklogs;
    }

    // -------------------------
    // Placement Status
    // -------------------------

    public Boolean getIsPlaced() {
        return isPlaced;
    }

    public void setIsPlaced(Boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    // -------------------------
    // User
    // -------------------------

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // -------------------------
    // Documents
    // -------------------------

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}