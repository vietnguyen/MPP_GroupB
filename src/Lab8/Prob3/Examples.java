package Lab8.Prob3;

import java.util.Comparator;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Examples {
	
	//type: Class::instanceMethod
	Function<String, String> upper1 = (String x) -> x.toUpperCase();
	Function<String, String> upper2 = String::toUpperCase;
	
	//	A. (Employee e) -> e.getName()
	Function<Employee, String> f1 = (Employee e) -> e.getName();
	Function<Employee, String> f2 = Employee::getName;
	
	//	B. (Employee e,String s) -> e.setName(s)
	BiConsumer<Employee, String> f3 = (Employee e, String s) -> e.setName(s);
	BiConsumer<Employee, String> f4 = Employee::setName;
	
	//	C. (String s1,String s2) -> s1.compareTo(s2)
	BiFunction<String, String, Integer> f5 = (String s1, String s2) -> s1.compareTo(s2);
	BiFunction<String, String, Integer> f6 = String::compareTo;
	
	//	D. (Integer x,Integer y) -> Math.pow(x,y)
	BiFunction<Integer, Integer, Double> f7 = (Integer x,Integer y) -> Math.pow(x, y);
	BiFunction<Integer, Integer, Double> f8 = Math::pow;
	
	//	E. (Apple a) -> a.getWeight()
	public class Apple{
		public double getWeight() {
			return new Random().nextDouble();
		}
	}
	Function<Apple, Double> f9 = (Apple a) -> a.getWeight();
	Function<Apple, Double> f10 = Apple::getWeight;
	
	//	F. (String x) -> Integer.parseInt(x);
	Function<String, Integer> f11 = (String x) -> Integer.parseInt(x);
	Function<String, Integer> f12 = Integer::parseInt;
	
	//	G. (Employee e1, Employee e2) -> comp.compare(e1,e2)
	EmployeeNameComparator comp = new EmployeeNameComparator();
	Comparator<Employee> f13 = (e1, e2) -> comp.compare(e1, e2);
	Comparator<Employee> f14 = comp :: compare;
	
	public void evaluator() {
		System.out.println(upper2.apply("hello"));
        System.out.println(f1.apply(new Employee("John", 1000)));
        System.out.println(f2.apply(new Employee("John", 1000)));
        
        Employee e = new Employee("John", 1000);
        f3.accept(e, "Doe");
        System.out.println(e.getName());
        
        f4.accept(e, "Smith");
        System.out.println(e.getName());

        System.out.println(f5.apply("John", "Doe"));
        System.out.println(f6.apply("John", "Doe"));

        System.out.println(f7.apply(2, 3));
        System.out.println(f8.apply(2, 3));

        Apple a = new Apple();
        System.out.println(f9.apply(a));
        System.out.println(f10.apply(a));

        System.out.println(f11.apply("123"));
        System.out.println(f12.apply("123"));

        Employee e1 = new Employee("John", 1000);
        Employee e2 = new Employee("Doe", 2000);
        System.out.println(f13.compare(e1, e2));
        System.out.println(f14.compare(e1, e2));
	}
	
	public static void main(String[] args) {
		Examples e = new Examples();
		e.evaluator();
	}
	
}
