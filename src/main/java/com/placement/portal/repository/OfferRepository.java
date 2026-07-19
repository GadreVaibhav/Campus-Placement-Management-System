package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Offer;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.StudentApplication;

@Repository
public interface OfferRepository
        extends JpaRepository<Offer, Long> {

    Optional<Offer> findByStudentApplication(
            StudentApplication application);

    List<Offer> findByStudentApplicationStudent(
            Student student);

}