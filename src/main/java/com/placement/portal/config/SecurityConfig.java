package com.placement.portal.config;

import com.placement.portal.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(auth -> auth

                        // ==================================================
                        // PUBLIC
                        // ==================================================

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()

                        // ==================================================
                        // STUDENT
                        // ==================================================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/me")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/students/me")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/students/resume")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/resume")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/students/resume")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                "/api/students/profile/**")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/jobs/available",
                                "/api/jobs/*")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/applications/apply/*")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/applications/my",
                                "/api/applications/my/recent")
                        .hasRole("STUDENT")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/interviews/my")
                        .hasRole("STUDENT")

                        // ==================================================
                        // RECRUITER
                        // ==================================================

                        .requestMatchers("/api/recruiter/**")
                        .hasRole("RECRUITER")

                        .requestMatchers("/api/recruiter/dashboard/**")
                        .hasRole("RECRUITER")   

                        // ==================================================
                        // ADMIN DASHBOARD
                        // ==================================================

                        .requestMatchers("/api/dashboard/**")
                        .hasRole("ADMIN")

                        // ==================================================
                        // ADMIN STUDENTS
                        // ==================================================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/search")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/students/{studentId}")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/students/{studentId}")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/students/{studentId}")
                        .hasRole("ADMIN")

                        // ==================================================
                        // ADMIN APPLICATIONS
                        // ==================================================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/applications")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/applications/recent")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/applications/{applicationId}")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/applications/{applicationId}/status")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/applications/{applicationId}")
                        .hasRole("ADMIN")

                        // ==================================================
                        // EVERYTHING ELSE
                        // ==================================================

                        .anyRequest().authenticated());

        return http.build();
    }
}