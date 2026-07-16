package com.placement.portal.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "documents")
public class Document {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Enumerated(EnumType.STRING)
@Column(name = "document_type")
private DocumentType documentType;
public Document() {
}
@PrePersist
public void prePersist() {
    uploadedAt = LocalDateTime.now();
}
@Column(name = "file_name")
private String fileName;

@Column(name = "file_path")
private String filePath;

@Column(name = "file_size")
private Long fileSize;

@Column(name = "mime_type")
private String mimeType;

@Column(name = "is_verified")
private Boolean isVerified;

@Column(name = "uploaded_at", nullable = false, updatable = false)
private LocalDateTime uploadedAt;
@ManyToOne
@JoinColumn(name = "student_id")
@JsonBackReference
private Student student;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public DocumentType getDocumentType() {
    return documentType;
}

public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
}

public String getFileName() {
    return fileName;
}

public void setFileName(String fileName) {
    this.fileName = fileName;
}

public String getFilePath() {
    return filePath;
}

public void setFilePath(String filePath) {
    this.filePath = filePath;
}

public Long getFileSize() {
    return fileSize;
}

public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
}

public String getMimeType() {
    return mimeType;
}

public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
}

public Boolean getIsVerified() {
    return isVerified;
}

public void setIsVerified(Boolean isVerified) {
    this.isVerified = isVerified;
}
public LocalDateTime getUploadedAt() {
    return uploadedAt;
}

public void setUploadedAt(LocalDateTime uploadedAt) {
    this.uploadedAt = uploadedAt;
}

public Student getStudent() {
    return student;
}

public void setStudent(Student student) {
    this.student = student;
}
}
