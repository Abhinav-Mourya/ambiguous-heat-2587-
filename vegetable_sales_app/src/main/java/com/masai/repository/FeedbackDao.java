package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.model.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer> {

	public List<Feedback> findByCustomer(Customer customer);
}
