package com.placement.portal.dto;

import jakarta.validation.constraints.NotBlank;

public class RecruiterProfileRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String designation;

    @NotBlank
    private String phone;

    public RecruiterProfileRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}