package com.mikedev.proto.ecommerce.fashion.dtos.request;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
public class ProductVariantRequest {

  private final String sku;
  private final String hexColorCode;
  private final String sizeCode;
  private final BigDecimal salePrice;
  private final BigDecimal originalPrice;
  private final Set<String> imgUrls;
}
