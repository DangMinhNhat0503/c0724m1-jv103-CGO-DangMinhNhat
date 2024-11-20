package CaseStudy.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class History implements Comparable<History>{
    private int id;
    private String date;
    private String productName;
    private int quantity;
    private int unitPrice;
    private int customerTurnover;

    public History () {
    }

    public History (int code, String date, String productName, int quantity, int unitPrice, int customerTurnover) {
        this.id = code;
        this.date = date;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.customerTurnover = customerTurnover;
    }
    // id, date, productName, quantity, unitPrice, customerTurnover

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCustomerTurnover() {
        return customerTurnover;
    }

    public void setCustomerTurnover(int customerTurnover) {
        this.customerTurnover = customerTurnover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(History o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(this.getDate(), formatter);
        LocalDate date2 = LocalDate.parse(o.getDate(), formatter);
        if (date1.isEqual(date2)) {
            return 0;
        } else if (date1.isBefore(date2)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", customerTurnover=" + customerTurnover +
                '}';
    }
}
