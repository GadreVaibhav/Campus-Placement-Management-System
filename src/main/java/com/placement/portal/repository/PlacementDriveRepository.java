package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Company;
import com.placement.portal.entity.PlacementDrive;

@Repository
public interface PlacementDriveRepository
        extends JpaRepository<PlacementDrive, Long>,
        JpaSpecificationExecutor<PlacementDrive> {

    List<PlacementDrive> findByStatusIgnoreCase(String status);

//     long countByStatusIgnoreCase(String status);

//     List<PlacementDrive> findTop5ByOrderByDriveDateDesc();

    Optional<PlacementDrive> findFirstByCompanyAndJobRole(
            Company company,
            String jobRole);

    // NEW
    long countByCompany(Company company);

    List<PlacementDrive> findByCompanyOrderByDriveDateDesc(
            Company company);

}