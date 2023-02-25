package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.Feedback;
import com.masai.service.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService fService;
	
	@PostMapping("/addFeedBack")
	public ResponseEntity<Feedback> addFeedBackHandler(@Valid @RequestBody Feedback feedback,@RequestParam Integer orderId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException,CustomerException,OrderException,FeedbackException{
		
		Feedback feedback1 = fService.addFeedBack(feedback, key, orderId, customerId);
		
		return new ResponseEntity<Feedback>(feedback1,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAllfeedback/{customerId}")
	public ResponseEntity<List<Feedback>> viewAllFeedBackHandler(@PathVariable Integer customerId,@RequestParam String key)
			throws LoginException,CustomerException,OrderException,FeedbackException{
		
		List<Feedback> feedback = fService.viewAllFeedBack(customerId, key);
		
		return new ResponseEntity<List<Feedback>>(feedback,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewfeedbackById/{customerId}")
	public ResponseEntity<Feedback> viewFeedBackByIdHandler(@PathVariable Integer customerId,@RequestParam String key)
			throws LoginException,CustomerException,OrderException,FeedbackException{
		
		Feedback feedback = fService.viewFeedBack(customerId, key);
		
		return new ResponseEntity<Feedback>(feedback,HttpStatus.ACCEPTED);
	}
}
