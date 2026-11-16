package database;

public class Order {
    int order_id;
    String product;
    int total;
    //int customer_id;
    Customer customer;
}


class Customer {
    int customer_id;
    String first_name;
    String last_name;
    String phone;
    String country;
}
