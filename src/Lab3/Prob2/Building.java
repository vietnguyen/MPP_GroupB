package Lab3.Prob2;

import java.util.ArrayList;
import java.util.List;

public class Building extends BaseEntity{
    private  String address;
    private double monthlyMaintenanceCost;
    private List<Apartment> apartments = new ArrayList<>();

    public Building(String address, double monthlyMaintenanceCost){
        this.address = address;
        this.monthlyMaintenanceCost = monthlyMaintenanceCost;
    }

    public void addApartment(Apartment apartment){
        apartments.add(apartment);
    }

    public double calculateMonthlyProfit(){
        double total = 0;
        for (Apartment apartment : apartments){
            total += apartment.getMonthlyRent();
        }

        return total - monthlyMaintenanceCost;
    }
}
