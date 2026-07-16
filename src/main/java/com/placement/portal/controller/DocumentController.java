package com.placement.portal.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import com.placement.portal.dto.DocumentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Parameter;
import com.placement.portal.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // ==========================================
    // Upload Document
    // ==========================================

    @PostMapping(
        value = "/upload/{studentId}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<DocumentResponseDTO> uploadDocument(

        @PathVariable Long studentId,

        @Parameter(description = "Upload Resume")
        @RequestParam("file") MultipartFile file,

        @RequestParam("documentType") String documentType)

        throws IOException {

    DocumentResponseDTO document = documentService.uploadDocument(
            studentId,
            file,
            documentType);

    return new ResponseEntity<>(document, HttpStatus.CREATED);
}

    // ==========================================
    // Get Documents of Student
    // ==========================================

    @GetMapping("/student/{studentId}")
    ResponseEntity<List<DocumentResponseDTO>> getDocumentsByStudent(

            @PathVariable Long studentId) {

        return ResponseEntity.ok(

                documentService.getDocumentsByStudent(studentId));
    }

    // ==========================================
    // Get Document By ID
    // ==========================================

    @GetMapping("/{documentId}")
    ResponseEntity<DocumentResponseDTO> getDocumentById(

            @PathVariable Long documentId) {

        return ResponseEntity.ok(

                documentService.getDocumentById(documentId));
    }

    // ==========================================
    // Delete Document
    // ==========================================

    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(

            @PathVariable Long documentId)

            throws IOException {

        documentService.deleteDocument(documentId);

        return ResponseEntity.ok(
                "Document deleted successfully.");
    }

    // ==========================================
    // Verify Document
    // ==========================================

    @PutMapping("/verify/{documentId}")
    ResponseEntity<DocumentResponseDTO> verifyDocument(

            @PathVariable Long documentId) {

        return ResponseEntity.ok(

                documentService.verifyDocument(documentId));
    }

    // ==========================================
// Download Document
// ==========================================

@GetMapping("/download/{documentId}")
public ResponseEntity<Resource> downloadDocument(
        @PathVariable Long documentId)
        throws IOException {

    Resource resource = documentService.downloadDocument(documentId);

    String contentType = Files.probeContentType(
            resource.getFile().toPath());

    if (contentType == null) {
        contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()

            .contentType(MediaType.parseMediaType(contentType))

            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + resource.getFilename() + "\"")

            .body(resource);
}
}