package com.authmo.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.authmo.entities.Customer;
 

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CustomerDao extends AbstractJpaDAO<Customer> {
	public CustomerDao(){
		super(Customer.class);
	}

	public List<Customer> getByName(String name) {
		String queryStr = "select distinct c from Customer c where name like :name ";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("name", name+"%");
		return (List<Customer>)query.getResultList();
	}
}
