package Campus.Placement.Management.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "min_cgpa_required")
    private Double minCgpaRequired;

    @Column(name = "required_skill")
    private String requiredSkill;

    @Column(name = "package_lpa")
    private Double packageLpa;

    // Constructor required by Spring
    public Company() {}

    public Company(String companyName, Double minCgpaRequired, String requiredSkill, Double packageLpa) {
        this.companyName = companyName;
        this.minCgpaRequired = minCgpaRequired;
        this.requiredSkill = requiredSkill;
        this.packageLpa = packageLpa;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Double getMinCgpaRequired() { return minCgpaRequired; }
   public void setMinCgpaRequired(Double minCgpaRequired) { // Use Double
        this.minCgpaRequired = minCgpaRequired; 
    }

    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }

    public Double getPackageLpa() { return packageLpa; }
   public void setPackageLpa(Double packageLpa) { // Use Double
        this.packageLpa = packageLpa; 
    }
}