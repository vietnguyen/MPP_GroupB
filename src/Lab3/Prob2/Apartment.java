package Lab3.Prob2;

public class Apartment extends BaseEntity{
    private String number;
    private double monthlyRent;

    public Apartment(String number, double rent){
        this.number = number;
        this.monthlyRent = rent;
    }

    public double getMonthlyRent(){
        return monthlyRent;
    }
}
