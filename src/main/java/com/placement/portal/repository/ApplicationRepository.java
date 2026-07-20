package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.entity.Application;
import com.placement.portal.entity.Job;
import com.placement.portal.entity.Student;
@Repository
public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    boolean existsByStudentAndJob(Student student, Job job);

    List<Application> findByStudent(Student student);

    List<Application> findByJob(Job job);

    Optional<Application> findByStudentAndJob(
            Student student,
            Job job);

    List<Application> findTop5ByStudentOrderByAppliedAtDesc(Student student);

    List<Application> findTop5ByOrderByAppliedAtDesc();

    List<Application> findByJobRecruiter(Recruiter recruiter);

    List<Application> findByJobCompanyId(Long companyId);

    // ===========================
    // Dashboard
    // ===========================

    long countByStudent(Student student);

    long countByStudentAndStatus(Student student, String status);

    long countByStatus(String status);
}