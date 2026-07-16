package com.placement.portal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.placement.portal.dto.DocumentResponseDTO;

public interface DocumentService {

    DocumentResponseDTO uploadDocument(
            Long studentId,
            MultipartFile file,
            String documentType) throws IOException;

    List<DocumentResponseDTO> getDocumentsByStudent(Long studentId);

    DocumentResponseDTO getDocumentById(Long documentId);

    void deleteDocument(Long documentId) throws IOException;

    DocumentResponseDTO verifyDocument(Long documentId);
    Resource downloadDocument(Long documentId) throws IOException;

}