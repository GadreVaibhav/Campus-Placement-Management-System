package com.placement.portal.specification;

import org.springframework.data.jpa.domain.Specification;

import com.placement.portal.entity.Company;

public class CompanySpecification {

    private CompanySpecification() {
    }

    public static Specification<Company> hasCompanyName(String companyName) {

        return (root, query, cb) ->

                companyName == null || companyName.isBlank()

                        ? cb.conjunction()

                        : cb.like(
                                cb.lower(root.get("companyName")),
                                "%" + companyName.toLowerCase() + "%");
    }

    public static Specification<Company> hasIndustry(String industry) {

        return (root, query, cb) ->

                industry == null || industry.isBlank()

                        ? cb.conjunction()

                        : cb.like(
                                cb.lower(root.get("industry")),
                                "%" + industry.toLowerCase() + "%");
    }

    public static Specification<Company> hasLocation(String location) {

        return (root, query, cb) ->

                location == null || location.isBlank()

                        ? cb.conjunction()

                        : cb.like(
                                cb.lower(root.get("location")),
                                "%" + location.toLowerCase() + "%");
    }

    public static Specification<Company> isActive(Boolean active) {

        return (root, query, cb) ->

                active == null

                        ? cb.conjunction()

                        : cb.equal(root.get("isActive"), active);
    }
}