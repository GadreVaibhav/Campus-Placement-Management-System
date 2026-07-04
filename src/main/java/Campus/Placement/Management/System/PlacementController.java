package Campus.Placement.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlacementController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // This creates an eligibility check endpoint:
    // http://localhost:8082/api/placements/eligible?studentId=1
    @GetMapping("/api/placements/eligible")
    public List<Company> getEligibleCompanies(@RequestParam Long studentId) {
        
        // 1. Fetch the student from the database using their ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // 2. Fetch all companies listed in the database
        List<Company> allCompanies = companyRepository.findAll();

        // 3. Apply Core Filtering Logic (The Placement Algorithm)
        // Checks if student's CGPA is greater than or equal to company's min requirement
        // AND checks if their programming language matches what the company wants
        return allCompanies.stream()
                .filter(company -> student.getCgpa() >= company.getMinCgpaRequired())
                .filter(company -> student.getPrimaryLanguage().equalsIgnoreCase(company.getRequiredSkill()))
                .collect(Collectors.toList());
    }
}