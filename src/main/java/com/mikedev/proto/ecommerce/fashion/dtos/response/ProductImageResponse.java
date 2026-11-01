package com.mikedev.proto.ecommerce.fashion.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
@Builder
public class ProductImageResponse {

  private final Long variantId;
  private final String imgUrl;

}
