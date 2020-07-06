package com.smoothstack.gcfashion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

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

	// Stripe secret testing key
	private static final String STRIPE_SECRET = "sk_test_51GxNidEC7SOZT967RsMuhDj5iy2msgv9sfBc8hysEbi1SOMpDvJBQeZG5aB61zF0nUXH34bMK2iWZFs94FkoiEAS00NWbnqpUj";

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

	public Integer deleteTransaction(long transactionId) {
		if (tDAO.findById(transactionId).isPresent()) {

			try {
				tDAO.deleteById(transactionId);
				return 1;
			} catch (Exception e) {
				// query error
				return 0;
			}
		} else {
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
	 * Returns all products like the string given
	 */
	public List<Product> findAllProductsLike(String productName) {
		return pDAO.findLike(productName);
	};

	/**
	 * Returns products by catId
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

	public List<Product> findProductsBySubcatId(long catId, long subcatId) {
		return pDAO.find(catId, subcatId);
	};

	public List<User> findUserByUserId(long userId) {
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

	/**
	 * Returns all transactions
	 */
	public List<Transaction> findAllTransactions() {

		return tDAO.findAll();
	};

	/**
	 * Get a transaction by id
	 * 
	 * @param transactionId transaction with Id to get
	 * @return Transaction with matching Id if it exists; if not, null is returned
	 */
	public Transaction findTransactionById(long transactionId) {

		// get transaction by id
		Optional<Transaction> optVal = tDAO.findById(transactionId);

		// return value if present; otherwise, null
		if (optVal.isPresent()) {
			return optVal.get();
		} else {
			return null;
		}
	};

	public Coupon getCoupon(long transactionId) {
		Transaction transaction = this.findTransactionById(transactionId);

		if (transaction.getCoupons().size() > 0) {
			return transaction.getCoupons().get(0);
		}

		return null;
	}

	public Long openTransactionsExist(long userId) {
		// get transaction by userId
		Optional<Transaction> retVal = tDAO.findOpenTransactionsByUserId(userId);

		if (retVal.isPresent()) {
			return retVal.get().getTransactionId();
		} else {
			return -1L;
		}
	}

	public List<Product> getCompleteTransactionDetails(Long transactionId) {

		Transaction transaction = this.findTransactionById(transactionId);
		List<Product> productList = new ArrayList<>();
		List<Product> retList = null;
		List<Product> newList = null;
		List<Inventory> invList = null;
		Product product = null;

		// for each inventory item in the open transaction, get its product info and
		// set the products inventory list to the inventory item
		for (Inventory inv : transaction.getInventory()) {

			// create a new product for cart
			product = new Product();

			// look for existing product matching inventory items product id
			retList = pDAO.findByProductId(inv.getProductId());

			// copy selected existing product data to new product for cart
			product.setProductId(retList.get(0).getProductId());
			product.setProductName(retList.get(0).getProductName());
			product.setPhoto(retList.get(0).getPhoto());
			product.setPrice(retList.get(0).getPrice());

			// set inventory data for new product
			invList = new ArrayList<>();
			inv.setQty(1L);
			invList.add(inv);
			product.setInventory(invList);

			// add new cart product with inventory details to cart's product list
			newList = new ArrayList<>();
			newList.add(product);
			productList.addAll(newList);
		}

		// return the shopping carts product list
		return productList;
	}

	public Integer saveTransaction(Transaction transaction) {

		// perform write operation depending on which object variables are set
		// update case where both a key and store id are given
		if (transaction.getTransactionId() != null && transaction.getStoreId() != null) {

			// update transaction if transaction id matches existing record
			if (tDAO.findById(transaction.getTransactionId()).isPresent()) {
				try {
					tDAO.save(transaction);
				} catch (Exception e) {
					// query error
					return -1;
				}

			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (transaction.getTransactionId() != null) {

			// if author to delete doesn't exist, return error status
			if (tDAO.findById(transaction.getTransactionId()).isPresent()) {
				try {
					tDAO.deleteById(transaction.getTransactionId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			try {
				// create the new record
				tDAO.save(transaction);
			} catch (Exception e) {
				// query error
				return -1;
			}
		}

		return 0;
	}

	public Integer updateTransactionCost(Map<String, Object> values) {

		Long userId = ((Number) values.get("userId")).longValue();
		Double tax = (Double) values.get("tax");
		Double total = (Double) values.get("total");

		// get transaction by userId
		Optional<Transaction> retVal = tDAO.findOpenTransactionsByUserId(userId);

		if (retVal.isPresent()) {
			retVal.get().setTax(tax);
			retVal.get().setTotal(total);
			try {
				tDAO.save(retVal.get());
			} catch (Exception e) {
				// query error
				return -1;
			}
			return 0;
		} else {
			return 1;
		}
	}

	public Map<String, String> createPaymentIntent(Transaction t) {

		// set stripe secret key
		Stripe.apiKey = STRIPE_SECRET;

		// total amount of transaction in cents is converted to dollars
		Long totalAmount = (long) (t.getTotal() * 100);

		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setCurrency("usd").setAmount(totalAmount)
				// Verify your integration in this guide by including this parameter
				.putMetadata("integration_check", "accept_a_payment").build();

		try {
			PaymentIntent intent = PaymentIntent.create(params);

			Map<String, String> map = new HashMap();
			map.put("client_secret", intent.getClientSecret());

			// set transaction to complete & record payment intent key
			this.setTransactionStatusComplete(t.getUserId(), intent.getId());

			return map;
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method completes a transaction by setting its status to complete and
	 * recording its Stripe paymentIntent under the paymentId transaction field
	 * 
	 * @param userId           id of user making transaction
	 * @param paymentIntentKey stripe paymentIntent key
	 * @return 0 for success; -1 for query error; 1 if no open transactions for user
	 *         found
	 */
	public Integer setTransactionStatusComplete(Long userId, String paymentIntentKey) {

		// get transaction by userId
		Optional<Transaction> retVal = tDAO.findOpenTransactionsByUserId(userId);

		// set transaction fields and save
		if (retVal.isPresent()) {
			try {
				retVal.get().setStatus("complete");
				retVal.get().setPaymentId(paymentIntentKey);
				tDAO.save(retVal.get());
			} catch (Exception e) {
				// query error
				return -1;
			}
			// success
			return 0;
		} else {
			// open transaction not found for userId
			return 1;
		}
	}
}
