package view;


import controller.CustomerController;
import entity.Customer;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private static CustomerController customerController = new CustomerController();
    private static List<Customer> customers;

    public static void main(String[] args) {
        while (true) {
            System.out.println("--------- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ------------");
            System.out.println("Chọn chức năng theo số (để tiếp tục) : ");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng");
            Scanner scanner = new Scanner(System.in);
            int choice = inputChoice();
            switch (choice) {
                case 1:
                    customers = customerController.getAll();
                    display(customers);
                    break;
                case 2:
                    Customer customer = inputCustomer();
                    customerController.save(customer);
                    System.out.println("Thêm mới thành công ");
                    break;
                case 3:
                    System.out.println("Tính năng chưa làm");
                case 4:
                    System.out.println("Mời bạn nhập vào số đth cần xóa");
                    String phone = scanner.nextLine();
                    boolean isFind = customerController.isExistsCustomer(phone);
                    if (!isFind) {
                        System.out.println("Không tìm thấy customer có số dth là " + phone);
                    } else {
                        System.out.println("Bạn có chắc muốn xóa hay không. Nhấn Y nếu đồng ý hoặc bất kỳ kí tự khác nếu không?");
                        String isConfirm = scanner.nextLine();
                        if (isConfirm.equalsIgnoreCase("y")) {
                            customerController.deleteByPhone(phone);
                            System.out.println("Xóa khách hàng thành công");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Tìm kiếm khách hàng theo tên");
                    String name = scanner.nextLine();
                    customers = customerController.searchByName(name);
                    display(customers);
                case 6:
                    System.out.println("Đọc file dữ liệu sẵn có");
                    customers = customerController.getAll();
                    display(customers);
                    break;
                case 7:
                    System.out.println("Truy xuất dữ liệu khách hàng");
                    customers = customerController.getAll();
                    System.out.println("Mời bạn nhập tên file muốn lưu");
                    String filename = scanner.nextLine();
                    boolean check1 = false;
                    while (!filename.matches("^[A-Za-z0-9]+\\.csv$") && !check1) {
                        System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
                        filename = scanner.nextLine();
                        check1 = false;
                        try {
                            int length = filename.length();
                        } catch (OutOfMemoryError e) {
                            System.out.println("Bạn nhập tên đăng nhập quá dài. Mời nhập lại");
                            check1 = true;
                        }
                    }
                    customerController.exportDataCSV(customers,filename);
                    break;
                case 8:
                    System.out.println("Thoát");
                    return;
            }
        }
    }

    private static Customer inputCustomer() {
        Scanner scanner = new Scanner(System.in);
        String phone;
        int group = -1;
        String name;
        String gender;
        String address;
        String dateOfBirth;
        String email;

        boolean check = false;

        System.out.print("Mời bạn nhập số điện thoại: ");
        phone = scanner.nextLine();
        check = false;
        while (!phone.matches("^[0-9]{10}$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            phone = scanner.nextLine();
        }

        System.out.print("Mời bạn nhập group: ");
        while (!check) {
            try {
                group = Integer.parseInt(scanner.nextLine());
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai lựa chon. Mời nhập lại");
            } catch (Exception e) {
                System.out.println("Lỗi khác");
            }
        }

        System.out.print("Mời bạn nhập tên: ");
        name = scanner.nextLine();
        check = false;
        while (!name.matches("^[A-Za-z\\s]+$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            name = scanner.nextLine();
            check = false;
            try {
                int length = name.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập tên quá dài. Mời nhập lại");
                check = true;
            }
        }

        System.out.print("Mời bạn nhập giới tính: ");
        gender = scanner.nextLine();
        while (!gender.matches("^(Nam|Nu)$")) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            gender = scanner.nextLine();
        }

        System.out.print("Mời bạn nhập địa chỉ: ");
        address = scanner.nextLine();
        check = false;
        while (!address.matches("^[A-Za-z0-9\\s]+$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            address = scanner.nextLine();
            check = false;
            try {
                int length = address.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập địa chỉ quá dài. Mời nhập lại");
                check = true;
            }
        }


        System.out.print("Mời bạn nhập ngày sinh với định dang dd/MM/YYYY: ");
        //dd/MM/YYYY
        check = false;
        dateOfBirth = scanner.nextLine();
        while (!dateOfBirth.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            dateOfBirth = scanner.nextLine();
        }


        System.out.print("Mời bạn nhập email: ");
        email = scanner.nextLine();
        check = false;
        while (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            email = scanner.nextLine();
            check = false;
            try {
                int length = email.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập email quá dài. Mời nhập lại");
                check = true;
            }
        }


        return new Customer(phone,group,name,gender,address,dateOfBirth,email);
    }

    private static int inputChoice () {
        Scanner scanner = new Scanner(System.in);

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException var2) {
            System.out.println("Nhập sai lựa chọn. Mời bạn nhập lại");
        } catch (Exception var3) {
            System.out.println("Lỗi khác");
        }

        return 0;
    }

    public static void display(List<Customer> customers) {
        System.out.println("Xem toàn bộ danh sách khách hàng");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}






