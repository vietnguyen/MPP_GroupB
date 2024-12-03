package Lab4.probC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private UUID orderNo;
    private double orderAmount;
    private LocalDate orderDate;

    public Order(double orderAmount, LocalDate orderDate){
        this.orderNo = UUID.randomUUID();
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getOrderAmount() {
        return orderAmount;
    }
}
