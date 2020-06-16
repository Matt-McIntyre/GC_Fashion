package com.smoothstack.gcfashion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/gcfashions/shop")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		
		// read all products
		List<Product> products = storeService.findAllProducts();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Optional<Product>> getProductByProductId(@PathVariable long productId) {
		
		// read products
		Optional<Product> products = storeService.findProductsByProductId(productId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products.isPresent()  != true) {
			return new ResponseEntity<Optional<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<List<Coupon>> findAllCoupons() {
		
		// read Coupons
		List<Coupon> coupons = storeService.findAllCoupons();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (coupons  != null && coupons.size() > 0) {
			return new ResponseEntity<List<Coupon>>(coupons , HttpStatus.OK);
		} else {
			// Coupons  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/transactions/{userId}")
	public ResponseEntity<Optional<Transaction>> findTransactionsByUserId(@PathVariable long userId) {
		// read all stores
		Optional<Transaction> transactions = storeService.findTransactionsByUserId(userId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (transactions.isPresent()  != true) {
			return new ResponseEntity<Optional<Transaction>>(transactions, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {

		Integer returnInt = -1; // for determining HttpStatus

		// update a transaction
		returnInt = storeService.saveTransaction(transaction);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/transactions/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable long transactionId) {

		Integer returnInt = -1; // for determining HttpStatus
		// update a transaction
		returnInt = storeService.deleteTransaction(transactionId);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		// read all categories
		List<Category> categories = storeService.findAllCategories();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (categories != null && categories.size() > 0) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/subcategories")
	public ResponseEntity<List<Subcategory>> getAllSubcategory() {
		
		// read all subcategories
		List<Subcategory> subcategories = storeService.findAllSubcategories();
		System.out.println("Number of subcategories read: " + subcategories.size());
		// a successful request should produce a list not null with a size greater than
		// zero
		if (subcategories != null && subcategories.size() > 0) {
			return new ResponseEntity<List<Subcategory>>(subcategories, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Optional<Product>> getProductByCatId(@PathVariable long catId) {
		
		// read products
		Optional<Product> products = storeService.findProductsByCatId(catId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products.isPresent()  != true) {
			return new ResponseEntity<Optional<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/stores")
	public ResponseEntity<List<Store>> getAllStores() {
		
		// read all stores
		List<Store> stores = storeService.findAllStores();
		System.out.println("Number of subcategories read: " + stores.size());
		// a successful request should produce a list not null with a size greater than
		// zero
		if (stores != null && stores.size() > 0) {
			return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	//might removes not in swagger urls
	@GetMapping("/account/users/{userId}")
	public ResponseEntity<Optional<User>> getUserByUserId(@PathVariable Long userId) {
		
		// read all stores
		Optional<User> user = storeService.findUserByUserId(userId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (user != null ) {
			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/account/users")
	public ResponseEntity<String> saveUser(@RequestBody User user) {

		Integer returnInt = -1; // for determining HttpStatus

		// update a transaction
		returnInt = storeService.saveUser(user);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
