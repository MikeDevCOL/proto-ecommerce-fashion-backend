package com.mikedev.proto.ecommerce.fashion.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
@Builder
public class ProductSizeResponse {

  private final Long id;
  private final String code;
  private final String label;
}