package com.gomaa.springdemo.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gomaa.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by first name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName",
												Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result
		return customers;
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(theCustomer);
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		//retrun the customer
		return theCustomer;
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the object with the primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

}
