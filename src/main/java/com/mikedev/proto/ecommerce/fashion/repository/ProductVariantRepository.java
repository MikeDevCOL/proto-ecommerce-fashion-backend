package com.mikedev.proto.ecommerce.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductVariantResponse;
import com.mikedev.proto.ecommerce.fashion.models.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

  @Query("""
        SELECT new com.mikedev.proto.ecommerce.fashion.dtos.response.ProductVariantResponse(
          pv.id,
          p.id,
          pv.color.hexCode,
          pv.size.code,
          pv.originalPrice,
          pv.salePrice,
          null
        ) FROM ProductVariant pv
          JOIN pv.product p
          WHERE p.id = :productId
      """)
  List<ProductVariantResponse> findAllVariantsByProductId(@Param("productId") Long productId);

}
