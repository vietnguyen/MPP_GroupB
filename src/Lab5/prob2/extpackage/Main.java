package Lab5.prob2.extpackage;

import java.time.LocalDate;

import Lab5.prob2.Customer;
import Lab5.prob2.Order;
import Lab5.prob2.CustOrderFactory;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world");
		Customer cust = CustOrderFactory.createCustomer("Bob");
		Order order = CustOrderFactory.createOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = CustOrderFactory.createOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");

		System.out.println(cust.getOrders());
	}
}

		
