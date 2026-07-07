package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Campus.Placement.Management.System.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Interview findByStudentApplicationId(Long applicationId);
}