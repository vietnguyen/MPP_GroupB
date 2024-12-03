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

    @Override
    public double calcGrossPay(int month, int year) {
        double total = baseSalary;

        for (Order order : orders){
            if(order.getOrderDate().getYear() == year && order.getOrderDate().getMonthValue() == month)
                total += order.getOrderAmount();
        }

        return total;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}
