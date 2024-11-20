package CaseStudy.view;

import CaseStudy.controller.CustomerController;
import CaseStudy.controller.HistoryController;
import CaseStudy.entity.Customer;
import CaseStudy.entity.History;


import java.util.List;
import java.util.Scanner;


public class MainView {

    private static CustomerController customerController = new CustomerController();
    private static List<Customer> customers;
    private static History histories;

    public MainView() {
    }

    public static void main(String[] args) {
        // ĐĂNG NHẬP
        Scanner sc = new Scanner(System.in);
        Customer guest;
        String userNameLogin;
        String passWordLogin;
        boolean loginCheck;
        System.out.println("----CHÀO MỪNG BẠN ĐÃ ĐẾN TRANG QUẢN LÝ DỮ LIỆU----");
        System.out.println("Bạn đã có tài khoản đăng nhập ? Nhấn Y nếu đã có, nhấn N nếu chưa có và sẽ đăng ký");
        String loginOption = sc.nextLine();
        if (loginOption.equalsIgnoreCase("N")) {
            System.out.println("Mời bạn tiến hành đăng ký");
            guest = inputCustomer();
            customerController.save(guest);
            System.out.println("Bạn đã đăng ký thành công, mời bạn khởi động lại chương trình để đăng nhập");
        } else if (loginOption.equalsIgnoreCase("Y")) {
            System.out.println("Mời bạn nhập tên đăng nhập");
            userNameLogin = sc.nextLine();
            System.out.println("Mời bạn nhập mật khẩu");
            passWordLogin = sc.nextLine();
            loginCheck = checkLogin(userNameLogin,passWordLogin);
            if (loginCheck) {
                System.out.println("Đăng nhập thành công");
                // Bắt đầu lựa chọn
                int searchId;
                int searchIdHistory;
                String searchUsername;
                while (true) {
                    System.out.println("---------Quản lý Thông Tin Khách Hàng---------");
                    System.out.println("1. Hiển thị toàn bộ danh sách khách hàng");
                    System.out.println("2. Quản lý thông tin cơ bản khách hàng");
                    System.out.println("3. Quản lý thông tin chi tiết khách hàng");
                    System.out.println("4. Báo cáo dữ liệu khách hàng");
                    System.out.println("5. Thoát");
                    System.out.println("Mời bạn nhập lựa chọn");
                    int choice = inputChoice();
                    switch (choice) {
                        case 1:
                            customers = customerController.getAll();
                            display(customers);
                            break;
                        case 2:
                            System.out.println("Mời bạn nhập id khách hàng cần hiển thị");
                            searchId = inputNumber();
                            System.out.println("Mời bạn nhập username");
                            searchUsername = inputNameOrUserName();
                            displayOneCustomer(searchId,searchUsername);
                            break;
                        case 3:
                            System.out.println("Mời bạn nhập id của bạn cần hiển thị lịch sử");
                            searchIdHistory = inputNumber();
                            histories = HistoryController.getHistoryByID(searchIdHistory);
                            if (histories != null) {
                                System.out.println(histories);
                            } else {
                                System.out.println("Không tìm thấy lịch sử khách hàng");
                            }
                            break;
                        case 4:
                            System.out.println("Truy xuất dữ liệu khách hàng");
                            customers = customerController.getAll();
                            System.out.println("Mời bạn nhập tên file muốn lưu");
                            String filename = sc.nextLine();
                            boolean check1 = false;
                            while (!filename.matches("^[A-Za-z0-9]+\\.csv$") && !check1) {
                                System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
                                filename = sc.nextLine();
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
                        case 5:
                            System.out.println("Thoát");
                            return;
                        default:
                            break;
                    }
                }
            }
            else {
                System.out.println("Bạn chưa đăng ký");
            }
        }
    }

    private static boolean checkLogin(String userNameLogin, String passWordLogin) {
        customers = customerController.getAll();
        boolean checkLogin = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPassword().equals(passWordLogin) && customers.get(i).getUsername().equals(userNameLogin)) {
                checkLogin = true;
            }
        }
        return checkLogin;
    }

    private static int inputChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai định dạng");
            } catch (Exception e) {
                System.out.println("Lỗi đã xảy ra");
            }
        return choice;
    }

    private static int inputNumber() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        int number = -1;
        do {
            check = true;
            try {
                number = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai định dạng, mời nhập lại");
                check = false;
            } catch (Exception e) {
                System.out.println("Lỗi đã xảy ra, mời nhập lại");
                check = false;
            }
            if (number < -1) {
                System.out.println("Giá trị nhập < 1, mời nhập lại");
                check = false;
            }
            else if (number == -1 || number == 0) {
                check = false;
            }
        } while (!check);

        return number;
    }

    private static String inputNameOrUserName (){
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        while (!name.matches("^[A-Za-z0-9]+$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng của USERNAME");
            name = sc.nextLine();
            check = false;
            try {
                int length = name.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập địa chỉ quá dài. Mời nhập lại");
                check = true;
            }
        }
        return name;
    }

    public static void displayOneCustomer(int id, String username) {
        customers = customerController.getAll();
        boolean check = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id && customers.get(i).getUsername().equals(username)) {
                check = true;
                System.out.println(customers.get(i).toString());
            }
        }
        if (!check){
            System.out.println("Bạn đã nhập sai id hoặc username");
        }
    }

    public static void display(List<Customer> customers) {
        System.out.println("Hiển thị toàn bộ danh sách khách hàng");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static Customer inputCustomer() {
        Scanner scanner = new Scanner(System.in);
        int id = -1;
        String name;
        String phone;
        String dateOfBirth;
        String userName;
        String passWord;
        String email;

        boolean check = false;

        System.out.print("Mời bạn nhập id: ");
        while (!check) {
            try {
                id = Integer.parseInt(scanner.nextLine());
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

        System.out.print("Mời bạn nhập số điện thoại: ");
        phone = scanner.nextLine();
        check = false;
        while (!phone.matches("^[0-9]{10}$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            phone = scanner.nextLine();
        }


        System.out.print("Mời bạn nhập ngày sinh với định dang dd/MM/YYYY: ");
        //dd/MM/YYYY
        check = false;
        dateOfBirth = scanner.nextLine();
        while (!dateOfBirth.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            dateOfBirth = scanner.nextLine();
        }

        System.out.print("Mời bạn nhập tên đăng nhập: ");
        userName = scanner.nextLine();
        check = false;
        while (!userName.matches("^[A-Za-z]+$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            userName = scanner.nextLine();
            check = false;
            try {
                int length = userName.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập tên đăng nhập quá dài. Mời nhập lại");
                check = true;
            }
        }

        System.out.print("Mời bạn tạo mật khẩu: ");
        passWord = scanner.nextLine();
        check = false;
        while (!passWord.matches("^[A-Za-z0-9]+$") && !check) {
            System.out.println("Bạn đã nhập sai định dạng, mời nhập lại");
            passWord = scanner.nextLine();
            check = false;
            try {
                int length = passWord.length();
            } catch (OutOfMemoryError e) {
                System.out.println("Bạn nhập mật khẩu quá dài. Mời nhập lại");
                check = true;
            }
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


        return new Customer(id, name, phone, dateOfBirth, userName, passWord, email);
    }
}
