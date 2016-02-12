package com.libjap.springboot.web;

import com.libjap.springboot.domain.Customer;
import com.libjap.springboot.domain.CustomerRepository;
import com.libjap.springboot.domain.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by coupang on 15. 10. 13..
 */
@RestController
public class HelloworldController {
	@Autowired
	CustomerService customerService;

	@RequestMapping("/hello")
	public String helloworld(){
		return "helloworld!!";
	}


	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseBody
	public String insert(
		@RequestParam String firstName,
		@RequestParam String lastName){
		Customer customer= new Customer(firstName,lastName);
		boolean result = customerService.insert(customer);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("firstName : ").append(firstName)
			.append("lastName: ").append(lastName)
			.append("result: ").append(result);

		return stringBuilder.toString();
	}

	@RequestMapping(value = "/insert2", method = RequestMethod.GET)
	@ResponseBody
	public String insert(){
		Customer customer= new Customer("1","2");
		boolean result = customerService.insert(customer);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("firstName : ").append("1")
			.append("lastName: ").append("2")
			.append("result: ").append(result);

		return stringBuilder.toString();
	}

}
