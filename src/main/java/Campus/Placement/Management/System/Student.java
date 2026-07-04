package Campus.Placement.Management.System;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students") // Maps this Java class directly to our SQL database table
public class Student {

   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@jakarta.persistence.Column(name = "student_id") // Add this line!
private Long id;

    private String name;
    private String email;
    private double cgpa;
    private String primaryLanguage;

    // --- No-Argument Constructor (Required by Spring) ---
    public Student() {}

    // --- All-Argument Constructor ---
    public Student(String name, String email, double cgpa, String primaryLanguage) {
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.primaryLanguage = primaryLanguage;
    }

    // --- Getters and Setters (How Java reads and writes values) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String getPrimaryLanguage() { return primaryLanguage; }
    public void setPrimaryLanguage(String primaryLanguage) { this.primaryLanguage = primaryLanguage; }
}