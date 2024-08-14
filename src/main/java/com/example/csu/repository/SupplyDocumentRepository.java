package com.example.csu.repository;

import com.example.csu.model.SupplyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplyDocumentRepository extends JpaRepository<SupplyDocument, Long> {
    List<SupplyDocument> findAllByUserId(Long Id);
    List<SupplyDocument> findAll();
}