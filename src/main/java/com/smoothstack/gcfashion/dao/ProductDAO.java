package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{
	List<Product> findByCatId(Long catId);
	List<Product> findByProductId(Long productId);
	
	@Query("SELECT p FROM Product p WHERE p.catId = :catId AND p.subcatId = :subcatId")
    public List<Product> find(@Param("catId") long catId, @Param("subcatId") long subcatId);
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    public List<Product> findLike(@Param("productName") String productName);
}
