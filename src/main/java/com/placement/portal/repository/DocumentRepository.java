package com.placement.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placement.portal.entity.Document;
import com.placement.portal.entity.DocumentType;
import com.placement.portal.entity.Student;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByStudent(Student student);

    Optional<Document> findByStudentAndDocumentType(
            Student student,
            DocumentType documentType);

    boolean existsByStudentAndDocumentType(
            Student student,
            DocumentType documentType);
}