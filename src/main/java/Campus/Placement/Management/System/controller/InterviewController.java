package Campus.Placement.Management.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import Campus.Placement.Management.System.model.Interview;
import Campus.Placement.Management.System.model.StudentApplication;
import Campus.Placement.Management.System.repository.InterviewRepository;
import Campus.Placement.Management.System.repository.StudentApplicationRepository;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private StudentApplicationRepository applicationRepository;

    @PostMapping("/{applicationId}")
    public ResponseEntity<?> scheduleInterview(@PathVariable Long applicationId, @RequestBody Interview interview) {
        
        // Debug prints to help us track the 403 Forbidden error if it happens again
        System.out.println("DEBUG: User trying to access is: " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("DEBUG: User roles/authorities are: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        // Find the application the student submitted
        StudentApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // Link the interview to that application
        interview.setStudentApplication(application);
        
        // Save and return
        return ResponseEntity.ok(interviewRepository.save(interview));
    }
}