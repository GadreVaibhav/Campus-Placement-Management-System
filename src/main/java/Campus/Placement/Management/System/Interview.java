package Campus.Placement.Management.System;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long id;

    // Many interviews can belong to one student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many interviews can be conducted by one company
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "interview_date_time")
    private LocalDateTime interviewDateTime;

    @Column(name = "status") // e.g., SCHEDULED, COMPLETED, CANCELLED
    private String status;

    public Interview() {}

    public Interview(Student student, Company company, LocalDateTime interviewDateTime, String status) {
        this.student = student;
        this.company = company;
        this.interviewDateTime = interviewDateTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public LocalDateTime getInterviewDateTime() { return interviewDateTime; }
    public void setInterviewDateTime(LocalDateTime interviewDateTime) { this.interviewDateTime = interviewDateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}