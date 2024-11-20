package CaseStudy.entity;

public class Customer extends Person implements Comparable<Customer>{
    private String username;
    private String password;
    private String email;

    public Customer () {
    }

    public Customer (int id, String name, String phone, String dateOfBirth, String username, String password, String email) {
        super(id, name, phone, dateOfBirth);
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                "} ";
    }


    @Override
    public int compareTo(Customer o) {
        if(this.getId() > o.getId()) {
            return 1;
        } else if(this.getId() == o.getId()) {
            return this.getName().compareTo(o.getName());
        }
        else return -1;
    }
}
