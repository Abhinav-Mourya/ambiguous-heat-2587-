package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.CurrentCustomerUserSession;
import com.masai.model.Customer;
import com.masai.model.Feedback;
import com.masai.model.Orders;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerSessionDao;
import com.masai.repository.FeedbackDao;
import com.masai.repository.OrdersDao;
import com.masai.repository.VegetableDTODao;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerSessionDao customerSessionDao;

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private OrdersDao orderDao;
	
	@Autowired
	private VegetableDTODao vegetableDao;

	@Override
	public Feedback addFeedBack(Feedback feedback, String key, Integer orderId,Integer customerId)
			throws FeedbackException, CustomerException, OrderException, LoginException {

		Customer customer = checkLogin(key, customerId);
		Optional<Orders> opt = orderDao.findById(orderId);
		if(opt.isEmpty())
			throw new OrderException("Invalid order Id:");
		Orders order = opt.get();
		
		feedback.setCustomer(customer);
		feedback.setOrders(order);
		
			
		return null;
	}

	@Override
	public List<Feedback> viewAllFeedBack(Integer customerId, String key)
			throws FeedbackException, LoginException, CustomerException {
		Customer customer = checkLogin(key, customerId);
		
		List<Feedback> list = feedbackDao.findByCustomer(customer);
		
		if(list.size()==0) throw new FeedbackException("please add feedback first");
		
		return list;
	}

	@Override
	public Feedback viewFeedBack(Integer customerId, String key) throws LoginException, FeedbackException, CustomerException {
		Customer customer = checkLogin(key, customerId);
		
		Optional<Feedback> opt = feedbackDao.findById(customerId);
		
		if(opt.isEmpty()) throw new FeedbackException("Customer not fount with this Id");
		
		Feedback feed = opt.get();
		
		if(feed.getCustomer().getCustomerId()!=customer.getCustomerId()) {
			throw new FeedbackException("Invalid feedBack Id for customer : "+customer.getCustomerId());
		}
		
		return feed;
		
		
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
