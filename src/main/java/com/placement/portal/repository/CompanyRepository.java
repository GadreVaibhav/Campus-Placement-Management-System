package com.placement.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Company;

@Repository
public interface CompanyRepository extends
        JpaRepository<Company, Long>,
        JpaSpecificationExecutor<Company> {

    Optional<Company> findByEmail(String email);

    Optional<Company> findByCompanyName(String companyName);

    boolean existsByEmail(String email);

    boolean existsByCompanyName(String companyName);
}