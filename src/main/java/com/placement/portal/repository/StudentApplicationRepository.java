package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import com.placement.portal.dto.ApplicationStatusCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.placement.portal.entity.PlacementDrive;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.StudentApplication;

@Repository
public interface StudentApplicationRepository
        extends JpaRepository<StudentApplication, Long> {

    List<StudentApplication> findByStudent(Student student);

    List<StudentApplication> findByPlacementDrive(
            PlacementDrive placementDrive);

    Optional<StudentApplication> findByStudentAndPlacementDrive(
            Student student,
            PlacementDrive placementDrive);

    List<StudentApplication> findAllByOrderByApplicationDateDesc(
            Pageable pageable);

        @Query("""
        SELECT new com.placement.portal.dto.ApplicationStatusCountDTO(
        a.status,
        COUNT(a)
        )
        FROM StudentApplication a
        GROUP BY a.status
        """)
        List<ApplicationStatusCountDTO> countApplicationsByStatus();
        long countByStudent(Student student);

long countByStudentAndStatus(
        Student student,
        com.placement.portal.entity.ApplicationStatus status);
}