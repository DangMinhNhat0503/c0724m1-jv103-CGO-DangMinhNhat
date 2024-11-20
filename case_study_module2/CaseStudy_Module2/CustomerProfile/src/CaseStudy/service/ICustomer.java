package CaseStudy.service;

import CaseStudy.entity.Customer;

import java.util.List;

public interface ICustomer extends IService<Customer> {

    void save(Customer guest);

    void exportDataCSV(List<Customer> customers, String filename);
}
