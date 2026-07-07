package Campus.Placement.Management.System.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "placement_drives")
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Connects this drive to a specific Company
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String jobRole;
    private Double packageOffered; // Using Double (Object) to prevent null mapping errors!
    private Double minimumCgpa; 
    
    private LocalDate driveDate;
    private LocalDate registrationDeadline;

    private String status; // e.g., "UPCOMING", "LIVE", "COMPLETED", "CANCELLED"

    // Default constructor for JPA
    public PlacementDrive() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
    
    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }
    
    public Double getPackageOffered() { return packageOffered; }
    public void setPackageOffered(Double packageOffered) { this.packageOffered = packageOffered; }
    
    public Double getMinimumCgpa() { return minimumCgpa; }
    public void setMinimumCgpa(Double minimumCgpa) { this.minimumCgpa = minimumCgpa; }
    
    public LocalDate getDriveDate() { return driveDate; }
    public void setDriveDate(LocalDate driveDate) { this.driveDate = driveDate; }
    
    public LocalDate getRegistrationDeadline() { return registrationDeadline; }
    public void setRegistrationDeadline(LocalDate registrationDeadline) { this.registrationDeadline = registrationDeadline; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}