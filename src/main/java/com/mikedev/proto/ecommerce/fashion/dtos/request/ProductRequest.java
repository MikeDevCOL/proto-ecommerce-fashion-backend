package com.mikedev.proto.ecommerce.fashion.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
public class ProductRequest {

  @NotBlank
  private final String title;

  @NotBlank
  private final String shortDescription;

  private final String mainImgUrl;
}
