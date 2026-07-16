package com.placement.portal.specification;

import org.springframework.data.jpa.domain.Specification;

import com.placement.portal.entity.Job;

public class JobSpecification {

    private JobSpecification() {
    }

    // ==========================================
    // Job Title
    // ==========================================

    public static Specification<Job> hasJobTitle(String jobTitle) {

        return (root, query, cb) ->

                jobTitle == null || jobTitle.isBlank()

                        ? cb.conjunction()

                        : cb.like(
                                cb.lower(root.get("jobTitle")),
                                "%" + jobTitle.toLowerCase() + "%");
    }

    // ==========================================
    // Location
    // ==========================================

    public static Specification<Job> hasLocation(String location) {

        return (root, query, cb) ->

                location == null || location.isBlank()

                        ? cb.conjunction()

                        : cb.like(
                                cb.lower(root.get("location")),
                                "%" + location.toLowerCase() + "%");
    }

    // ==========================================
    // Status
    // ==========================================

    public static Specification<Job> hasStatus(String status) {

        return (root, query, cb) ->

                status == null || status.isBlank()

                        ? cb.conjunction()

                        : cb.equal(
                                cb.lower(root.get("status")),
                                status.toLowerCase());
    }

    // ==========================================
    // Company
    // ==========================================

    public static Specification<Job> hasCompany(Long companyId) {

        return (root, query, cb) ->

                companyId == null

                        ? cb.conjunction()

                        : cb.equal(
                                root.get("company").get("id"),
                                companyId);
    }

    // ==========================================
    // Recruiter
    // ==========================================

    public static Specification<Job> hasRecruiter(Long recruiterId) {

        return (root, query, cb) ->

                recruiterId == null

                        ? cb.conjunction()

                        : cb.equal(
                                root.get("recruiter").get("id"),
                                recruiterId);
    }

    // ==========================================
    // Eligibility CGPA
    // ==========================================

    public static Specification<Job> hasEligibilityCgpa(Float cgpa) {

        return (root, query, cb) ->

                cgpa == null

                        ? cb.conjunction()

                        : cb.greaterThanOrEqualTo(
                                root.get("eligibilityCgpa"),
                                cgpa);
    }
}