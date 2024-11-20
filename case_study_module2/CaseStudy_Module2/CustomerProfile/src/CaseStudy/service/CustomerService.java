package CaseStudy.service;

import CaseStudy.entity.Customer;
import CaseStudy.entity.History;
import CaseStudy.repository.CustomerRepository;

import java.util.List;

public class CustomerService implements ICustomer{
    private static CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.getAll();
        return customers;
    }

    @Override
    public Customer getHistoryById(int T) {
        return null;
    }


    @Override
    public void save(Customer guest) {
        customerRepository.save(guest);
    }

    @Override
    public void exportDataCSV(List<Customer> customers, String filename) {
        customerRepository.exportDataCSV(customers,filename);
    }
}
