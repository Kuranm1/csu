package com.example.csu.service;

import com.example.csu.dto.SupplyDocumentDto;
import com.example.csu.mapper.SupplyDocumentMapper;
import com.example.csu.model.SupplyDocument;
import com.example.csu.repository.SupplyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyDocumentService {

    @Autowired
    private SupplyDocumentRepository supplyDocumentRepository;

    public List<SupplyDocument> getSupplyDocuments() {
        return supplyDocumentRepository.findAll();
    }

    public SupplyDocumentDto getSupplyDocument(Long id) {
        Optional<SupplyDocument> document = supplyDocumentRepository.findById(id);
        return document.map(SupplyDocumentMapper::toDto).orElse(null);
    }
    public List<SupplyDocument> getSupplyDocumentsByUser(Long id) {
        return supplyDocumentRepository.findAllByUserId(id);
    }

    public SupplyDocumentDto addSupplyDocument(SupplyDocument document) {
        return SupplyDocumentMapper.toDto(supplyDocumentRepository.save(document));
    }

    public void deleteSupplyDocument(Long id) {
        supplyDocumentRepository.deleteById(id);
    }

    public void setSupplyDocumentStatus(SupplyDocumentDto dto){
        Optional<SupplyDocument> document = supplyDocumentRepository.findById(dto.getId());
        if (document.isPresent()){
            SupplyDocument updatedRecord =  document.get();
            updatedRecord.setStatus(dto.getStatus());
            supplyDocumentRepository.save(updatedRecord);
        }
    }
}
