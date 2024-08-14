package com.example.csu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplyDocumentDto {

    private Long id;
    private String documentName;
    private String documentSubject;
    private String createdBy;
    private String status;
    private LocalDateTime createdDateTime;

}
