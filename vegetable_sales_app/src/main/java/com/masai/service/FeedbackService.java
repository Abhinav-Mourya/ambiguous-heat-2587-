package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.VegetableException;
import com.masai.model.Customer;
import com.masai.model.Feedback;

public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback, String key, Integer orderId,Integer customerId) throws LoginException, FeedbackException, CustomerException,OrderException;
	
	public List<Feedback> viewAllFeedBack(Integer customerId, String key)throws LoginException,CustomerException, FeedbackException;
	
	public Feedback viewFeedBack(Integer customerId,String key) throws LoginException, FeedbackException,CustomerException;
}
