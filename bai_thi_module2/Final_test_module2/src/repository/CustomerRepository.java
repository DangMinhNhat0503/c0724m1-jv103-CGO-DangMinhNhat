package repository;

import entity.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String LINK = "src/data/dataCustomer.csv";
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
                customer = new Customer(temp[0], Integer.parseInt(temp[1]), temp[2], temp[3], temp[4], temp[5], temp[6]);
                //int phone, int group, String name, String gender, String address, String dateOfBirth, String email
                customers.add(customer);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");

        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return customers;
    }

    public void save(Customer customer) {
        File file = new File(LINK);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(customer.getPhone() + "," + customer.getGroup() + "," + customer.getName() + "," + customer.getGender() + "," + customer.getAddress() + "," + customer.getDateOfBirth() + "," + customer.getEmail());
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi dữ liệu");
        }
    }

    public boolean findByPhone(String phone) {
        List<Customer> customers = getAll();
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void remove(String phone) {
        List<Customer> customers = getAll();
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                customers.remove(customer);
                break;
            }
        }
        File file = new File(LINK);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer customer : customers) {
                bufferedWriter.write(customer.getPhone() + "," + customer.getGroup() + "," + customer.getName() + "," + customer.getGender() + "," + customer.getAddress() + "," + customer.getDateOfBirth() + "," + customer.getEmail());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    public void exportDataCSV(List<Customer> customers, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer customer : customers) {
                bufferedWriter.write(customer.getPhone() + "," + customer.getGroup() + "," + customer.getName() + "," + customer.getGender() + "," + customer.getAddress() + "," + customer.getDateOfBirth() + "," + customer.getEmail());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Đã xuất file thành công, mời bạn kiểm tra");
        } catch (IOException e) {
            System.out.println("Lỗi ghi dữ liệu");
        }
    }

    public List<Customer> findByName(String name) {
        List<Customer> customers = getAll();
        List<Customer> temp = new ArrayList<>();
        int count = 0;
        for (Customer customer: customers) {
            if(customer.getName().contains(name)) {
                temp.add(customer);
                count++;
            }
        }
        if (count != 0) {
            System.out.println("Đã tìm thấy học sinh");
        }
        else {
            System.out.println("Không tìm thấy tên học sinh");
        }
        return temp;
    }
}