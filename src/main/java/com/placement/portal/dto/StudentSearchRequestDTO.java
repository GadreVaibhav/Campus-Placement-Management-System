package com.placement.portal.dto;

public class StudentSearchRequestDTO {

    // Search Filters
    private String name;
    private String branch;
    private String skill;
    private Float minCgpa;
    private Float maxCgpa;
    private Integer graduationYear;
    private Boolean isPlaced;

    // Pagination
    private int page = 0;
    private int size = 10;

    // Sorting
    private String sortBy = "studentId";
    private String direction = "asc";

    public StudentSearchRequestDTO() {
    }

    // =========================
    // Getters & Setters
    // =========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Float getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(Float minCgpa) {
        this.minCgpa = minCgpa;
    }

    public Float getMaxCgpa() {
        return maxCgpa;
    }

    public void setMaxCgpa(Float maxCgpa) {
        this.maxCgpa = maxCgpa;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Boolean getIsPlaced() {
        return isPlaced;
    }

    public void setIsPlaced(Boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}