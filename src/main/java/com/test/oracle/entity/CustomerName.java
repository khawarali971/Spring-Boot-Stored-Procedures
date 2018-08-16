package com.test.oracle.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table
public class CustomerName {
    @Id
    @PrimaryKey
    private String msisdn;
    private String firstname;
    private String lastname;

    public CustomerName(String msisdn, String firstname, String lastname) {
        this.msisdn = msisdn;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public CustomerName() {
    }

    @Override
    public String toString() {
        return "CustomerName{" +
                "msisdn='" + msisdn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
