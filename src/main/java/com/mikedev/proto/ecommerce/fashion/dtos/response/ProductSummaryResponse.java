package com.mikedev.proto.ecommerce.fashion.dtos.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@With
@Builder
@AllArgsConstructor
@Data
public class ProductSummaryResponse {

	private final Long id;
	private final String title;
	private final BigDecimal minPrice;
	private final Long variantCount;
	private final String mainImageUrl;
}
