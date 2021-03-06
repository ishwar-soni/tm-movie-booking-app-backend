package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDAO customerDAO;

    @Override
    public Customer acceptCustomerDetails(Customer customer) {
        return  customerDAO.save(customer);

    }

    @Override
    public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException {
        Customer customer = customerDAO.findById(id).orElseThrow(
                ()->  new CustomerDetailsNotFoundException("Customer not found for id" + id));
        return customer;
    }

    @Override
    public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException {
        Customer myCustomer = customerDAO.findByUsername(username);
        if(myCustomer == null)
            throw new CustomerDetailsNotFoundException("Details not found for : " + username);
        else
            return myCustomer;
    }

    @Override
    public boolean deleteCustomer(int id) throws CustomerDetailsNotFoundException {
        Customer customer = getCustomerDetails(id);
        customerDAO.delete(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerDAO.findAll();
    }
}
