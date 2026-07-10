package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Campus.Placement.Management.System.entity.PlacementDrive;

import java.util.List;

@Repository
public interface PlacementDriveRepository extends JpaRepository<PlacementDrive, Long> {
    // Custom method to find all job postings by a specific company
    List<PlacementDrive> findByCompanyId(Long companyId);
}