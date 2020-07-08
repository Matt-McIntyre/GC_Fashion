package com.smoothstack.gcfashion.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

import com.smoothstack.gcfashion.entity.Transaction;
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
	public void testValidUpdateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 0);
	}
	
	@Test
	public void testInvalidSaveUser() {
		User user = new User();
		user.setUserId(10L);
		
		int retVal = storeService.saveUser(user);
		
		assertEquals(retVal, 0);
	}
	
	@Test
	public void testValidSaveUser() {
		User user = new User();
		user.setUserId(10L);
		
		int retVal = storeService.saveUser(user);
		
		assertEquals(retVal, 0);
	}
	
	@Test
	public void testSaveUserException() {
		User user = new User();
		user.setUserId(10L);
		
		when(uDAO.save(user)).thenThrow(RuntimeException.class);
		
		int retVal = storeService.saveUser(user);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testOpenTransactionsExistBadId() {
		
		when(tDAO.findOpenTransactionsByUserId(10L)).thenReturn(Optional.empty());
		
		long retVal = storeService.openTransactionsExist(10L); 
		
		assertEquals(retVal, -1L);

	}
	
	@Test
	public void testOpenTransactionsExistValidId() {
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(30L);
		
		when(tDAO.findOpenTransactionsByUserId(10L)).thenReturn(Optional.of(transaction));
		
		long retVal = storeService.openTransactionsExist(10L);
		
		assertEquals(retVal, 30L);

	}
}
