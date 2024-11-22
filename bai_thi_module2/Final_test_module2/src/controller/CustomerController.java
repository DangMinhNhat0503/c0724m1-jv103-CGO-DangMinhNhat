package controller;

import entity.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerController {
    private static CustomerRepository customerRepository = new CustomerRepository();

    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.getAll();
        return customers;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public boolean isExistsCustomer(String phone) {
        if( customerRepository.findByPhone(phone)) {
            return true;
        }
        return false;
    }

    public void deleteByPhone(String phone) {
        customerRepository.remove(phone);
    }

    public void exportDataCSV(List<Customer> customers, String filename) {
        customerRepository.exportDataCSV(customers,filename);
    }

    public List<Customer> searchByName(String name) {
        return customerRepository.findByName(name);
    }
}
