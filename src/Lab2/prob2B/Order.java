package Lab2.prob2B;

import java.util.ArrayList;
import java.util.List;

public class Order {
  private int orderNumber;
  List<OrderLine> orderLines = new ArrayList<>();
  
  public Order(int orderNumber, int lineNum, double price, int quantity) {
    this.orderNumber = orderNumber;
    addOrderLine(new OrderLine(lineNum, price, quantity, this));
  }
  
  public void addOrderLine(OrderLine orderLine) {
    orderLines.add(orderLine);
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
