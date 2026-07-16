package com.placement.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.placement.portal.dto.JobRequestDTO;
import com.placement.portal.dto.JobResponseDTO;
import com.placement.portal.entity.Company;
import com.placement.portal.entity.Job;
import com.placement.portal.entity.Recruiter;
import com.placement.portal.exception.CompanyNotFoundException;
import com.placement.portal.exception.JobNotFoundException;
import com.placement.portal.mapper.JobMapper;
import com.placement.portal.repository.CompanyRepository;
import com.placement.portal.repository.JobRepository;
import com.placement.portal.repository.RecruiterRepository;
import com.placement.portal.service.JobService;
import com.placement.portal.specification.JobSpecification;

@Service
public class JobServiceImpl implements JobService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(JobServiceImpl.class);

    // ==========================================
    // Repositories
    // ==========================================

    private final JobRepository jobRepository;

    private final CompanyRepository companyRepository;

    private final RecruiterRepository recruiterRepository;

    public JobServiceImpl(

            JobRepository jobRepository,

            CompanyRepository companyRepository,

            RecruiterRepository recruiterRepository) {

        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
    }
        // ==========================================
    // Create Job
    // ==========================================

    @Override
    public JobResponseDTO createJob(JobRequestDTO requestDTO) {

        logger.info("Creating job: {}", requestDTO.getJobTitle());

        Company company = companyRepository

                .findById(requestDTO.getCompanyId())

                .orElseThrow(() ->

                        new CompanyNotFoundException(
                                "Company not found."));

        Recruiter recruiter = recruiterRepository

                .findById(requestDTO.getRecruiterId())

                .orElseThrow(() ->

                        new IllegalArgumentException(
                                "Recruiter not found."));

        Job job = new Job();

        job.setJobTitle(requestDTO.getJobTitle());
        job.setDescription(requestDTO.getDescription());
        job.setPackageLpa(requestDTO.getPackageLpa());
        job.setLocation(requestDTO.getLocation());
        job.setEligibilityCgpa(requestDTO.getEligibilityCgpa());
        job.setSkillsRequired(requestDTO.getSkillsRequired());
        job.setLastDate(requestDTO.getLastDate());
        job.setStatus(requestDTO.getStatus());

        job.setCompany(company);
        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        logger.info("Job created successfully.");

        return JobMapper.toResponseDTO(savedJob);
    }
        // ==========================================
    // Update Job
    // ==========================================

    @Override
    public JobResponseDTO updateJob(
            Long jobId,
            JobRequestDTO requestDTO) {

        logger.info("Updating job ID: {}", jobId);

        Job job = jobRepository.findById(jobId)

                .orElseThrow(() ->

                        new JobNotFoundException(
                                "Job not found with ID: " + jobId));

        Company company = companyRepository.findById(
                requestDTO.getCompanyId())

                .orElseThrow(() ->

                        new CompanyNotFoundException(
                                "Company not found."));

        Recruiter recruiter = recruiterRepository.findById(
                requestDTO.getRecruiterId())

                .orElseThrow(() ->

                        new IllegalArgumentException(
                                "Recruiter not found."));

        job.setJobTitle(requestDTO.getJobTitle());
        job.setDescription(requestDTO.getDescription());
        job.setPackageLpa(requestDTO.getPackageLpa());
        job.setLocation(requestDTO.getLocation());
        job.setEligibilityCgpa(requestDTO.getEligibilityCgpa());
        job.setSkillsRequired(requestDTO.getSkillsRequired());
        job.setLastDate(requestDTO.getLastDate());
        job.setStatus(requestDTO.getStatus());

        job.setCompany(company);
        job.setRecruiter(recruiter);

        Job updatedJob = jobRepository.save(job);

        logger.info("Job updated successfully.");

        return JobMapper.toResponseDTO(updatedJob);
    }
        // ==========================================
    // Get Job By ID
    // ==========================================

    @Override
    public JobResponseDTO getJobById(Long jobId) {

        Job job = jobRepository.findById(jobId)

                .orElseThrow(() ->

                        new JobNotFoundException(
                                "Job not found with ID: " + jobId));

        return JobMapper.toResponseDTO(job);
    }
        // ==========================================
    // Get All Jobs
    // ==========================================

    @Override
    public Page<JobResponseDTO> getAllJobs(

            int page,

            int size,

            String sortBy,

            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return jobRepository

                .findAll(pageable)

                .map(JobMapper::toResponseDTO);
    }
        // ==========================================
    // Search Jobs
    // ==========================================

    @Override
    public Page<JobResponseDTO> searchJobs(

            String jobTitle,

            String location,

            String status,

            Long companyId,

            Long recruiterId,

            Float eligibilityCgpa,

            int page,

            int size,

            String sortBy,

            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Job> specification =

                Specification.where(

                        JobSpecification.hasJobTitle(jobTitle))

                        .and(JobSpecification.hasLocation(location))

                        .and(JobSpecification.hasStatus(status))

                        .and(JobSpecification.hasCompany(companyId))

                        .and(JobSpecification.hasRecruiter(recruiterId))

                        .and(JobSpecification.hasEligibilityCgpa(
                                eligibilityCgpa));

        return jobRepository

                .findAll(specification, pageable)

                .map(JobMapper::toResponseDTO);
    }
        // ==========================================
    // Delete Job
    // ==========================================

    @Override
    public void deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)

                .orElseThrow(() ->

                        new JobNotFoundException(
                                "Job not found with ID: " + jobId));

        jobRepository.delete(job);

        logger.info("Job deleted successfully.");
    }
}