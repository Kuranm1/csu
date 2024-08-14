package com.example.csu.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class SupplyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private String documentSubject;
    private String status;
    private LocalDateTime createdDateTime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
