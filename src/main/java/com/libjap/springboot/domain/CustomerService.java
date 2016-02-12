package com.libjap.springboot.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;


	public boolean insert(Customer customer){
		Customer result = customerRepository.save(customer);
		return result != null;
	}
}
