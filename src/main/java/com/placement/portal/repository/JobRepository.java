package com.placement.portal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Job;

@Repository
public interface JobRepository
        extends JpaRepository<Job, Long>,
                JpaSpecificationExecutor<Job> {

    // Active jobs
    List<Job> findByStatus(String status);

    // Active + Not Expired
    List<Job> findByStatusAndLastDateGreaterThanEqual(
            String status,
            LocalDate lastDate);

}