package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Campus.Placement.Management.System.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
