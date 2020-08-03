package com.smoothstack.gcfashion.service;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.UserDAO;
import com.smoothstack.gcfashion.entity.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTest {

	@Mock
	TransactionDAO tDAO;
	
	@Mock
	UserDAO uDAO;
	
	@Mock
	ProductDAO pDAO;
	
	@InjectMocks
	StoreService storeService;
	
	@Test
	public void testCreatePaymentIntentNull() {
		Transaction t = new Transaction();

		assertNull(storeService.createPaymentIntent(t));
	}
}
