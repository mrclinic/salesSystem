package com.example.salessystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salessystem.exception.ResourceNotFoundException;
import com.example.salessystem.model.Sales;
import com.example.salessystem.repository.ClientRepository;
import com.example.salessystem.repository.SalesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SalesController {

	static final Logger LOGGER = Logger.getLogger("salesSystemLogger");
	@Autowired
	SalesRepository salesRepository;
	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/clients/{clientId}/sales")
	public Page<Sales> getAllSalesByClientId(@PathVariable(value = "clientId") Long clientId, Pageable pageable) {
		return salesRepository.findByClientId(clientId, pageable);
	}

	@PostMapping("/clients/{clientId}/sales")
	public Sales createSales(@PathVariable(value = "clientId") Long clientId, @Valid @RequestBody Sales sales) {
		return clientRepository.findById(clientId).map(client -> {
			sales.setClient(client);
			return salesRepository.save(sales);
		}).orElseThrow(() -> new ResourceNotFoundException("clientId", "clientId", clientId));
	}

	@PutMapping("/clients/{clientId}/sales/{salesId}")
	public Sales updateSales(@PathVariable(value = "clientId") Long clientId,
			@PathVariable(value = "salesId") Long salesId, @Valid @RequestBody Sales salesRequest)
			throws JsonProcessingException {
		if (!clientRepository.existsById(clientId)) {
			throw new ResourceNotFoundException("clientId", "clientId", clientId);
		}

		// Creating the ObjectMapper object
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(salesRequest);
		LOGGER.info("updateSales(): Info-->clientId: " + clientId + ", salesRequest: " + jsonString);
		return salesRepository.findById(salesId).map(sales -> {
			sales.setSeller(salesRequest.getSeller());
			sales.setTotal(salesRequest.getTotal());
			return salesRepository.save(sales);
		}).orElseThrow(() -> new ResourceNotFoundException("SalesId", "SalesId", salesId));
	}

	@DeleteMapping("/clients/{clientId}/sales/{salesId}")
	public ResponseEntity<?> deleteSales(@PathVariable(value = "clientId") Long clientId,
			@PathVariable(value = "salesId") Long salesId) {
		return salesRepository.findByIdAndClientId(salesId, clientId).map(sale -> {
			salesRepository.delete(sale);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"sales not found with salesId " + salesId + " and clientId " + clientId,
				"sales not found with salesId " + salesId + " and clientId " + clientId, clientId));
	}
}
