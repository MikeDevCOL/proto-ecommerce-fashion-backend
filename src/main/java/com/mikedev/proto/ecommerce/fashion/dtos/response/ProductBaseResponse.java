package com.mikedev.proto.ecommerce.fashion.dtos.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
@Builder
public class ProductBaseResponse {

  private final Long id;
  private final String title;
  private final String shortDescription;
  private final String mainImgUrl;
  private final BigDecimal minPrice;

}
