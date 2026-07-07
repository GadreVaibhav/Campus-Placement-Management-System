package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Campus.Placement.Management.System.model.StudentApplication;

import java.util.List;

@Repository
// Open StudentApplicationRepository.java and change the method:
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, Long> {
    
    // CHANGE THIS:
    // List<StudentApplication> findByStudentId(Long studentId); 
    
    // TO THIS (to match the field name in your Student.java model):
    List<StudentApplication> findByStudent_StudentId(Long studentId);
}