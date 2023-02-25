package com.masai.service;

import com.masai.exceptions.BillingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.BillingDetails;

public interface BillingsService {

	public BillingDetails addBill(Integer orderId,Integer billingId,Integer customerId,String key)throws LoginException,CustomerException,OrderException,BillingException;
	
	
	public BillingDetails viewBill(Integer billingId,Integer customerId,String key)throws LoginException,CustomerException,BillingException;
}
