package com.smoothstack.gcfashion.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.entity.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTransactionTest {

	@Mock
	TransactionDAO tDAO;

	@InjectMocks
	StoreService storeService;

	@Test
	public void testUpdateTransactionCostNull() {
		Map<String, Object> values = null;

		int retVal = storeService.updateTransactionCost(values);

		assertEquals(retVal, -2);
	}

	@Test
	public void testUpdateTransactionCostMissingValues() {
		Map<String, Object> values = new HashMap<>();

		values.put("userId", 1);
		values.put("tax", 1.00);

		int retVal = storeService.updateTransactionCost(values);

		assertEquals(retVal, -2);
	}

	@Test
	public void testUpdateTransactionCostInvalid() {
		Map<String, Object> values = new HashMap<>();

		values.put("userId", 1);
		values.put("total", 10.00);
		values.put("tax", 1.00);

		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.empty());

		int retVal = storeService.updateTransactionCost(values);

		assertEquals(retVal, 1);
	}

	@Test
	public void testUpdateTransactionCostValid() {
		Map<String, Object> values = new HashMap<>();

		values.put("userId", 1);
		values.put("total", 10.00);
		values.put("tax", 1.00);

		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setTax(1.00);
		transaction.setTotal(10.00);

		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.of(transaction));

		int retVal = storeService.updateTransactionCost(values);

		assertEquals(retVal, 0);
	}

	@Test
	public void testUpdateTransactionCostException() {
		Map<String, Object> values = new HashMap<>();

		values.put("userId", 1);
		values.put("total", 10.00);
		values.put("tax", 1.00);

		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setTax(1.00);
		transaction.setTotal(10.00);

		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.of(transaction));
		when(tDAO.save(transaction)).thenThrow(RuntimeException.class);

		int retVal = storeService.updateTransactionCost(values);

		assertEquals(retVal, -1);
	}
	
	@Test
	public void testSetTransactionStatusCompleteNotFound() {
		
		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.empty());
		
		int retVal = storeService.setTransactionStatusComplete(1L, "paymentIntentKey");

		assertEquals(retVal, 1);
	}
	
	@Test
	public void testSetTransactionStatusCompleteSuccess() {
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		transaction.setStatus("complete");
		transaction.setPaymentId("paymentIntentKey");
		
		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.setTransactionStatusComplete(1L, "paymentIntentKey");

		assertEquals(retVal, 0);
	}
	
	@Test
	public void testSetTransactionStatusCompleteException() {
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		transaction.setStatus("complete");
		transaction.setPaymentId("paymentIntentKey");
		
		when(tDAO.findOpenTransactionsByUserId(1L)).thenReturn(Optional.of(transaction));
		when(tDAO.save(transaction)).thenThrow(RuntimeException.class);
		
		int retVal = storeService.setTransactionStatusComplete(1L, "paymentIntentKey");

		assertEquals(retVal, -1);
	}
}