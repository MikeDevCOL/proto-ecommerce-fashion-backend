package com.mikedev.proto.ecommerce.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikedev.proto.ecommerce.fashion.models.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

  List<Size> findAllByCodeIn(Iterable<String> codes);

}
