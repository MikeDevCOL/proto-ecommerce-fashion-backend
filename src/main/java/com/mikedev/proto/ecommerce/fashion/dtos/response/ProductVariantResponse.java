package com.mikedev.proto.ecommerce.fashion.dtos.response;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class ProductVariantResponse {

  private final Long id;
  private final Long productId;
  private final String hexColorCode;
  private final String sizeCode;
  private final BigDecimal salePrice;
  private final BigDecimal originalPrice;
  private final Set<String> imgUrls;
}
