package com.placement.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Application;
import com.placement.portal.entity.Interview;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.entity.Student;

@Repository
public interface InterviewRepository
        extends JpaRepository<Interview, Long> {

    Interview findByApplication(Application application);

    Interview findByApplicationId(Long applicationId);

    long countByApplicationStudent(Student student);

    List<Interview> findByApplicationStudent(Student student);

    List<Interview> findTop5ByOrderByInterviewTimeAsc();

    List<Interview> findByApplicationJobRecruiterOrderByInterviewTimeAsc(
            Recruiter recruiter);

    // NEW
    long countByApplicationJobRecruiter(Recruiter recruiter);

}