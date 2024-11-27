package Lab2.prob2B;

public class OrderLine {
  private Order order;
  private int lineNum;
  private double price;
  private int quantity;
  OrderLine(int lineNum, double price, int quantity, Order order) {
    this.lineNum = lineNum;
    this.price = price;
    this.quantity = quantity;
    this.order = order;
  }
  
  // getters
  public int getLineNum() {
    return lineNum;
  }
  public double getPrice() {
    return price;
  }
  public int getQuantity() {
    return quantity;
  }
  public Order getOrder() {
    return order;
  }
  
  @Override
  public String toString() {
    return "  line item: \n"  + "    line num = " + lineNum + "    price = " + price + "    quantity = "+ quantity;
  }
}
