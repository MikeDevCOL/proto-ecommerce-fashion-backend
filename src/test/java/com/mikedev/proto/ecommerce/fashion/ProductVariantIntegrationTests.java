package com.mikedev.proto.ecommerce.fashion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductVariantIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void variantsIncludeTheirProductImages() throws Exception {
    mockMvc.perform(get("/api/products/1/variants"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].imgUrls.length()").value(1))
        .andExpect(jsonPath("$[1].imgUrls.length()").value(1))
        .andExpect(jsonPath("$[2].imgUrls.length()").value(1));
  }
}