package Lab4.probC;

import java.util.UUID;

public class Hourly extends Employee{
    private double hourlyWage;
    private int hoursPerWeek;

    public Hourly(double hourlyWage, int hoursPerWeek){
        this.empId = UUID.randomUUID();
        this.hoursPerWeek = hoursPerWeek;
        this.hourlyWage = hourlyWage;
    }

    @Override
    public double calcGrossPay(int month, int year) {
        return hourlyWage * hoursPerWeek * 5 * 4;
    }
}
