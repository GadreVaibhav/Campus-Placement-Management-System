package com.placement.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Job;

@Repository
public interface JobRepository
        extends JpaRepository<Job, Long>,
                JpaSpecificationExecutor<Job> {

}