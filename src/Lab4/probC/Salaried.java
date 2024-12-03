package Lab4.probC;

import java.util.UUID;

public class Salaried extends Employee{
    private double salary;

    public Salaried(double salary){
        this.empId = UUID.randomUUID();
        this.salary = salary;
    }

    @Override
    public double calcGrossPay(int month, int year) {
        return salary;
    }
}
