package Lab4.probC;

import java.util.UUID;

public abstract class Employee {
    protected UUID empId;
    public PayCheck calcCompensation(int month, int year){
        double gross = calcGrossPay(month, year);
        var payCheck = new PayCheck(gross);

        return payCheck;
    }

    public abstract double calcGrossPay(int month, int year);
}
