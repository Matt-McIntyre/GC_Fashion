package com.smoothstack.gcfashion.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/test")
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
}
