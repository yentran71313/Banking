package com.cg.service.customer;


import com.cg.model.Customer;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public List<Customer> findAllByIdNotAndDeletedFalse(Long id) {
        return customerRepository.findAllByIdNotAndDeletedFalse(id);
    }


    @Override
    public List<Customer> findAllByDeletedFalse() {
        return customerRepository.findAllByDeletedFalse();
    }

    @Override
    public List<Customer> findAllByEmail(String email) {
        return customerRepository.findAllByEmail(email);
    }

    @Override
    public List<Customer> findAllByPhone(String phone) {
        return customerRepository.findAllByPhone(phone);
    }


}
