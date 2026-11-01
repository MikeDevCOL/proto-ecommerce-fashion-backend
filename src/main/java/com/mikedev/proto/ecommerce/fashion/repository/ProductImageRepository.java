package com.mikedev.proto.ecommerce.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mikedev.proto.ecommerce.fashion.dtos.response.ProductImageResponse;
import com.mikedev.proto.ecommerce.fashion.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

  @Query("""
      SELECT new com.mikedev.proto.ecommerce.fashion.dtos.response.ProductImageResponse(
          pi.productVariant.id,
          pi.imgUrl
      )
      FROM ProductImage pi
      WHERE pi.productVariant.id IN :variantIds
      ORDER BY productVariant
      """)
  List<ProductImageResponse> findImagesByVariantIds(@Param("variantIds") List<Long> variantIds);

}
