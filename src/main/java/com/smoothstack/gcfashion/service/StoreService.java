package com.smoothstack.gcfashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.gcfashion.dao.CategoryDAO;
import com.smoothstack.gcfashion.entity.Category;

@Service
public class StoreService {

	@Autowired
	CategoryDAO cDAO;
	
	/**
	 * Returns all categories
	 */
	public List<Category> findAllCategories() {
		System.out.println("Inside findAllCategories method in StoreService");
		
		return cDAO.findAll();
	}

}
