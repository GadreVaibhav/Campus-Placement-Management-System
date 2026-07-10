package Campus.Placement.Management.System.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;
    private String email;
    private float cgpa;
    private String primaryLanguage; // E.g., Java, Python
    
    // Linking the Student to the User authentication account
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Default Constructor
    public Student() {}
    private String skill; // Make sure this matches the field name exactly

    // Add this getter method
    public String getSkill() {
        return skill;
    }

    // Add this setter method
    public void setSkill(String skill) {
        this.skill = skill;
    }
    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public float getCgpa() { return cgpa; }
    public void setCgpa(float cgpa) { this.cgpa = cgpa; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}