package com.smoothstack.gcfashion.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.User;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

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
	public void testGetAllEmptyLists() {
		List<Category> cList = new ArrayList<>();
		when(cDAO.findAll()).thenReturn(cList);
		assertEquals(storeService.findAllCategories().size(), 0);

		List<Coupon> cpList = new ArrayList<>();
		when(cpDAO.findAll()).thenReturn(cpList);
		assertEquals(storeService.findAllCoupons().size(), 0);

		List<Subcategory> scList = new ArrayList<>();
		when(scDAO.findAll()).thenReturn(scList);
		assertEquals(storeService.findAllSubcategories().size(), 0);

		List<Store> sList = new ArrayList<>();
		when(sDAO.findAll()).thenReturn(sList);
		assertEquals(storeService.findAllStores().size(), 0);

		List<Product> pList = new ArrayList<>();
		when(pDAO.findAll()).thenReturn(pList);
		assertEquals(storeService.findAllProducts().size(), 0);

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
	public void testInvalidFindTransactionsByUserId() {
		when(tDAO.find(Long.valueOf(1))).thenReturn(Optional.empty());
		Optional<Transaction> optResult = storeService.findTransactionsByUserId(1L);

		assertEquals(optResult.isPresent(), false);
	}
	
	@Test
	public void testValidFindTransactionsByUserId() {
		Transaction transaction1 = new Transaction();
		transaction1.setTransactionId(Long.valueOf(1));
		
		when(tDAO.find(Long.valueOf(1))).thenReturn(Optional.of(transaction1));
		Optional<Transaction> optResult = storeService.findTransactionsByUserId(Long.valueOf(1));
		assertEquals(optResult.isPresent(), true);
	}
	
	@Test
	public void testInvalidFindProductsByProductId() {
		when(pDAO.findByProductId(Long.valueOf(1))).thenReturn(Optional.empty());
		Optional<Product> optResult = storeService.findProductsByProductId(1L);

		assertEquals(optResult.isPresent(), false);
	}
	
	@Test
	public void testValidFindProductsByProductId() {
		Product product1 = new Product();
		product1.setProductId(Long.valueOf(1));
		
		when(pDAO.findByProductId(Long.valueOf(1))).thenReturn(Optional.of(product1));
		Optional<Product> optResult = storeService.findProductsByProductId(1L);
		assertEquals(optResult.isPresent(), true);
	}
	
	@Test
	public void testInvalidFindProductsByCatId() {
		when(pDAO.findByCatId(Long.valueOf(1))).thenReturn(Optional.empty());
		Optional<Product> optResult = storeService.findProductsByCatId(1L);

		assertEquals(optResult.isPresent(), false);
	}
	
	@Test
	public void testValidFindProductsByCatId() {
		Product product1 = new Product();
		product1.setProductId(Long.valueOf(1));
		product1.setCatId(Long.valueOf(1));
		
		when(pDAO.findByCatId(Long.valueOf(1))).thenReturn(Optional.of(product1));
		Optional<Product> optResult = storeService.findProductsByCatId(1L);
		assertEquals(optResult.isPresent(), true);
	}
	
	@Test
	public void testInvalidFindUserByUserId() {
		when(uDAO.findByUserId(Long.valueOf(1))).thenReturn(Optional.empty());
		Optional<User> optResult = storeService.findUserByUserId(1L);

		assertEquals(optResult.isPresent(), false);
	}
	
	@Test
	public void testValidFindUserByUserId() {
		User user1 = new User();
		user1.setUserId(Long.valueOf(1));
		user1.setFullName("Name 1");
		
		when(uDAO.findByUserId(Long.valueOf(1))).thenReturn(Optional.of(user1));
		Optional<User> optResult = storeService.findUserByUserId(1L);
		assertEquals(optResult.isPresent(), true);
	}
	
	@Test
	public void testTransactionDeleteValid() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);

		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int returnValue = storeService.deleteTransaction(1L);
		assertEquals(returnValue, 1);
	}

	@Test
	public void testTransactionDeleteInvalid() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);

		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
		
		int returnValue = storeService.deleteTransaction(1L);
		assertEquals(returnValue, 0);
	}
	
	@Test
	public void testInvalidUpdateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testValidUpdateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 1);
	}
	
	@Test
	public void testInvalidUpdateUser() {
		User user = new User();
		user.setUserId(10L);
		
		int retVal = storeService.saveUser(user);
		
		assertEquals(retVal, 0);
	}
	
	@Test
	public void testValidUpdateUser() {
		User user = new User();
		user.setUserId(10L);
		
		int retVal = storeService.saveUser(user);
		
		assertEquals(retVal, 0);
	}
	
	@Test
	public void testInvalidCreateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.save(transaction)).thenThrow(IllegalArgumentException.class);
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testValidCreateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		int retVal = storeService.saveTransaction(transaction);
		assertEquals(retVal, 1);
	}
}
