package lesson9.labs.prob12;

import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaLibrary {
    public static final Predicate<Employee> salaryAndNameCheck = e -> e.getSalary() > 100000 && e.getLastName().charAt(0) > 'E';
    public static final Predicate<Employee> salaryAndNameCheck2 = e -> e.getSalary() > 85000 && e.getLastName().charAt(0) < 'R';
    public static final Function<Employee, String> getFirstName = Employee::getFirstName;
    public static final Function<Employee, String> getLastName = Employee::getLastName;
    public static final Function<Employee, String> getFullNameUpper = e -> e.getFirstName().toUpperCase() + " " + e.getLastName().toUpperCase();
}
