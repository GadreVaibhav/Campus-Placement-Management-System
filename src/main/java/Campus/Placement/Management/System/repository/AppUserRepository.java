package Campus.Placement.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Campus.Placement.Management.System.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Custom method to find a user by their email during the login process
    Optional<AppUser> findByEmail(String email);
}