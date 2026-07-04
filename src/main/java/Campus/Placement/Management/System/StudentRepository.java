package Campus.Placement.Management.System;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // That's it! Spring Boot automatically implements all standard CRUD operations here.
}