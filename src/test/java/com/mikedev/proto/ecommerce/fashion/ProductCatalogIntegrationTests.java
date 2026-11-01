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
class ProductCatalogIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void summariesArePagedAndSortedByTitle() throws Exception {
    mockMvc.perform(get("/api/products/summaries")
        .param("page", "0")
        .param("size", "2")
        .param("sortBy", "product.title")
        .param("direction", "ASC"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.length()").value(2))
        .andExpect(jsonPath("$.content[0].title").value("Canvas Chore Coat"))
        .andExpect(jsonPath("$.totalElements").value(15));
  }

  @Test
  void productDetailsContainPriceAndAvailableOptions() throws Exception {
    mockMvc.perform(get("/api/products/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Classic Cotton T-Shirt"))
        .andExpect(jsonPath("$.minPrice").value(23.00))
        .andExpect(jsonPath("$.availableSizes.length()").value(3))
        .andExpect(jsonPath("$.availableColors.length()").value(3));
  }
}