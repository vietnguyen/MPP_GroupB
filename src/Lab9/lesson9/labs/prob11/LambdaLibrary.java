package lesson9.labs.prob11;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaLibrary {
    public static final Predicate<Employee> salaryAndNameCheck = e -> e.getSalary() > 100000 && e.getLastName().charAt(0) > 'M';
    public static final Function<Employee, String> getFirstName = Employee::getFirstName;
    public static final Function<Employee, String> getLastName = Employee::getLastName;
    public static final Function<Employee, String> getFullName = e -> e.getFirstName() + " " + e.getLastName();
}
