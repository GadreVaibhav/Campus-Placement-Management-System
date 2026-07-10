package Campus.Placement.Management.System.serviceImpl;
import Campus.Placement.Management.System.dto.StudentRequestDTO;
import Campus.Placement.Management.System.dto.StudentResponseDTO;
import Campus.Placement.Management.System.entity.Student;
import Campus.Placement.Management.System.repository.StudentRepository;
import Campus.Placement.Management.System.repository.UserRepository;
import Campus.Placement.Management.System.service.StudentService;
import org.springframework.stereotype.Service;
import Campus.Placement.Management.System.entity.User;
import Campus.Placement.Management.System.exception.UserNotFoundException;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

   public StudentServiceImpl(StudentRepository studentRepository,
                          UserRepository userRepository) {
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
}

    @Override
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }
    private StudentResponseDTO mapToResponseDTO(Student student) {

    StudentResponseDTO response = new StudentResponseDTO();

    response.setStudentId(student.getStudentId());
    response.setName(student.getName());
    response.setEmail(student.getEmail());
    response.setCgpa(student.getCgpa());
    response.setSkill(student.getSkill());

    return response;
}
    @Override
    public StudentResponseDTO updateProfile(Long userId, StudentRequestDTO requestDTO) {

   User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    Student student = studentRepository.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Student profile not found"));

    student.setName(requestDTO.getName());
    student.setEmail(requestDTO.getEmail());
    student.setCgpa(requestDTO.getCgpa());
    student.setSkill(requestDTO.getSkill());

    Student updatedStudent = studentRepository.save(student);
    return mapToResponseDTO(updatedStudent);
}

    @Override
    public Student getStudentById(Long studentId) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) {

    }
}