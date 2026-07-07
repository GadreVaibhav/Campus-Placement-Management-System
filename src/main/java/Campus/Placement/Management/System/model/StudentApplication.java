package Campus.Placement.Management.System.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_applications")
public class StudentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Connects to the Student who is applying
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Connects to the Drive they are applying for
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drive_id", nullable = false)
    private PlacementDrive placementDrive;

    private String applicationStatus; // e.g., "APPLIED", "SHORTLISTED", "INTERVIEW", "OFFERED", "REJECTED"

    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate() {
        this.appliedAt = LocalDateTime.now(); // Automatically logs the exact time they hit "Apply"
    }

    public StudentApplication() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public PlacementDrive getPlacementDrive() { return placementDrive; }
    public void setPlacementDrive(PlacementDrive placementDrive) { this.placementDrive = placementDrive; }

    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}