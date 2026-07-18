package com.placement.portal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.placement.portal.entity.Application;
@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @OneToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
    private LocalDateTime interviewTime;
    private String interviewMode; 
    private String interviewerName;
    private String feedback; 

    // Default constructor
    public Interview() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

   public Application getApplication() {
    return application;
}

public void setApplication(Application application) {
    this.application = application;
}

    public LocalDateTime getInterviewTime() { return interviewTime; }
    public void setInterviewTime(LocalDateTime interviewTime) { this.interviewTime = interviewTime; }

    public String getInterviewMode() { return interviewMode; }
    public void setInterviewMode(String interviewMode) { this.interviewMode = interviewMode; }

    public String getInterviewerName() { return interviewerName; }
    public void setInterviewerName(String interviewerName) { this.interviewerName = interviewerName; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}