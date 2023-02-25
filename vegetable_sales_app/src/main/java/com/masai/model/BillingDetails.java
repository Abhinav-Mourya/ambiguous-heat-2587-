package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer billingId;
	private String transactionMode;
	private LocalDateTime transactionDate;
	
	private Boolean transactionStatus;
	
	@Embedded
	private Address billingAddress;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="orderId")
	private Orders order;
	
	
	
}
