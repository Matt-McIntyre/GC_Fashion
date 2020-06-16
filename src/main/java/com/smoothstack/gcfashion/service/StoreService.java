package com.smoothstack.gcfashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.gcfashion.dao.CategoryDAO;
import com.smoothstack.gcfashion.dao.CouponDAO;
import com.smoothstack.gcfashion.dao.SubcategoryDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.StoreDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.UserDAO;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.User;

@Service
public class StoreService {

	@Autowired
	CategoryDAO cDAO;
	
	@Autowired
	CouponDAO cpDAO;
	
	@Autowired
	SubcategoryDAO scDAO;
	
	@Autowired
	StoreDAO sDAO;
	
	@Autowired
	ProductDAO pDAO;
	
	@Autowired
	TransactionDAO tDAO;
	
	@Autowired
	UserDAO uDAO;
	
	/**
	 * Returns all categories
	 */
	public List<Category> findAllCategories() {
		
		return cDAO.findAll();
	};
	
	/**
	 * Returns all subcategories
	 */
	public List<Subcategory> findAllSubcategories() {
		
		return scDAO.findAll();
	};
	
	/**
	 * Returns all stores
	 */
	public List<Store> findAllStores() {
		
		return sDAO.findAll();
	};
	
	/**
	 * Returns transactions by userId
	 */
	public List<Transaction> findTransactionsByUserId(long userId) {
		
		return tDAO.find(userId);
	};
	
	public Integer saveTransaction(Transaction transaction) {

			try {
				// create the new record
				tDAO.save(transaction);
			} catch (Exception e) {
				// query error
				return -1;
			}

		return 0;
	}
	
	public Integer deleteTransaction(long transactionId) {
		try {
			tDAO.deleteById(transactionId);
			return 1;
		} catch (Exception e) {
			// query error
			return 0;
		} 
				
	}
	
	
	/**
	 * Returns all coupons
	 */
	public List<Coupon> findAllCoupons() {
		return cpDAO.findAll();
	};
	
	/**
	 * Returns all products
	 */
	public List<Product> findAllProducts() {
		return pDAO.findAll();
	};

	/**
	 * Returns all products by catId
	 */
	public List<Product> findProductsByCatId(long catId) {
		return pDAO.findByCatId(catId);
	};
	
	/**
	 * Returns all products by catId
	 */
	public List<Product> findProductsByProductId(long productId) {
		return pDAO.findByProductId(productId);
	};
	
	public User findUserByUserId(long userId) {
		return uDAO.findByUserId(userId);
	};
	
	public Integer saveUser(User user) {

		try {
			// create the new record
			uDAO.save(user);
		} catch (Exception e) {
			// query error
			System.out.print(e);
			return -1;
		}

	return 0;
}
}
