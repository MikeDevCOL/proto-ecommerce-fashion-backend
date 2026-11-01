package com.mikedev.proto.ecommerce.fashion.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "productVariants")
@EqualsAndHashCode(exclude = "productVariants")
@Getter
@Setter
@Builder
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100, unique = true)
  private String title;

  @Column(nullable = false, length = 200)
  private String shortDescription;

  @OneToMany(mappedBy = "product", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
  private Set<ProductVariant> productVariants;

  @Column(nullable = false)
  private String mainImgUrl;

}
