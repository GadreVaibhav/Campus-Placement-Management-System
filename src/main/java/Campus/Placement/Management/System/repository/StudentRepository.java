package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Campus.Placement.Management.System.entity.Student;
import Campus.Placement.Management.System.entity.User;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUser(User user);
}