package com.authmo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.authmo.entities.Customer;
import com.authmo.service.CustomerService;

/*****
 * page entry
 * @author han
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
	/****
	 * by default locating to model view "home".
	 * @return
	 */
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/")
	public String getHome() {
		return "home";
	} 
	 
}
