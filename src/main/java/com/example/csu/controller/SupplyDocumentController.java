package com.example.csu.controller;

import com.example.csu.dto.SupplyDocumentDto;
import com.example.csu.dto.UserDto;
import com.example.csu.mapper.SupplyDocumentMapper;
import com.example.csu.mapper.UserMapper;
import com.example.csu.model.SupplyDocument;
import com.example.csu.service.AuthService;
import com.example.csu.service.SupplyDocumentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supply-documents")
public class SupplyDocumentController {

    @Autowired
    private SupplyDocumentService supplyDocumentService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<SupplyDocumentDto> getSupplyDocuments(HttpSession session) {
        if (!authService.isManger(session)){
            UserDto user = (UserDto) session.getAttribute("user");
            return supplyDocumentService.getSupplyDocumentsByUser(user.getId()).stream()
                    .map(SupplyDocumentMapper::toDto)
                    .collect(Collectors.toList());
        }

        return supplyDocumentService.getSupplyDocuments().stream()
                .map(SupplyDocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SupplyDocumentDto getSupplyDocuments(@PathVariable Long id) {
        return supplyDocumentService.getSupplyDocument(id);
    }

    @PostMapping
    public SupplyDocumentDto addSupplyDocument(@RequestBody SupplyDocument document , HttpSession session) {
        UserDto current_user = (UserDto)session.getAttribute("user");
        document.setUser(UserMapper.toEntity(current_user));
        document.setCreatedDateTime(LocalDateTime.now());
        document.setStatus("pending");
        return supplyDocumentService.addSupplyDocument(document);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplyDocument(@PathVariable Long id) {
        supplyDocumentService.deleteSupplyDocument(id);
    }

    @PutMapping
    public ResponseEntity<String> setSupplyDocumentStatus(@RequestBody SupplyDocumentDto dto, HttpSession session) {
        if (authService.isManger(session)){
            supplyDocumentService.setSupplyDocumentStatus(dto);
            return ResponseEntity.status(HttpStatus.OK).body("Status updated");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Should be manger");
    }
}
