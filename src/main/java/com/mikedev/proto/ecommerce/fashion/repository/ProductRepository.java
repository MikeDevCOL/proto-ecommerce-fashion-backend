package com.mikedev.proto.ecommerce.fashion.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mikedev.proto.ecommerce.fashion.dtos.response.ColorResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductBaseResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSizeResponse;
import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSummaryResponse;
import com.mikedev.proto.ecommerce.fashion.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("""
        SELECT
          new com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSummaryResponse(
            v.product.id,
            v.product.title,
            MIN(v.originalPrice) AS minPrice,
            COUNT(v.sku) AS variantCount,
            v.product.mainImgUrl
          )
        FROM ProductVariant v
        WHERE v.isActive = true
        GROUP BY v.product.id, v.product.title, v.product.mainImgUrl
      """)
  Page<ProductSummaryResponse> findSummaries(Pageable pageable);

  @Query("""
        SELECT
          new com.mikedev.proto.ecommerce.fashion.dtos.response.ProductBaseResponse(
            p.id,
            p.title,
            p.shortDescription,
            p.mainImgUrl,
            (
              SELECT MIN(v.originalPrice) AS minPrice
              FROM Product p
              JOIN p.productVariants v
              WHERE p.id = :id
              GROUP BY(p.id)
            ) AS minPrice
          )
        FROM Product p
        WHERE p.id = :id
      """)
  Optional<ProductBaseResponse> findProductDetailsById(@Param("id") Long id);

  @Query("""
          SELECT new com.mikedev.proto.ecommerce.fashion.dtos.response.ColorResponse(
              c.id,
              c.hexCode
          )
          FROM Product p
          JOIN p.productVariants v
          JOIN v.color c
          WHERE p.id = :id AND
          v.isActive = true
      """)
  Set<ColorResponse> findColorsByProductId(@Param("id") Long id);

  @Query("""
          SELECT new com.mikedev.proto.ecommerce.fashion.dtos.response.ProductSizeResponse(
              s.id,
              s.code,
              s.label
          )
          FROM Product p
          JOIN p.productVariants v
          JOIN v.size s
          WHERE p.id = :id AND
          v.isActive = true
      """)
  Set<ProductSizeResponse> findSizesByProductId(@Param("id") Long id);

}
