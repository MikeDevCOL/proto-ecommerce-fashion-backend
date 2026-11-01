package com.mikedev.proto.ecommerce.fashion.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class ProductResponse {
  private final Long id;
  private final String title;
  private final String shortDescription;
  private final String mainImgUrl;
}
