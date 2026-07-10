package Campus.Placement.Management.System.service;
import Campus.Placement.Management.System.dto.StudentRequestDTO;
import Campus.Placement.Management.System.dto.StudentResponseDTO;
import Campus.Placement.Management.System.entity.Student;

import java.util.List;

public interface StudentService {

    Student registerStudent(Student student);

    StudentResponseDTO updateProfile(Long userId,StudentRequestDTO requestDTO);

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();

    void deleteStudent(Long studentId);
}
