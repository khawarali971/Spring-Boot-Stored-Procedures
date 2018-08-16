package com.test.oracle.controller;

import com.test.oracle.entity.CustomerName;
import com.test.oracle.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	TestService testService;
	@GetMapping("/{msisdn}")
	public ResponseEntity<CustomerName> getCustomerName(@PathVariable String msisdn) {
		try{
			CustomerName getCustomerById = testService.getCustomerById(msisdn);
			if (getCustomerById == null) {
				CustomerName getCustomer = testService.findById(msisdn);
				if (getCustomer == null) {
				    System.out.println("Null in Call");
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				System.out.println("procedure call " + msisdn.length());
				CustomerName createCustomer = testService.create(getCustomer);
				return new ResponseEntity<>(createCustomer, HttpStatus.OK);
			}
			System.out.println("cassandra call " + msisdn.length());
			return new ResponseEntity<>(getCustomerById, HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println("error");
			return null;
		}
	}
	@GetMapping("/insert")
	public ResponseEntity<CustomerName> getCustomerName(){
        String[] msisdnTest = new String[] {"345", "456", "1234","123","234"};
        for (String msisdn: msisdnTest) {
            CustomerName getCustomer = testService.findById(msisdn);
            if (getCustomer == null) {
                System.out.println("no result "  + msisdn.trim());
            }
            else{
                System.out.println("row inserted " + msisdn);
                CustomerName createCustomer = testService.create(getCustomer);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
