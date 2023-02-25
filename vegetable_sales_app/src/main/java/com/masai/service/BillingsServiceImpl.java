package com.masai.service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BillingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.BillingDetails;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.repository.BillingDetailsDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;

import com.masai.repository.OrdersDao;



@Service
public class BillingsServiceImpl implements BillingsService {
	
	@Autowired
	private BillingDetailsDao billingDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;
	
	@Autowired
	private OrdersDao orderDao;
	
	@Override
	public BillingDetails addBill(Integer orderId, Integer billingId, Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, BillingException {
		Customer customer = checkLogin(key, customerId);
		
		Optional<Orders> opt = orderDao.findById(orderId);
		
        if(opt.isEmpty()) throw new OrderException("Invalid OrderId");
		
		Orders order = opt.get();
		
		BillingDetails bill = new BillingDetails();
		bill.setTransactionMode(key);
		bill.setTransactionDate(LocalDateTime.now());
		bill.setTransactionStatus(true);
		bill.setBillingAddress(customer.getAddress());
		bill.setOrder(order);
		
		BillingDetails bill2 = billingDao.save(bill);
		
		order.setBillingDetail(bill2);
		orderDao.save(order);
	
		return bill2;
	}
	
	@Override
	public BillingDetails viewBill(Integer billingId, Integer customerId, String key)
			throws LoginException, CustomerException, BillingException {
		Customer customer = checkLogin(key, customerId);
		
		Optional<BillingDetails> bill = billingDao.findById(billingId);
		if(bill.isEmpty()) throw new BillingException("Invalid billingId");
		
		return bill.get();
	}
	
	
	public Customer checkLogin(String key, Integer customerId) throws LoginException, CustomerException {
		Optional<Customer> opt = customerDao.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("No customer found with id:- " + customerId);

		Customer customer = opt.get();
		CurrentCustomerUserSession cus = customerSessionDao.findByUuid(key);

		if (cus == null)
			throw new LoginException("Invalid Current Key");
		if (cus.getUserId() != customer.getCustomerId())
			throw new LoginException("Please Login first.....");

		return customer;

	}


	


	

}
