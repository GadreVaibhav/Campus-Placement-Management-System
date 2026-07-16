package com.placement.portal.dto;

public class BranchCountDTO {

    private String branch;

    private Long count;

    public BranchCountDTO() {
    }

    public BranchCountDTO(String branch, Long count) {
        this.branch = branch;
        this.count = count;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}