package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Campus.Placement.Management.System.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}