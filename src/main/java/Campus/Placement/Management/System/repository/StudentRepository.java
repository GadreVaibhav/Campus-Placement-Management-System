package Campus.Placement.Management.System.repository;

import Campus.Placement.Management.System.model.Student;
import Campus.Placement.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUser(User user);
}