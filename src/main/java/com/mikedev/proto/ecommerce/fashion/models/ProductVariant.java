package com.mikedev.proto.ecommerce.fashion.models;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "product_variants")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 12, nullable = false)
  private String sku;

  @ManyToOne
  @JoinColumn(nullable = false, name = "product_id")
  private Product product;

  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "color_id", nullable = false)
  private Color color;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "size_id", nullable = false)
  private Size size;

  @Column(name = "original_price", precision = 12, scale = 2, nullable = false)
  private BigDecimal originalPrice;

  @Column(name = "sale_price", precision = 12, scale = 2)
  private BigDecimal salePrice;

  @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
  private Set<ProductImage> productImages;

  @Column(nullable = false)
  @Builder.Default
  private boolean isActive = true;

  public String generateSku() {
    var generatedSku = formatCode(product.getTitle()) +
        "-" + formatCode(color.getName()) + "-" +
        "-" + formatCode(size.getCode());

    return generatedSku.toUpperCase();
  }

  private String formatCode(String item) {
    return item.substring(0, 2);
  }
}
