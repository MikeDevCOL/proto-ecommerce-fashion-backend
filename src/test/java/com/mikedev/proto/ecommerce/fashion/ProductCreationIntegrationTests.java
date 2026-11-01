package com.mikedev.proto.ecommerce.fashion;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductCreationIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createsProductAndUsesPlaceholderWhenImageIsMissing() throws Exception {
    String request = """
        {"title":"Created product %s","shortDescription":"Integration test description"}
        """.formatted(UUID.randomUUID());

    mockMvc.perform(post("/api/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.mainImgUrl").value("https://placehold.co/600x600"));
  }

  @Test
  void rejectsBlankRequiredFields() throws Exception {
    mockMvc.perform(post("/api/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {"title":" ","shortDescription":" "}
            """))
        .andExpect(status().isBadRequest());
  }
}