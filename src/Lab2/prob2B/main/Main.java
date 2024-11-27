package Lab2.prob2B.main;

import Lab2.prob2B.Order;

public class Main {
  public static void main(String[] args){
     Order order = new Order(1, 1, 100.0, 2);
     // OrderLine orderLine = new OrderLine();// compiler error
     System.out.println(order);
     // Expected output:
      // Order Number: 1
      //   line item:
      //     line num = 1    price = 100.0    quantity = 2
  }
}
