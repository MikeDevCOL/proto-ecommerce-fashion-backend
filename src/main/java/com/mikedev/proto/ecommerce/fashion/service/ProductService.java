package com.mikedev.proto.ecommerce.fashion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikedev.proto.ecommerce.fashion.dtos.request.ProductRequest;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductDetailsResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSummaryResponse;
import com.mikedev.proto.ecommerce.fashion.models.Product;
import com.mikedev.proto.ecommerce.fashion.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  @Transactional(readOnly = true)
  public Page<ProductSummaryResponse> findSummaries(Pageable pageable) {

    return repository.findSummaries(pageable);

  }

  @Transactional(readOnly = true)
  public ProductDetailsResponse findProductDetailsResponseById(Long id) {

    var productBaseResponse = repository
        .findProductDetailsById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    var colors = repository
        .findColorsByProductId(id);

    var sizes = repository.findSizesByProductId(id);

    return new ProductDetailsResponse(
        productBaseResponse.getId(),
        productBaseResponse.getTitle(),
        sizes,
        colors,
        productBaseResponse.getMinPrice(),
        productBaseResponse.getMainImgUrl());

  }

  @Transactional
  public ProductResponse createNewProduct(ProductRequest request) {

    if (request.getMainImgUrl() == null || request.getMainImgUrl().isEmpty()) {
      request = request.withMainImgUrl("https://placehold.co/600x600");
    }

    var productEntity = Product.builder()
        .title(request.getTitle())
        .shortDescription(request.getShortDescription())
        .mainImgUrl(request.getMainImgUrl())
        .build();

    var entitySaved = repository.save(productEntity);

    return new ProductResponse(
        entitySaved.getId(),
        entitySaved.getTitle(),
        entitySaved.getShortDescription(),
        entitySaved.getMainImgUrl());
  }
}
