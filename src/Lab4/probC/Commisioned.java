package Lab4.probC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commisioned extends Employee{
    private double baseSalary;
    private double commission;
    private List<Order> orders = new ArrayList<>();

    public Commisioned(double baseSalary, double commission){
        this.empId = UUID.randomUUID();
        this.baseSalary = baseSalary;
        this.commission = commission;
    }

  /**
   * Commissioned employees are also paid monthly. They receive a small base salary, plus
   * a percentage (commission) on the total value of all orders they sold during the previous
   * month.
   */
  @Override
  public double calcGrossPay(int month, int year) {
        double total = baseSalary;
        int previousMonth = month - 1;

        for (Order order : orders){
            if(order.getOrderDate().getYear() == year && order.getOrderDate().getMonthValue() == previousMonth)
                total += order.getOrderAmount();
        }

        return baseSalary + (total * commission);
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}
