package com.example.csu.repository;
import com.example.csu.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{

    List<Warehouse> findAll();
    Optional<Warehouse> findById(Long id);
}
