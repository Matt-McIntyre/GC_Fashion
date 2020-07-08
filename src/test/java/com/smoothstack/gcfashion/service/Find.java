package com.smoothstack.gcfashion.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.gcfashion.dao.CategoryDAO;
import com.smoothstack.gcfashion.dao.CouponDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.StoreDAO;
import com.smoothstack.gcfashion.dao.SubcategoryDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.UserDAO;
import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Find {
	
	@Mock
	CategoryDAO cDAO;

	@Mock
	CouponDAO cpDAO;

	@Mock
	SubcategoryDAO scDAO;

	@Mock
	StoreDAO sDAO;

	@Mock
	ProductDAO pDAO;

	@Mock
	TransactionDAO tDAO;

	@Mock
	UserDAO uDAO;

	@InjectMocks
	StoreService storeService;

	@Test
	public void testFindAllTransactionsEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		when(tDAO.findAll()).thenReturn(transactions);

		assertEquals(storeService.findAllTransactions().size(), 0);

	}

	@Test
	public void testFindAllTransactionsNotEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();

		transactions.add(t1);
		transactions.add(t2);

		when(tDAO.findAll()).thenReturn(transactions);

		assertEquals(storeService.findAllTransactions().size(), 2);

	}
	
	@Test
	public void testFindAllCategories() {
		List<Category> cList = new ArrayList<>();
		Category category1 = new Category();
		category1.setCatId(Long.valueOf(1));
		category1.setCatName("Cat 1");

		Category category2 = new Category();
		category2.setCatId(Long.valueOf(2));
		category2.setCatName("Cat 2");

		cList.add(category1);
		cList.add(category2);

		when(cDAO.findAll()).thenReturn(cList);
		assertEquals(storeService.findAllCategories().size(), 2);
	}

	@Test
	public void testFindAllSubcategories() {
		List<Subcategory> scList = new ArrayList<>();
		Subcategory subcategory1 = new Subcategory();
		subcategory1.setSubcatId(Long.valueOf(1));
		subcategory1.setSubcatName("subcat 1");

		Subcategory subcategory2 = new Subcategory();
		subcategory2.setSubcatId(Long.valueOf(2));
		subcategory2.setSubcatName("subcat 2");

		scList.add(subcategory1);
		scList.add(subcategory2);

		when(scDAO.findAll()).thenReturn(scList);
		assertEquals(storeService.findAllSubcategories().size(), 2);
	}

	@Test
	public void testFindAllStores() {
		List<Store> sList = new ArrayList<>();
		Store store1 = new Store();
		store1.setStoreId(Long.valueOf(1));
		store1.setLocation("Location 1");

		Store store2 = new Store();
		store2.setStoreId(Long.valueOf(2));
		store2.setLocation("Location 2");

		sList.add(store1);
		sList.add(store2);

		when(sDAO.findAll()).thenReturn(sList);
		assertEquals(storeService.findAllStores().size(), 2);
	}

	@Test
	public void testFindAllCoupons() {
		List<Coupon> cList = new ArrayList<>();
		Coupon coupon1 = new Coupon();
		coupon1.setCouponId(Long.valueOf(1));
		coupon1.setProductId(Long.valueOf(1));
		coupon1.setAppliesTo("total");
		coupon1.setDiscount(.12);

		Coupon coupon2 = new Coupon();
		coupon2.setCouponId(Long.valueOf(2));
		coupon2.setProductId(Long.valueOf(2));
		coupon2.setAppliesTo("total");
		coupon1.setDiscount(.12);

		cList.add(coupon1);
		cList.add(coupon2);
		when(cpDAO.findAll()).thenReturn(cList);
		assertEquals(storeService.findAllCoupons().size(), 2);
	}

	@Test
	public void testFindAllProducts() {
		List<Product> pList = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(Long.valueOf(1));
		product1.setProductName("Product 1");

		Product product2 = new Product();
		product2.setProductId(Long.valueOf(2));
		product2.setProductName("Product 2");

		pList.add(product1);
		pList.add(product2);

		when(pDAO.findAll()).thenReturn(pList);
		assertEquals(storeService.findAllProducts().size(), 2);
	}

	@Test
	public void testFindTransactionsByUserIdNotFound() {
		List<Transaction> transactions = new ArrayList<>();

		when(tDAO.find(Long.valueOf(1))).thenReturn(transactions);

		assertEquals(storeService.findTransactionsByUserId(1L).size(), 0);
	}

	@Test
	public void testFindTransactionsByUserIdFound() {
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction1 = new Transaction();
		transaction1.setTransactionId(1L);
		transactions.add(transaction1);

		when(tDAO.find(Long.valueOf(1))).thenReturn(transactions);

		assertEquals(storeService.findTransactionsByUserId(1L).size(), 1);
	}

	@Test
	public void testFindProductsByProductIdNotFound() {
		
		List<Product> products = new ArrayList<>();

		when(pDAO.findByProductId(Long.valueOf(1))).thenReturn(products);

		assertEquals(storeService.findProductsByProductId(1L).size(), 0);
	}
	
	@Test
	public void testFindProductsByProductIdFound() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(1L);
		products.add(product1);

		when(pDAO.findByProductId(Long.valueOf(1))).thenReturn(products);

		assertEquals(storeService.findProductsByProductId(1L).size(), 1);
	}
	
	@Test
	public void testFindProductsByCatIdNotFound() {
		List<Product> products = new ArrayList<>();

		when(pDAO.findByCatId(Long.valueOf(1))).thenReturn(products);

		assertEquals(storeService.findProductsByCatId(1L).size(), 0);
	}
	
	@Test
	public void testFindProductsByCatIdFound() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(1L);
		products.add(product1);

		when(pDAO.findByCatId(Long.valueOf(1))).thenReturn(products);

		assertEquals(storeService.findProductsByCatId(1L).size(), 1);
	}
	
	@Test
	public void testFindProductsBySubcatIdNotFound() {
		List<Product> products = new ArrayList<>();

		when(pDAO.find(1L, 2L)).thenReturn(products);

		assertEquals(storeService.findProductsBySubcatId(1L, 2L).size(), 0);
	}
	
	@Test
	public void testFindProductsBySubcatIdFound() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(1L);
		products.add(product1);

		when(pDAO.find(1L, 2L)).thenReturn(products);

		assertEquals(storeService.findProductsBySubcatId(1L, 2L).size(), 1);
	}
	
	@Test
	public void testFindUserByUserIdNotFound() {
		List<User> users = new ArrayList<>();

		when(uDAO.findByUserId(Long.valueOf(1))).thenReturn(users);

		assertEquals(storeService.findUserByUserId(1L).size(), 0);
	}
	
	@Test
	public void testFindUserByUserIdFound() {
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setUserId(Long.valueOf(1));
		user1.setFullName("Name 1");
		users.add(user1);

		when(uDAO.findByUserId(Long.valueOf(1))).thenReturn(users);

		assertEquals(storeService.findUserByUserId(1L).size(), 1);
	}
	
	@Test
	public void testFindAllProductsLikeNoMatch() {
		List<Product> products = new ArrayList<>();
		
		when(pDAO.findLike("noMatch")).thenReturn(products);
		
		assertEquals(storeService.findAllProductsLike("noMatch"), products);
	}
	
	@Test
	public void testFindAllProductsLikeMatch() {
		List<Product> products = new ArrayList<>();
		Product p = new Product();
		products.add(p);
		
		when(pDAO.findLike("match")).thenReturn(products);
		
		assertEquals(storeService.findAllProductsLike("match"), products);
	}
}