package Lab3.Prob2;

import java.util.ArrayList;
import java.util.List;

public class Landlord extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private List<Building> buildings = new ArrayList<>();

    public Landlord(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public void addBuilding(Building building){
        buildings.add(building);
    }

    public double calculateTotalMonthlyProfit(){
        double total = 0;
        for (Building building : buildings){
            total += building.calculateMonthlyProfit();
        }

        return total;
    }
}
