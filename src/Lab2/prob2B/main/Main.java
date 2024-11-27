package Lab2.prob2B.main;

import Lab2.prob2B.Order;

public class Main {
  public static void main(String[] args){
     Order order = new Order(1, 1, 100.0, 2);
     System.out.println(order);
     
     // OrderLine orderLine = new OrderLine();// compiler error
  }
}
