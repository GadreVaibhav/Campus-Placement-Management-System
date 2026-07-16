package com.placement.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Interview;
import com.placement.portal.entity.Student;
@Repository
public interface InterviewRepository
        extends JpaRepository<Interview, Long> {

    Interview findByStudentApplicationId(Long applicationId);

    long countByStudentApplicationStudent(Student student);

}