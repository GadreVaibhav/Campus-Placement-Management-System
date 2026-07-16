package com.placement.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Recruiter;

@Repository
public interface RecruiterRepository
        extends JpaRepository<Recruiter, Long> {

    Optional<Recruiter> findByEmail(String email);

    boolean existsByEmail(String email);
}