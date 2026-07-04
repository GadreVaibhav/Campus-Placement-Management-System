package Campus.Placement.Management.System;

public class InterviewRequest {
    private Long studentId;
    private Long companyId;
    private String dateTimeStr; // Expected format: YYYY-MM-DDTHH:MM (matches HTML datetime-local picker)
    private String status;

    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getDateTimeStr() { return dateTimeStr; }
    public void setDateTimeStr(String dateTimeStr) { this.dateTimeStr = dateTimeStr; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}