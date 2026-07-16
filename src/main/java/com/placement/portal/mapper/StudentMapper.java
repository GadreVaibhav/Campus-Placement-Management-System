package com.placement.portal.mapper;

import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.entity.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentResponseDTO toResponseDTO(Student student) {

    StudentResponseDTO dto = new StudentResponseDTO();

    dto.setStudentId(student.getStudentId());
    dto.setName(student.getName());
    dto.setEmail(student.getEmail());
    dto.setPhone(student.getPhone());
    dto.setBranch(student.getBranch());
    dto.setGraduationYear(student.getGraduationYear());
    dto.setPrimaryLanguage(student.getPrimaryLanguage());
    dto.setCgpa(student.getCgpa());
    dto.setSkill(student.getSkill());
    dto.setResumeUrl(student.getResumeUrl());
    dto.setTenthPercentage(student.getTenthPercentage());
    dto.setTwelfthPercentage(student.getTwelfthPercentage());
    dto.setCurrentBacklogs(student.getCurrentBacklogs());
    dto.setTotalBacklogs(student.getTotalBacklogs());
    dto.setIsPlaced(student.getIsPlaced());

    return dto;
}
}