package com.placement.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.placement.portal.dto.StudentRequestDTO;
import com.placement.portal.dto.StudentResponseDTO;
import com.placement.portal.dto.StudentUpdateDTO;
import com.placement.portal.entity.Student;
import com.placement.portal.entity.User;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.exception.UserNotFoundException;
import com.placement.portal.mapper.StudentMapper;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.repository.UserRepository;
import com.placement.portal.service.StudentService;
import org.springframework.data.jpa.domain.Specification;
import com.placement.portal.specification.StudentSpecification;
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              UserRepository userRepository) {

        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    // ==========================================
    // Register Student
    // ==========================================

    @Override
    public Student registerStudent(Student student) {

        logger.info("Registering student with email: {}", student.getEmail());

        Student savedStudent = studentRepository.save(student);

        logger.info("Student registered successfully with ID: {}",
                savedStudent.getStudentId());

        return savedStudent;
    }

    // ==========================================
    // Update Profile
    // ==========================================

    @Override
    public StudentResponseDTO updateProfile(Long userId,
                                            StudentRequestDTO requestDTO) {

        logger.info("Updating profile for User ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {

                    logger.error("User not found with ID: {}", userId);

                    return new UserNotFoundException(
                            "User not found with ID: " + userId);
                });

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> {

                    logger.error("Student profile not found for User ID: {}", userId);

                    return new StudentNotFoundException(
                            "Student profile not found for User ID: " + userId);
                });

        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setPhone(requestDTO.getPhone());
        student.setBranch(requestDTO.getBranch());
        student.setGraduationYear(requestDTO.getGraduationYear());
        student.setPrimaryLanguage(requestDTO.getPrimaryLanguage());
        student.setCgpa(requestDTO.getCgpa());
        student.setSkill(requestDTO.getSkill());
        student.setTenthPercentage(requestDTO.getTenthPercentage());
        student.setTwelfthPercentage(requestDTO.getTwelfthPercentage());
        student.setCurrentBacklogs(requestDTO.getCurrentBacklogs());
        student.setTotalBacklogs(requestDTO.getTotalBacklogs());

        Student updatedStudent = studentRepository.save(student);

        logger.info("Profile updated successfully for User ID: {}", userId);

        return StudentMapper.toResponseDTO(updatedStudent);
    }

    // ==========================================
    // Get Student By ID
    // ==========================================

    @Override
    public StudentResponseDTO getStudentById(Long studentId) {

        logger.info("Fetching student with ID: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.error("Student not found: {}", studentId);

                    return new StudentNotFoundException(
                            "Student not found with ID: " + studentId);
                });

        logger.info("Student retrieved successfully.");

        return StudentMapper.toResponseDTO(student);
    }

    // ==========================================
    // Logged In Student
    // ==========================================

    @Override
    public StudentResponseDTO getLoggedInStudent(String email) {

        logger.info("Fetching logged in student: {}", email);

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> {

                    logger.error("Student not found for email: {}", email);

                    return new StudentNotFoundException(
                            "Student not found with email: " + email);
                });

        logger.info("Logged in student fetched successfully.");

        return StudentMapper.toResponseDTO(student);
    }

    // ==========================================
    // Get All Students
    // ==========================================

    @Override
    public Page<StudentResponseDTO> getAllStudents(
            int page,
            int size,
            String sortBy,
            String direction) {

        logger.info(
                "Fetching students | page={} size={} sortBy={} direction={}",
                page,
                size,
                sortBy,
                direction
        );

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> students = studentRepository.findAll(pageable);

        logger.info("Total students fetched: {}", students.getTotalElements());

        return students.map(StudentMapper::toResponseDTO);
    }
    // ==========================================
// Search Students
// ==========================================

@Override
public Page<StudentResponseDTO> searchStudents(

        String name,

        String branch,

        String skill,

        Float cgpa,

        Integer graduationYear,

        Boolean placed,

        int page,

        int size,

        String sortBy,

        String direction) {

    logger.info(
            "Searching students | name={} branch={} skill={} cgpa={} graduationYear={} placed={}",
            name,
            branch,
            skill,
            cgpa,
            graduationYear,
            placed);

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    Specification<Student> specification =

            Specification.where(StudentSpecification.hasName(name))

                    .and(StudentSpecification.hasBranch(branch))

                    .and(StudentSpecification.hasSkill(skill))

                    .and(StudentSpecification.hasMinimumCgpa(cgpa))

                    .and(StudentSpecification.hasGraduationYear(graduationYear))

                    .and(StudentSpecification.isPlaced(placed));

    Page<Student> students =
            studentRepository.findAll(specification, pageable);

    logger.info("Search completed successfully. {} students found.",
            students.getTotalElements());

    return students.map(StudentMapper::toResponseDTO);
}

@Override
public List<StudentResponseDTO> getRecentStudents() {

    Pageable pageable = PageRequest.of(0, 5);

    List<Student> students =
            studentRepository.findAllByOrderByStudentIdDesc(pageable);

    return students.stream()
            .map(StudentMapper::toResponseDTO)
            .toList();
}
    // ==========================================
    // Delete Student
    // ==========================================

    @Override
    public void deleteStudent(Long studentId) {

        logger.info("Deleting student ID: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.error("Student not found: {}", studentId);

                    return new StudentNotFoundException(
                            "Student not found with ID: " + studentId);
                });

        studentRepository.delete(student);

        logger.info("Student deleted successfully.");
    }
@Override
public StudentResponseDTO updateStudent(
        Long studentId,
        StudentUpdateDTO dto) {

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                    new StudentNotFoundException("Student not found"));

    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setBranch(dto.getBranch());
    student.setCgpa(dto.getCgpa());
    student.setSkill(dto.getSkill());
    student.setGraduationYear(dto.getGraduationYear());

    Student updated = studentRepository.save(student);

    return StudentMapper.toResponseDTO(updated);
}
}