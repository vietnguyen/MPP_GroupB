package Lab2.prob2B;

import java.util.ArrayList;
import java.util.List;

public class Order {
  private int orderNumber;
  List<OrderLine> orderLines = new ArrayList<>();
  
  public Order(int orderNumber, int lineNum, double price, int quantity) {
    this.orderNumber = orderNumber;
    addOrderLine(lineNum, price, quantity);
  }
  
  
  public void addOrderLine(int lineNum, double price, int quantity) {
    orderLines.add(new OrderLine(lineNum, price, quantity, this));
  }
  
  public List<OrderLine> getOrderLines() {
    return orderLines;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Order Number: ").append(orderNumber).append("\n");
    for (OrderLine orderLine : orderLines) {
      sb.append(orderLine).append("\n");
    }
    return sb.toString();
  }
}
