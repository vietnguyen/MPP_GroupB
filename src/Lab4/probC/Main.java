package Lab4.probC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    
    public static void main(String[] args){
        var commisionedEmp = createCommisionedEmployee();

        Employee[] employees = new Employee[]{
                new Hourly(20, 8),
                new Salaried(5000),
                commisionedEmp
        };

        int[] months = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int[] years = new int[]{2020,2021,2022,2023,2024};
        var rand = new Random();
        int month = months[rand.nextInt(0, months.length)], year = years[rand.nextInt(0, years.length)];

        for (Employee employee : employees){
            System.out.printf("The paycheck for employee with ID %s for %d/%d is:\n", employee.empId, month, year);
            var payCheck = employee.calcCompensation(month, year);
            payCheck.print();
            System.out.println("\n");
        }
    }
    
    public static Commisioned createCommisionedEmployee(){
        var commisionedEmp = new Commisioned(2000, 0.05);

        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Order order1 = new Order(6000, LocalDate.parse("11/23/2024", parser)),
            order2 = new Order(4550, LocalDate.parse("06/21/2024", parser)),
            order3 = new Order(100000, LocalDate.parse("09/03/2024", parser)),
            order4 = new Order(300, LocalDate.parse("09/03/2024", parser)),
            order5 = new Order(6000, LocalDate.parse("01/12/2023", parser));
        commisionedEmp.addOrder(order1);
        commisionedEmp.addOrder(order2);
        commisionedEmp.addOrder(order3);
        commisionedEmp.addOrder(order4);
        commisionedEmp.addOrder(order5);

        return commisionedEmp;
    }
}
