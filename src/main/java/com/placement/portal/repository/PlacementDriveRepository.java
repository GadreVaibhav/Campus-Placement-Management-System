package com.placement.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.PlacementDrive;

@Repository
public interface PlacementDriveRepository extends
        JpaRepository<PlacementDrive, Long>,
        JpaSpecificationExecutor<PlacementDrive> {
                List<PlacementDrive> findByStatusIgnoreCase(String status);
}