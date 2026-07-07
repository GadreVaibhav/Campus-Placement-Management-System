package Campus.Placement.Management.System.controller;

import Campus.Placement.Management.System.dto.AuthenticationRequest; // Updated import
import Campus.Placement.Management.System.dto.RegisterRequest;       // Updated import
import Campus.Placement.Management.System.model.User;
import Campus.Placement.Management.System.repository.UserRepository;
import Campus.Placement.Management.System.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

   @Autowired
    private PasswordEncoder passwordEncoder; // <--- Does this have @Autowired above it?

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        
        // This encrypts your plain text password into BCrypt format safely
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        
        return Map.of("message", "User registered successfully!");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return Map.of("token", token, "role", user.getRole().toString());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}