package com.test.oracle.service;

import com.test.oracle.entity.CustomerName;

import java.util.Optional;

public interface TestService {

    CustomerName findById(String msisdn);

    CustomerName getCustomerById(String msisdn);

    CustomerName create(CustomerName getCustomer);
}
