package com.placement.portal.dto;

import com.placement.portal.entity.ApplicationStatus;

public class ApplicationStatusCountDTO {

    private ApplicationStatus status;

    private Long count;

    public ApplicationStatusCountDTO() {
    }

    public ApplicationStatusCountDTO(
            ApplicationStatus status,
            Long count) {

        this.status = status;
        this.count = count;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}