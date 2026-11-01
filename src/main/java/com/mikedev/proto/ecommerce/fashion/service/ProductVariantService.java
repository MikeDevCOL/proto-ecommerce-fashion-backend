package com.mikedev.proto.ecommerce.fashion.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductImageResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductVariantResponse;
import com.mikedev.proto.ecommerce.fashion.repository.ProductImageRepository;
import com.mikedev.proto.ecommerce.fashion.repository.ProductVariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

  private final ProductVariantRepository repository;
  private final ProductImageRepository productImageRepository;

  @Transactional(readOnly = true)
  public List<ProductVariantResponse> findAllVariantsByProductId(Long productId) {
    List<ProductVariantResponse> variants = repository.findAllVariantsByProductId(productId);

    if (variants.isEmpty())
      return variants;

    List<Long> ids = variants.stream().map(ProductVariantResponse::getId).toList();

    Map<Long, Set<String>> imagesByVariant = productImageRepository.findImagesByVariantIds(ids)
        .stream()
        .collect(Collectors.groupingBy(
            ProductImageResponse::getVariantId,
            Collectors.mapping(ProductImageResponse::getImgUrl, Collectors.toSet())));

    return variants.stream().map(
        v -> {
          var imgUrls = imagesByVariant.getOrDefault(v.getId(), Set.of());
          return v.withImgUrls(imgUrls);
        })
        .toList();
  }
}
