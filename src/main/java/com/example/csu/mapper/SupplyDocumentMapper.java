package com.example.csu.mapper;

import com.example.csu.dto.SupplyDocumentDto;
import com.example.csu.model.SupplyDocument;


public class SupplyDocumentMapper {

    public static SupplyDocumentDto toDto(SupplyDocument document) {
        return  SupplyDocumentDto.builder()
                .id(document.getId())
                .documentSubject(document.getDocumentSubject())
                .documentName(document.getDocumentName())
                .createdDateTime(document.getCreatedDateTime())
                .createdBy(document.getUser().getFullName())
                .status(document.getStatus())
                .build();
    }

    public static SupplyDocument toEntity(SupplyDocumentDto document) {
        return  SupplyDocument.builder()
                .id(document.getId())
                .documentSubject(document.getDocumentSubject())
                .documentName(document.getDocumentName())
                .createdDateTime(document.getCreatedDateTime())
                .status(document.getStatus())
                .build();
    }
}
