package com.placement.portal.specification;

import org.springframework.data.jpa.domain.Specification;

import com.placement.portal.entity.Student;

public class StudentSpecification {

    private StudentSpecification() {
    }

    public static Specification<Student> hasName(String name) {

        return (root, query, criteriaBuilder) ->

                name == null || name.isBlank()

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.like(

                                criteriaBuilder.lower(root.get("name")),

                                "%" + name.toLowerCase() + "%"

                        );
    }

    public static Specification<Student> hasBranch(String branch) {

        return (root, query, criteriaBuilder) ->

                branch == null || branch.isBlank()

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.like(

                                criteriaBuilder.lower(root.get("branch")),

                                "%" + branch.toLowerCase() + "%"

                        );
    }

    public static Specification<Student> hasSkill(String skill) {

        return (root, query, criteriaBuilder) ->

                skill == null || skill.isBlank()

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.like(

                                criteriaBuilder.lower(root.get("skill")),

                                "%" + skill.toLowerCase() + "%"

                        );
    }

    public static Specification<Student> hasMinimumCgpa(Float cgpa) {

        return (root, query, criteriaBuilder) ->

                cgpa == null

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.greaterThanOrEqualTo(

                                root.get("cgpa"),

                                cgpa

                        );
    }

    public static Specification<Student> hasGraduationYear(Integer year) {

        return (root, query, criteriaBuilder) ->

                year == null

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.equal(

                                root.get("graduationYear"),

                                year

                        );
    }

    public static Specification<Student> isPlaced(Boolean placed) {

        return (root, query, criteriaBuilder) ->

                placed == null

                        ? criteriaBuilder.conjunction()

                        : criteriaBuilder.equal(

                                root.get("isPlaced"),

                                placed

                        );
    }
}