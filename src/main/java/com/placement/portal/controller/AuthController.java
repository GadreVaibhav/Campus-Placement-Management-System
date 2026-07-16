package com.placement.portal.controller;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.placement.portal.entity.User;
import com.placement.portal.repository.UserRepository;
import com.placement.portal.security.JwtService;
import com.placement.portal.entity.Student;
import com.placement.portal.repository.StudentRepository;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   private final UserRepository userRepository;
private final JwtService jwtService;
private final PasswordEncoder passwordEncoder;
private final StudentRepository studentRepository;
public AuthController(
        UserRepository userRepository,
        StudentRepository studentRepository,
        JwtService jwtService,
        PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.studentRepository = studentRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
}

  @PostMapping("/register")
public Map<String, String> register(@RequestBody User user) {

    // Check if email already exists
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        throw new RuntimeException("Email already exists");
    }

    // Encrypt password
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Save User
    User savedUser = userRepository.save(user);

    // Create Student Profile only for STUDENT role
    if (savedUser.getRole() == User.Role.STUDENT) {

        Student student = new Student();

        student.setUser(savedUser);
        student.setEmail(savedUser.getEmail());

        studentRepository.save(student);
    }

    return Map.of(
            "message", "User registered successfully"
    );
}
@PostMapping("/login")
public Map<String, String> login(@RequestBody User loginRequest) {

    User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
    );

    String token = jwtService.generateToken(userDetails);

    return Map.of(
            "token", token,
            "role", user.getRole().name()
    );
}
}