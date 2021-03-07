package com.example.salessystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salessystem.exception.ResourceNotFoundException;
import com.example.salessystem.model.Product;
import com.example.salessystem.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apip")
public class ProductController {

	@Autowired
	ProductRepository ProductRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return ProductRepository.findAll();
	}

	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {
		return ProductRepository.save(product);
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId) {
		return ProductRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) {

		Product product = ProductRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		product.setDescription(productDetails.getDescription());
		product.setName(productDetails.getName());
		product.setCategory(productDetails.getCategory());
		Product updatedProduct = ProductRepository.save(product);
		return updatedProduct;
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		Product product = ProductRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		ProductRepository.delete(product);

		return ResponseEntity.ok().build();
	}
}
