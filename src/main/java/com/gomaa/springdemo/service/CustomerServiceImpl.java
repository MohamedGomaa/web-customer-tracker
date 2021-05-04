package com.gomaa.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomaa.springdemo.dao.CustomerDAO;
import com.gomaa.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	// need to inject our DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		return customerDAO.getCustomer(theId);
	}


	@Override
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);
		
	}
	
	
}
