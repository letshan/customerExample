package com.authmo.web.controllers;
   
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authmo.entities.Customer;
import com.authmo.service.CustomerService;

 

@Controller
@RequestMapping("api")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	 
	@RequestMapping("customer/random")	
	@ResponseBody
	public Customer randomcustomer() {
		return customerService.getRandom();
	}
	
	@RequestMapping("customer/{id}")
	@ResponseBody
	public Customer getById(@PathVariable Integer id) {
		Customer cm = customerService.getById(id);
		return cm;
	}
	 
	
	/**
	 * Saves new customer. Spring automatically binds the name
	 * and age parameters in the request to the customer argument
	 * @param customer
	 * @return String indicating success or failure of save
	 */
	@RequestMapping(value="customer", method=RequestMethod.POST) 
	@ResponseBody
	public Customer savecustomer(@RequestBody Customer customer) {
		customerService.save(customer);
		return  customer ;
		 
	}
	/****
	 * delete a customer
	 * @param id
	 * @return
	 */
	@RequestMapping(value="customer/delete", params="id") 
	@ResponseBody
	public boolean delete(@RequestParam("id") Integer id) {
		if(id!=null) {
			try {
				customerService.delete(id);
				return true;
			}catch(Throwable ex) {
				ex.printStackTrace();
			}
		} 
		return false;		
	}
}
