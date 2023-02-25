package com.masai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.BillingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.BillingDetails;
import com.masai.service.BillingsService;

@RestController
public class BillingController {

	private BillingsService billingService;
	
	@PostMapping("/addBill")
	public ResponseEntity<BillingDetails> addBillingHandler(@RequestParam Integer orderId,@RequestParam Integer billingId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, BillingException{
		
		BillingDetails bill2 = billingService.addBill(orderId, billingId, customerId, key);
		
		return new ResponseEntity<BillingDetails>(bill2,HttpStatus.OK);
	}
	
	@GetMapping("/viewBill/{BillingId}")
	public ResponseEntity<BillingDetails> viewPaymentDetailsHandler(@PathVariable Integer BillingId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, BillingException {
		
		BillingDetails bill2 = billingService.viewBill(BillingId, customerId, key);
		
		return new ResponseEntity<BillingDetails>(bill2,HttpStatus.OK);
	}
}
