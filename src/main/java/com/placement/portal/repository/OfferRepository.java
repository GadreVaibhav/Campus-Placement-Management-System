package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Application;
import com.placement.portal.entity.Offer;
import com.placement.portal.entity.Student;

@Repository
public interface OfferRepository
        extends JpaRepository<Offer, Long> {

    // Find Offer by Application
    Optional<Offer> findByApplication(
            Application application);

    // Find all offers of a student
    List<Offer> findByApplicationStudent(
            Student student);

}