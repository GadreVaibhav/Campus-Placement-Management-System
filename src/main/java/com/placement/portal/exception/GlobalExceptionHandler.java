package com.placement.portal.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // ==========================
    // Student Not Found
    // ==========================

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(

            StudentNotFoundException ex,
            HttpServletRequest request) {

        logger.error("Student Exception: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "Not Found",

                ex.getMessage(),

                request.getRequestURI()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

        
        @ExceptionHandler(DocumentNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleDocumentNotFound(

                DocumentNotFoundException ex,
                HttpServletRequest request) {

        logger.error("Document Exception: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "Not Found",

                ex.getMessage(),

                request.getRequestURI()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    // ==========================
    // User Not Found
    // ==========================

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(

            UserNotFoundException ex,
            HttpServletRequest request) {

        logger.error("User Exception: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "Not Found",

                ex.getMessage(),

                request.getRequestURI()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ==========================
    // Validation Errors
    // ==========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(

            MethodArgumentNotValidException ex) {

        logger.warn("Validation failed.");

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()

                .getAllErrors()

                .forEach(error -> {

                    String field =
                            ((FieldError) error).getField();

                    String message =
                            error.getDefaultMessage();

                    errors.put(field, message);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // ==========================
    // Any Other Exception
    // ==========================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(

            Exception ex,
            HttpServletRequest request) {

        logger.error("Unexpected Exception", ex);

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                "Internal Server Error",

                ex.getMessage(),

                request.getRequestURI()

        );

        return new ResponseEntity<>(response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgument(

                IllegalArgumentException ex,

                HttpServletRequest request) {

        logger.warn("Validation Error: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.BAD_REQUEST.value(),

                "Bad Request",

                ex.getMessage(),

                request.getRequestURI());

        return new ResponseEntity<>(response,
                HttpStatus.BAD_REQUEST);
        }

        // ==========================
        // Company Not Found
        // ==========================

        @ExceptionHandler(CompanyNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCompanyNotFound(

                CompanyNotFoundException ex,
                HttpServletRequest request) {

        logger.error("Company Exception: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "Not Found",

                ex.getMessage(),

                request.getRequestURI()

        );

        return new ResponseEntity<>(response,
                HttpStatus.NOT_FOUND);
        }
}