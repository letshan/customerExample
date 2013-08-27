package com.authmo.customer.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.authmo.entities.Customer;
import com.authmo.service.CustomerService;
 

/****
 * this test include
 * @author han
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/spring-context.xml")
public class CustomerServiceTest {
	@Autowired
	private CustomerService service;
	@Test
	public void testCustomerSearch() {
		List<Customer> clCustomers = service.getByName("what");
		Assert.assertTrue(clCustomers.size()>0);
	}
	@Test
	public void testCustomerSave() {
		Customer cm = new Customer(); 
		cm.setAge(112);
		cm.setAddress("604 Great South Rd, Ellerslie, Auckland 1051");
		cm.setFax("xxxxxx");
		cm.setName("what is this");
		cm.setLastUpdate(new Date());
		Customer cm2 = service.save(cm);
		Assert.assertNotNull(cm2);
		Assert.assertEquals("what is this", cm2.getName());
		Assert.assertTrue("not saved to db: id not created",  cm2.getId()>0);
		Customer cm3= service.getById(cm2.getId());
		Assert.assertNotNull(cm3);
		Assert.assertEquals("what is this", cm3.getName());		 
		
	}
	
	@Test
	public void testCustomerDelete() {
		Customer cm = new Customer(); 
		cm.setAddress("1111111");
		cm.setFax("xxxxxx");
		cm.setName("what is that");
		cm.setLastUpdate(new Date());
		Customer cm2 = service.save(cm);
		Assert.assertNotNull(cm2);
		Assert.assertEquals("what is that", cm2.getName());
		Assert.assertTrue("not saved to db: id not created", cm2.getId()>0);
		service.delete(cm2.getId());
		cm = service.getById(cm2.getId());
		Assert.assertNull("didn't delete obj",cm);
	}
}
