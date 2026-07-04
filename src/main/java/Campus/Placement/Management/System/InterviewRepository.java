package Campus.Placement.Management.System;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    // Custom finder method to see all interviews scheduled for a specific student
    List<Interview> findByStudentId(Long studentId);
}