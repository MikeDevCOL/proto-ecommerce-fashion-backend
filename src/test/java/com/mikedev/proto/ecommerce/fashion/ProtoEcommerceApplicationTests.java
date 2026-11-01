package com.mikedev.proto.ecommerce.fashion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mikedev.proto.ecommerce.fashion.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProtoEcommerceApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
		assertEquals(15, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products", Integer.class));
		assertEquals(45, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product_variants", Integer.class));
		assertEquals(0, jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM products p WHERE (SELECT COUNT(*) FROM product_variants v WHERE v.product_id = p.id) < 3",
				Integer.class));
		assertEquals(0, jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM products p WHERE (SELECT COUNT(DISTINCT v.color_id) FROM product_variants v WHERE v.product_id = p.id) < 3",
				Integer.class));
		assertEquals(0, jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM product_variants WHERE sale_price IS NULL", Integer.class));
		assertEquals(45, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product_images", Integer.class));
		assertEquals(15, productRepository.findSummaries(
				PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "product.title"))).getTotalElements());
	}

	@Test
	void findSummariesCanBeSortedByProductId() {
		var summaries = productRepository.findSummaries(
				PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "product.id")));

		assertEquals(15, summaries.getTotalElements());
		var ids = summaries.getContent().stream().map(summary -> summary.getId()).toList();
		assertEquals(ids.stream().sorted().toList(), ids);
	}

}
