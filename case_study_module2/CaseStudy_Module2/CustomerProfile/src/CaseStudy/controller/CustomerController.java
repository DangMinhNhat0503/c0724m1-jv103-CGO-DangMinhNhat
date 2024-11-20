package CaseStudy.controller;

import CaseStudy.entity.Customer;
import CaseStudy.entity.History;
import CaseStudy.service.*;

import java.util.List;

public class CustomerController {
    private static ICustomer customerService = new CustomerService();


    public List<Customer> getAll() {
        List<Customer> customers = customerService.getAll();
        return customers;
    }


    public void save(Customer guest) {
        customerService.save(guest);
    }

    public void exportDataCSV(List<Customer> customers, String filename) {
        customerService.exportDataCSV(customers,filename);
    }
}
