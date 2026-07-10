package Campus.Placement.Management.System.dto;

public class StudentResponseDTO {
    private Long studentId;
    private String name;
    private String email;
    private float cgpa;
    private String skill;
    public Long getStudentId()
    {
        return studentId;
    }
    public void setStudentId(Long studentId)
    {
        this.studentId=studentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
