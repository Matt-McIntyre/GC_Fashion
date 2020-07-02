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
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/gcfashions")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/shop/products")
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
	
	@GetMapping("/shop/products/like/{productName}")
	public ResponseEntity<List<Product>> getAllProductLike(@PathVariable String productName) {
		
		// read all products
		List<Product> products = storeService.findAllProductsLike(productName);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/shop/products/{productId}")
	public ResponseEntity<List<Product>> getProductByProductId(@PathVariable long productId) {
		
		// read products
		List<Product> products = storeService.findProductsByProductId(productId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/shop/coupons")
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
	
	@GetMapping("/account/users/{userId}/transactions")
	public ResponseEntity<List<Transaction>> findTransactionsByUserId(@PathVariable long userId) {
		// read all stores
		List<Transaction> transactions = storeService.findTransactionsByUserId(userId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (transactions != null && transactions.size() > 0) {
			return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/account/users/{userId}/role")
	public ResponseEntity<String> findUserRole(@PathVariable long userId) {
		// read all stores
		String user = storeService.findUserRole(userId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (user.length() > 0) {
			return new ResponseEntity<String>(user, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/shop/account/users/{userId}/transactions")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {

		Integer returnInt = -1; // for determining HttpStatus

		// create new a transaction
		returnInt = storeService.saveTransaction(transaction);

		// indicate success or failure
		if (returnInt == 1) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/account/users/{userId}/transactions")
	public ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction, @PathVariable long userId) {

		Integer returnInt = -1; // for determining HttpStatus

		// update a transaction
		transaction.setTransactionId(userId);
		returnInt = storeService.saveTransaction(transaction);

		// indicate success or failure
		if (returnInt == 1) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
//	@DeleteMapping("/transactions/{transactionId}")
//	public ResponseEntity<String> deleteTransaction(@PathVariable long transactionId) {
//
//		Integer returnInt = -1; // for determining HttpStatus
//		// update a transaction
//		returnInt = storeService.deleteTransaction(transactionId);
//
//		// indicate success or failure
//		if (returnInt == 0) {
//			return new ResponseEntity<String>("Success", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@GetMapping("/shop/categories")
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
	
	@GetMapping("/shop/categories/{catId}/subcategories/{subcatId}")
	public ResponseEntity<List<Product>> getAllSubcategory(@PathVariable long catId,@PathVariable long subcatId) {
		
		// read Product
		List<Product> subcategories = storeService.findProductsBySubcatId(catId, subcatId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (subcategories != null && subcategories.size() > 0) {
			return new ResponseEntity<List<Product>>(subcategories, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/shop/categories/{catId}/products")
	public ResponseEntity<List<Product>> getProductByCatId(@PathVariable long catId) {
		
		// read products
		List<Product> products = storeService.findProductsByCatId(catId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/shop/stores")
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
	public ResponseEntity<List<User>> getUserByUserId(@PathVariable Long userId) {
		
		// read all stores
		List<User> user = storeService.findUserByUserId(userId);
		// a successful request should produce a list not null with a size greater than
		// zero
		if (user != null ) {
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/account/users/{userId}")
	public ResponseEntity<String> saveUser(@RequestBody User user, @PathVariable Long userId) {
		
		Integer returnInt = -1; // for determining HttpStatus

		// update a transaction
		user.setUserId(userId);
		returnInt = storeService.saveUser(user);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	

	@PutMapping("/new/account")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		
		Integer returnInt = -1; // for determining HttpStatus

		returnInt = storeService.saveUser(user);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
