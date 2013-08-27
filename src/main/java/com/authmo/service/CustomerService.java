package com.authmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authmo.entities.Customer;
import com.authmo.repository.CustomerDao;

@Service("customerService")
public class CustomerService {
	@Autowired
	private CustomerDao customerDao; 
	
	public Customer getById(long id){
		return (Customer)customerDao.getById(id);
		 
	}
	public List<Customer> getAll(){
		return (List<Customer>)customerDao.getAll();
	}
	
	public Customer save(Customer customer){
		if(customer!=null && customer.getId()>0){
			customerDao.update(customer);
		}else{
			customerDao.create(customer);
		}
		return customer;
	}
	public Customer getRandom() {
		Customer customer = new Customer();
		return customer;
	}
	public void delete(long id) {	
		customerDao.deleteById(id);				
	}
	public List<Customer> getByName(String name) {
		return customerDao.getByName(name);
	}
}
