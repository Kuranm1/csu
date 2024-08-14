package com.example.csu.service;

import com.example.csu.model.Warehouse;
import com.example.csu.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Optional<Warehouse> getWarehouse(Long Id) {
        return warehouseRepository.findById(Id);
    }
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public String generateCsv(List<Warehouse> data, String fileName) throws IOException {
        String directory = "exported_warehouses/";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String filePath = directory + formattedDateTime +"_"+ fileName + ".csv";
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(String.join(",", Arrays.asList("ID", "Name", "Description", "Created By", "Date")));
            writer.append("\n");
            for (Warehouse warehouse : data) {
                List<String> row = new ArrayList<>();
                row.add(warehouse.getId().toString());
                row.add(warehouse.getWarehouseName());
                row.add(warehouse.getWarehouseDescription());
                row.add(warehouse.getCreatedBy());
                row.add(warehouse.getCreatedDateTime().toString());
                writer.append(String.join(",", row));
                writer.append("\n");
            }

        }
        return filePath;
    }
}
