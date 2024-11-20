package CaseStudy.repository;

import CaseStudy.entity.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String LINK = "src/CaseStudy/data/CustomerData.csv";
    private static List<Customer> customers = new ArrayList<>();

    public List<Customer> getAll() {
        File file = new File(LINK);
        Customer customer;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                customer = new Customer(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
                //int id, String name, int phone, String dateOfBirth,String username, String password, String email
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");

        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return customers;
    }

    public void save(Customer guest) {
        File file = new File(LINK);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(guest.getId() + "," + guest.getName() + "," + guest.getPhone() + "," + guest.getDateOfBirth() + "," + guest.getUsername() + "," + guest.getPassword() + "," + guest.getEmail());
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi dữ liệu");
        }
    }

    public void exportDataCSV(List<Customer> customers, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer customer : customers) {
                bufferedWriter.write(customer.getId() + "," + customer.getName() + "," + customer.getPhone() + "," + customer.getDateOfBirth() + "," + customer.getUsername() + "," + customer.getPassword() + "," + customer.getEmail());
                bufferedWriter.newLine();
            }
//            for (int i = 0; i < customers.size(); i++) {
//                bufferedWriter.write(customers.get(i).getId() + "," + customers.get(i).getName() + "," + customers.get(i).getPhone() + "," + customers.get(i).getDateOfBirth() + "," + customers.get(i).getUsername() + "," + customers.get(i).getPassword() + "," + customers.get(i).getEmail());
//                bufferedWriter.newLine();
//            }
            bufferedWriter.close();
            System.out.println("Đã xuất file thành công, mời bạn kiểm tra");
        } catch (IOException e) {
            System.out.println("Lỗi ghi dữ liệu");
        }
    }
}
