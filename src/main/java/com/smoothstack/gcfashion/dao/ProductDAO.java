package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{
	Optional<Product> findByCatId(Long catId);
	Optional<Product> findByProductId(Long productId);
}
