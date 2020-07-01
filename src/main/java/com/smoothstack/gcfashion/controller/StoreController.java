package com.smoothstack.gcfashion.controller;

import java.util.ArrayList;
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
	
	//
	//
	//  SHOP Path
	//
	//
	
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
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
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
	
	//
	//
	//  SHOP / TRANSACTION Path
	//
	//
	
	@GetMapping("/shop/transactions/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {

		// read transaction by Id passed in body
		Transaction transaction = storeService.findTransactionById(id);

		// a successful request should produce non-null transaction return value
		if (transaction != null) {
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/shop/transactions/open/userid/{userId}")
	public ResponseEntity<Transaction> getOpenTransactionByUserId(@PathVariable Long userId) {

		Transaction transaction = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// set productList to null if no open transaction for userId was found
		// otherwise, create a product list representing users shopping cart
		// from that transaction info
		if (retVal == -1) {
			// return response
			return ResponseEntity.notFound().build();
		} else {
			transaction = storeService.findTransactionById(retVal);
			
			// return response
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		}
	}
	
	@GetMapping("/shop/transactions/cart/userid/{userId}")
	public ResponseEntity<List<Product>> getCartByUserId(@PathVariable Long userId) {

		List<Product> productList = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// set productList to null if no open transaction for userId was found
		// otherwise, create a product list representing users shopping cart
		// from that transaction info
		if (retVal == -1) {
			productList = new ArrayList<>(); // create empty list
		} else {
			productList = storeService.getCompleteTransactionDetails(retVal);
		}

		// return response
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/shop/transactions/open/coupon/userid/{userId}")
	public ResponseEntity<Coupon> getCouponByUserId(@PathVariable Long userId) {

		Coupon coupon = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// return 404 if no open transaction for userId was found
		// otherwise, return coupon associated with open transaction for user
		if (retVal == -1) {
			// return response
			return ResponseEntity.notFound().build();
		} else {
			coupon = storeService.getCoupon(retVal);

			// return response
			return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		}
	}
	
	@PostMapping("/shop/transactions/open/coupon")
	public ResponseEntity<String> addCouponByUserId(@RequestBody Transaction t) {

		Transaction existingTransaction = null;
		Integer returnInt = -1; // for determining HttpStatus

		Long retVal = storeService.openTransactionsExist(t.getUserId());

		// an open transaction exists, update transaction
		if (retVal != -1) {
			// get a copy of the existing transaction
			existingTransaction = storeService.findTransactionById(retVal);

			// set new coupon values
			existingTransaction.setCoupons(t.getCoupons());

			// update the existing transaction
			returnInt = storeService.saveTransaction(existingTransaction);
		}

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			// get a copy of the existing transaction
			existingTransaction = storeService.findTransactionById(retVal);

			// set new coupon values
			existingTransaction.setCoupons(null);

			// update the existing transaction
			returnInt = storeService.saveTransaction(existingTransaction);

			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
	}
	
	//
	//
	//  ACCOUNT Path
	//
	//
	
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
	
	@PutMapping("/account/users/{userId}/transactions")
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
