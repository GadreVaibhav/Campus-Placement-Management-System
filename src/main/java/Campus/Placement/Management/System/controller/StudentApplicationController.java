package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.dto.InterviewRequest; // If you use this
import Campus.Placement.Management.System.model.Student;
import Campus.Placement.Management.System.model.PlacementDrive;
import Campus.Placement.Management.System.model.StudentApplication;
import Campus.Placement.Management.System.repository.PlacementDriveRepository;
import Campus.Placement.Management.System.repository.StudentApplicationRepository;
import Campus.Placement.Management.System.repository.StudentRepository; // Fixed import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class StudentApplicationController {

    @Autowired
    private StudentApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlacementDriveRepository driveRepository;

    // 1. STUDENT ACTION: Apply for a job
    @PostMapping("/apply/{studentId}/{driveId}")
    public ResponseEntity<?> applyForDrive(@PathVariable Long studentId, @PathVariable Long driveId) {
        
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
                
        PlacementDrive drive = driveRepository.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Drive not found"));

        // Create the application tracker
        StudentApplication application = new StudentApplication();
        application.setStudent(student);
        application.setPlacementDrive(drive);
        application.setApplicationStatus("APPLIED"); // Default starting status

        StudentApplication savedApplication = applicationRepository.save(application);
        return ResponseEntity.ok(savedApplication);
    }

    // 2. ADMIN ACTION: View all applications for a specific job drive
    @GetMapping("/drive/{driveId}")
    public ResponseEntity<List<StudentApplication>> getApplicationsForDrive(@PathVariable Long driveId) {
        // repository may not expose a finder by drive id; filter in-memory instead
        List<StudentApplication> all = applicationRepository.findAll();
        List<StudentApplication> filtered = all.stream()
                .filter(app -> app.getPlacementDrive() != null && driveId.equals(app.getPlacementDrive().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    // 3. ADMIN ACTION: Update an application status (e.g., to "SHORTLISTED" or "REJECTED")
    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long applicationId, @RequestBody Map<String, String> updates) {
        StudentApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
                
        String newStatus = updates.get("status");
        if (newStatus != null) {
            application.setApplicationStatus(newStatus);
        }
        
        return ResponseEntity.ok(applicationRepository.save(application));
    }
}