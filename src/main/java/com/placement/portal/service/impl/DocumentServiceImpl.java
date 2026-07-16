package com.placement.portal.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;
import com.placement.portal.dto.DocumentResponseDTO;
import com.placement.portal.mapper.DocumentMapper;
import com.placement.portal.entity.Document;
import com.placement.portal.entity.DocumentType;
import com.placement.portal.entity.Student;
import com.placement.portal.exception.DocumentNotFoundException;
import com.placement.portal.exception.StudentNotFoundException;
import com.placement.portal.repository.DocumentRepository;
import com.placement.portal.repository.StudentRepository;
import com.placement.portal.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

    // ==========================================
    // Logger
    // ==========================================

    private static final Logger logger =
            LoggerFactory.getLogger(DocumentServiceImpl.class);

    // ==========================================
    // Upload Folder
    // ==========================================

    @Value("${file.upload-dir}")
    private String uploadDir;

    // ==========================================
    // Repositories
    // ==========================================

    private final DocumentRepository documentRepository;
    private final StudentRepository studentRepository;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            StudentRepository studentRepository) {

        this.documentRepository = documentRepository;
        this.studentRepository = studentRepository;
    }

    // ==========================================
    // Upload Document
    // ==========================================

    @Override
    public DocumentResponseDTO uploadDocument(
            Long studentId,
            MultipartFile file,
            String documentType)
            throws IOException {

        logger.info("Uploading document for student ID: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.error(
                            "Student not found with ID: {}",
                            studentId);

                    return new StudentNotFoundException(
                            "Student not found with ID: "
                                    + studentId);
                });
        
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                !(originalFileName.toLowerCase().endsWith(".pdf")
                || originalFileName.toLowerCase().endsWith(".doc")
                || originalFileName.toLowerCase().endsWith(".docx"))) {

            throw new IllegalArgumentException(
                    "Only PDF, DOC and DOCX files are allowed.");
        }
        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {

            throw new IllegalArgumentException(
                    "Maximum file size is 5 MB.");
        }
        if (file == null || file.isEmpty()) {

            logger.error("Uploaded file is empty.");

            throw new IllegalArgumentException(
                    "Please select a file.");
        }

       Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

            logger.info(
                    "Upload directory created successfully.");
        }

        String fileName = UUID.randomUUID() + "_" + originalFileName;

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING);

        Document document = new Document();

        document.setDocumentType(
                DocumentType.valueOf(
                        documentType.toUpperCase()));

        document.setFileName(fileName);

        document.setFilePath(filePath.toString());

        document.setFileSize(file.getSize());

        document.setMimeType(file.getContentType());

        document.setStudent(student);

        document.setIsVerified(false);

        Document savedDocument =
                documentRepository.save(document);

        logger.info(
                "Document uploaded successfully. ID: {}",
                savedDocument.getId());

        return DocumentMapper.toResponseDTO(savedDocument);
    }
        // ==========================================
    // Get All Documents of a Student
    // ==========================================

   @Override
public List<DocumentResponseDTO> getDocumentsByStudent(Long studentId) {

    logger.info("Fetching documents for Student ID: {}", studentId);

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                    new StudentNotFoundException(
                            "Student not found with ID: " + studentId));

    return documentRepository.findByStudent(student)

            .stream()

            .map(DocumentMapper::toResponseDTO)

            .collect(Collectors.toList());
}

    // ==========================================
    // Get Document By ID
    // ==========================================

    @Override
    public DocumentResponseDTO getDocumentById(Long documentId) {

        logger.info("Fetching document ID: {}", documentId);

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> {

                    logger.error("Document not found with ID: {}", documentId);

                    return new DocumentNotFoundException(
                            "Document not found with ID: " + documentId);
                });

        logger.info("Document fetched successfully.");

        return DocumentMapper.toResponseDTO(document);
    }

    // ==========================================
    // Delete Document
    // ==========================================

    @Override
    public void deleteDocument(Long documentId) throws IOException {

        logger.info("Deleting document ID: {}", documentId);

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> {

                    logger.error("Document not found with ID: {}", documentId);

                    return new DocumentNotFoundException(
                            "Document not found with ID: " + documentId);
                });

        Path filePath = Paths.get(document.getFilePath());

        if (Files.exists(filePath)) {

            Files.delete(filePath);

            logger.info("Physical file deleted successfully.");
        }
        else {

            logger.warn("Physical file does not exist.");
        }

        documentRepository.delete(document);

        logger.info("Document record deleted successfully.");
    }
        // ==========================================
    // Verify Document (Admin)
    // ==========================================

    @Override
    public DocumentResponseDTO verifyDocument(Long documentId) {

        logger.info("Verifying document ID: {}", documentId);

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> {

                    logger.error("Document not found with ID: {}", documentId);

                    return new DocumentNotFoundException(
                            "Document not found with ID: " + documentId);
                });

        document.setIsVerified(true);

        Document verified = documentRepository.save(document);

        logger.info("Document verified successfully.");

        return DocumentMapper.toResponseDTO(verified);
    }

    // ==========================================
// Download Document
// ==========================================

@Override
public Resource downloadDocument(Long documentId) throws IOException {

    logger.info("Downloading document with ID: {}", documentId);

    Document document = documentRepository.findById(documentId)
            .orElseThrow(() -> {

                logger.error("Document not found with ID: {}", documentId);

                return new DocumentNotFoundException(
                        "Document not found with ID: " + documentId);
            });

    Path path = Paths.get(document.getFilePath());

    if (!Files.exists(path)) {

        logger.error("File not found on disk: {}", document.getFilePath());

        throw new IOException("File not found.");
    }

    try {

        Resource resource = new UrlResource(path.toUri());

        if (resource.exists() && resource.isReadable()) {

            logger.info("File downloaded successfully.");

            return resource;
        }

        throw new IOException("File is not readable.");

    } catch (MalformedURLException ex) {

        logger.error("Invalid file path.", ex);

        throw new IOException("Unable to read file.");
    }
}
}