package Lab3.Prob2;

public class main {
    public static void main(String[] args){
        var landlord1 = new Landlord("Jim", "Jones", "jimjones@etst.com");
        var building1 = new Building("1000 W 10th St", 1500);
        landlord1.addBuilding(building1);
        building1.addApartment(new Apartment("A100", 800));
        building1.addApartment(new Apartment("A101", 950));
        building1.addApartment(new Apartment("A102", 1200));
        building1.addApartment(new Apartment("B200", 600));
        var building2 = new Building("1000 E 12th St", 900);
        landlord1.addBuilding(building2);
        building2.addApartment(new Apartment("003", 400));
        building2.addApartment(new Apartment("001", 400));
        building2.addApartment(new Apartment("002", 700));
        building2.addApartment(new Apartment("004", 950));
        building2.addApartment(new Apartment("005", 550));

        var landlord2 = new Landlord("Sam", "Stone", "samestone@etst.com");
        var building3 = new Building("300 E 6th St", 1300);
        landlord2.addBuilding(building3);
        building3.addApartment(new Apartment("1", 600));
        building3.addApartment(new Apartment("2", 600));
        building3.addApartment(new Apartment("3", 800));

        System.out.printf("%s's total profit for this month is $%.0f", landlord1.getFirstName(), landlord1.calculateTotalMonthlyProfit());
        System.out.println();
        System.out.printf("%s's total profit for this month is $%.0f", landlord2.getFirstName(), landlord2.calculateTotalMonthlyProfit());
    }
}
