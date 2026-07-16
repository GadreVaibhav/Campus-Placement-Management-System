package com.placement.portal.mapper;

import com.placement.portal.dto.DocumentResponseDTO;
import com.placement.portal.entity.Document;

public class DocumentMapper {

    private DocumentMapper() {
    }

    public static DocumentResponseDTO toResponseDTO(Document document) {

        DocumentResponseDTO dto = new DocumentResponseDTO();

        dto.setId(document.getId());

        dto.setDocumentType(
                document.getDocumentType().name());

        dto.setFileName(document.getFileName());

        dto.setFileSize(document.getFileSize());

        dto.setMimeType(document.getMimeType());

        dto.setIsVerified(document.getIsVerified());

        dto.setUploadedAt(document.getUploadedAt());

        return dto;
    }
}