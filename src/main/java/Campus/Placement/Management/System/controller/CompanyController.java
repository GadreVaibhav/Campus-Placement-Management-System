package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.model.Company;
import Campus.Placement.Management.System.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // ADD THIS LINE
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/api/companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping("/api/companies")
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }
}