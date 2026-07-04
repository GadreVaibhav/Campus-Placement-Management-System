package Campus.Placement.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    @PostMapping
    public Interview scheduleInterview(@RequestBody InterviewRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
                
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        // Parse incoming HTML input time format strings into official LocalDateTime objects
        LocalDateTime parsedDateTime = LocalDateTime.parse(request.getDateTimeStr());

        Interview interview = new Interview(student, company, parsedDateTime, request.getStatus());
        return interviewRepository.save(interview);
    }
}