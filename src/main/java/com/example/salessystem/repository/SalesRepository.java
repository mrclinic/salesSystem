package com.example.salessystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salessystem.model.Sales;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

	Page<Sales> findByClientId(Long clientId, Pageable pageable);

	Optional<Sales> findByIdAndClientId(Long id, Long clientId);

}
