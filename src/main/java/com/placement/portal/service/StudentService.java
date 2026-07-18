package com.placement.portal.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.placement.portal.dto.StudentRequestDTO;
import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.dto.StudentUpdateDTO;
import com.placement.portal.entity.Student;

public interface StudentService {

    // ==========================================
    // Register Student
    // ==========================================

    Student registerStudent(Student student);

    // ==========================================
    // Update Profile
    // ==========================================

   StudentResponseDTO updateLoggedInStudent(
        String email,
        StudentRequestDTO requestDTO);

    // ==========================================
    // Get Student By ID
    // ==========================================

    StudentResponseDTO getStudentById(Long studentId);

    // ==========================================
    // Get Logged-in Student
    // ==========================================

    StudentResponseDTO getLoggedInStudent(String email);

    // ==========================================
    // Get All Students
    // ==========================================

    Page<StudentResponseDTO> getAllStudents(
            int page,
            int size,
            String sortBy,
            String direction);

    // ==========================================
    // Search Students
    // ==========================================

    Page<StudentResponseDTO> searchStudents(

            String name,

            String branch,

            String skill,

            Float cgpa,

            Integer graduationYear,

            Boolean placed,

            int page,

            int size,

            String sortBy,

            String direction);

    // ==========================================
    // Delete Student
    // ==========================================

    void deleteStudent(Long studentId);
    List<StudentResponseDTO> getRecentStudents();

  StudentResponseDTO updateStudent(
        Long studentId,
        StudentUpdateDTO dto);

        StudentResponseDTO uploadResume(String email, MultipartFile file);

Resource downloadResume(String email);

void deleteResume(String email);
}