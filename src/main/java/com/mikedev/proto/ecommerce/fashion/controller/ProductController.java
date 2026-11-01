package com.mikedev.proto.ecommerce.fashion.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mikedev.proto.ecommerce.fashion.dtos.request.ProductRequest;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductDetailsResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSummaryResponse;
import com.mikedev.proto.ecommerce.fashion.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping("/summaries")
  public ResponseEntity<Page<ProductSummaryResponse>> findSummaries(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "product.title") String sortBy,
      @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

    String sortProperty = "id".equals(sortBy) ? "product.id" : sortBy;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortProperty));

    return ResponseEntity.ok(service.findSummaries(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailsResponse> findProductDetailsById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findProductDetailsResponseById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createNewProduct(@RequestBody ProductRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewProduct(request));
  }
}
