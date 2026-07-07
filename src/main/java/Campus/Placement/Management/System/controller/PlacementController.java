package Campus.Placement.Management.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Campus.Placement.Management.System.model.Company;
import Campus.Placement.Management.System.model.Student;
import Campus.Placement.Management.System.repository.CompanyRepository;
import Campus.Placement.Management.System.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PlacementController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

   @GetMapping("/api/placements/eligible")
public List<Company> getEligibleCompanies(@RequestParam Long studentId) {
    
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

    List<Company> allCompanies = companyRepository.findAll();

    return allCompanies.stream()
            .filter(company -> student.getCgpa() >= company.getMinCgpaRequired())
            .filter(company -> {
                String studentSkill = student.getSkill(); // No more errors!
                String companySkill = company.getRequiredSkill();
                return studentSkill != null && companySkill != null && studentSkill.equalsIgnoreCase(companySkill);
            })
            .collect(Collectors.toList());
}
}