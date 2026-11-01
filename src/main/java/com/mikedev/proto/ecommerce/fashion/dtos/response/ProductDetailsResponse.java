package com.mikedev.proto.ecommerce.fashion.dtos.response;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
@Builder
public class ProductDetailsResponse {

  private final Long id;
  private final String title;
  private final Set<ProductSizeResponse> availableSizes;
  private final Set<ColorResponse> availableColors;
  private final BigDecimal minPrice;
  private final String mainImgUrl;

}
