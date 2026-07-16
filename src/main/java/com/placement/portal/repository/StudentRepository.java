package com.placement.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.placement.portal.dto.BranchCountDTO;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.User;
import org.springframework.data.domain.Pageable;
@Repository
public interface StudentRepository extends
        JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {

    Optional<Student> findByUser(User user);

    Optional<Student> findByUserEmail(String email);

    @Query("""
SELECT new com.placement.portal.dto.BranchCountDTO(
    s.branch,
    COUNT(s)
)
FROM Student s
GROUP BY s.branch
ORDER BY COUNT(s) DESC
""")
List<BranchCountDTO> countStudentsByBranch();
List<Student> findAllByOrderByStudentIdDesc(Pageable pageable);
}