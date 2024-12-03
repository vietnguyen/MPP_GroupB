package Lab5.prob2.extpackage;

import java.time.LocalDate;

import Lab5.prob2.Customer;
import Lab5.prob2.Order;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world");
		Customer cust = new Customer("Bob");
		Order order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");

		System.out.println(cust.getOrders());
	}
}

		
