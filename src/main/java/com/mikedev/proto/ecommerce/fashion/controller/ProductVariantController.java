package com.mikedev.proto.ecommerce.fashion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductVariantResponse;
import com.mikedev.proto.ecommerce.fashion.service.ProductVariantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products/{productId}/variants")
public class ProductVariantController {

  private final ProductVariantService service;

  @GetMapping
  public ResponseEntity<List<ProductVariantResponse>> list(
      @PathVariable Long productId) {

    return ResponseEntity.ok(service.findAllVariantsByProductId(productId));
  }

}
