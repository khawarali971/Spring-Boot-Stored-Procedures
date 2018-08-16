package com.test.oracle.implementation;

import com.google.gson.JsonObject;
import com.test.oracle.Repo.TestRepo;
import com.test.oracle.entity.CustomerName;
import com.test.oracle.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

@Service
public class TestServiceImplementation implements TestService {

    @Autowired
    private TestRepo testRepo;
    @Override
    public CustomerName findById(String msisdn) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.2.219:1521/orcl", "hr","hr");
            CallableStatement callableStatement = null;
            String getDBUSERByUserIdSql = "{call getname(?,?,?)}";
            callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
            callableStatement.setString(1, msisdn);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            String firstname = callableStatement.getString(2);
            String lastname = callableStatement.getString(3);
            callableStatement.close();
            conn.close();
            CustomerName customerName = new CustomerName();
            customerName.setMsisdn(msisdn);
            customerName.setFirstname(firstname);
            customerName.setLastname(lastname);
            System.out.println(customerName);
            return customerName;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public CustomerName getCustomerById(String msisdn) {
        System.out.println("in cassandra call");
        Optional<CustomerName> getCustomer = testRepo.findById(msisdn);
        if(!getCustomer.isPresent()){
            return null;
        }
        return getCustomer.get();
    }

    @Override
    public CustomerName create(CustomerName getCustomer) {
        CustomerName create = testRepo.save(getCustomer);
        return create;

    }

}
