package com.smoothstack.gcfashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/gcfashions")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		// read all categories
		List<Category> categories = storeService.findAllCategories();
		System.out.println("Number of categories read: " + categories.size());

		// a successful request should produce a list not null with a size greater than
		// zero
		if (categories != null && categories.size() > 0) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} else {
			System.out.println("Inside fail else branch");
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("shop/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		
		// read all products
		List<Product> products = storeService.findAllProducts();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			System.out.println("Inside fail else branch");
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("shop/products/{productId}")
	public ResponseEntity<List<Product>> getProductByProductId(@PathVariable long productId) {
		
		// read products
		List<Product> products = storeService.findProductsByProductId(productId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			System.out.println("Inside fail else branch");
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	};
	
	@GetMapping("/shop/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCatId(@PathVariable long catId) {
		
		// read products
		List<Product> products = storeService.findProductsByCatId(catId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			System.out.println("Inside fail else branch");
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	};
}
