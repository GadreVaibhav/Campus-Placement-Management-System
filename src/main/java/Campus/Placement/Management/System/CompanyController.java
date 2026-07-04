package Campus.Placement.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    // GET Route to fetch all corporate records
    @GetMapping("/api/companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // POST Route to insert a new company record
    @PostMapping("/api/companies")
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }
}