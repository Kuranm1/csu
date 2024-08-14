package com.example.csu.controller;

import com.example.csu.dto.UserDto;
import com.example.csu.model.Warehouse;
import com.example.csu.service.AuthService;
import com.example.csu.service.WarehouseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {


    @Autowired
    private WarehouseService warehouseService;


    @GetMapping
    public List<Warehouse> getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouse(@PathVariable Long id) {
        return warehouseService.getWarehouse(id);
    }
    @PostMapping
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        warehouse.setCreatedBy(user.getFullName());
        warehouse.setCreatedDateTime(LocalDateTime.now());
        return warehouseService.addWarehouse(warehouse);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return HttpStatus.OK;
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportCsv() {
        List<Warehouse> data = warehouseService.getWarehouses();
        try {
            String filePath = warehouseService.generateCsv(data, "sample");
            return ResponseEntity.status(HttpStatus.OK).body("CSV file created: " + filePath);
        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating CSV file");
        }
    }
}
