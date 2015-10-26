package com.libjap.springboot.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by coupang on 15. 10. 16..
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {

	List<Customer> findByLastName(String name);
}
