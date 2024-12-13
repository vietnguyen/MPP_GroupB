# Lab 8 Solutions

## Question 1

For details [Lab8/Prob1/RandSupplierTest.java](./Prob1/RandSupplierTest.java).

### 1a
#### i)
- Parameters: none
- Free variables: s, t

#### ii)
- Parameters: s, t
- Free variables: ignoreCase

### 1b
An example of a method reference is:
`Math::random`
Its corresponding functional interface is `Supplier<Double>`. Do the following:

#### i)
Rewrite this method reference as a lambda expression:
```java
Supplier<Double> s1 = () -> Math.random();
```

#### ii)
Put this method expression in a main method in a Java class and use it to print a random number to the console:
```java
public class RandSupplierTest {
    public static void main(String[] args) {
        Supplier<Double> s1 = () -> Math.random();
        System.out.println(s1.get());
    }
}
```

#### iii)
Create an equivalent Java class in which the functional behavior of `Math::random` is expressed using an inner class (implementing `Supplier`); call this inner class from a main method and use it to output a random number to the console. The behavior should be the same as in part ii:
```java
import java.util.function.Supplier;

public class RandSupplierTest {
    public static double supplyRand() {
        class RandSupplier implements Supplier<Double> {
            @Override
            public Double get() {
                return Math.random();
            }
        }
        return new RandSupplier().get();
    }

    public static void main(String[] args) {
        System.out.println(supplyRand());
    }
}
```

## Question 2

For details [Lab8/Prob2/BiFunctionTest.java](./Prob2/BiFunctionTest.java).

Consider the following lambda expression. Can this expression be correctly typed as a `BiFunction`?
```java
(x, y) -> {
    List<Double> list = new ArrayList<>();
    list.add(Math.pow(x, y));
    list.add(x * y);
    return list;
};
```
Yes, it can. Demonstrate you are right by doing the following: In the main method of a Java class, assign this lambda expression to an appropriate `BiFunction` and call the `apply` method with arguments `(2.0, 3.0)`, and print the result to console:
```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class BiFunctionTest {
    public static void main(String[] args) {
        BiFunction<Double, Double, List<Double>> b1 = (x, y) -> {
            List<Double> list = new ArrayList<>();
            list.add(Math.pow(x, y));
            list.add(x * y);
            return list;
        };
        List<Double> result = b1.apply(2.0, 3.0);
        System.out.println(result);
    }
}
```

## Question 3

For details [Lab8/Prob3/Examples.java](./Prob3/Examples.java).

### 3.1
Assign the lambda expressions to a variable of the appropriate type and then express as a method reference. Indicate which type of method reference you are using. Assume that the `Employee` and `EmployeeNameComparator` classes are available.

#### A. (Employee e) -> e.getName()
```java
Function<Employee, String> f1 = (Employee e) -> e.getName();
Function<Employee, String> f2 = Employee::getName;
```

#### B. (Employee e, String s) -> e.setName(s)
```java
BiConsumer<Employee, String> f3 = (Employee e, String s) -> e.setName(s);
BiConsumer<Employee, String> f4 = Employee::setName;
```

#### C. (String s1, String s2) -> s1.compareTo(s2)
```java
BiFunction<String, String, Integer> f5 = (String s1, String s2) -> s1.compareTo(s2);
BiFunction<String, String, Integer> f6 = String::compareTo;
```

#### D. (Integer x, Integer y) -> Math.pow(x, y)
```java
BiFunction<Integer, Integer, Double> f7 = (Integer x, Integer y) -> Math.pow(x, y);
BiFunction<Integer, Integer, Double> f8 = Math::pow;
```

#### E. (Apple a) -> a.getWeight()
```java
Function<Apple, Double> f9 = (Apple a) -> a.getWeight();
Function<Apple, Double> f10 = Apple::getWeight;
```

#### F. (String x) -> Integer.parseInt(x)
```java
Function<String, Integer> f11 = (String x) -> Integer.parseInt(x);
Function<String, Integer> f12 = Integer::parseInt;
```

#### G. EmployeeNameComparator comp = new EmployeeNameComparator();
(Employee e1, Employee e2) -> comp.compare(e1, e2)
```java
EmployeeNameComparator comp = new EmployeeNameComparator();
Comparator<Employee> f13 = (e1, e2) -> comp.compare(e1, e2);
Comparator<Employee> f14 = comp::compare;
```

### 3.2
Create a method in your `Examples` class called `evaluator`. Inside `evaluator`, evaluate each of your method expressions using input arguments of your own choosing.
```java
public class Examples {
    Function<Employee, String> f1 = (Employee e) -> e.getName();
    Function<Employee, String> f2 = Employee::getName;
    BiConsumer<Employee, String> f3 = (Employee e, String s) -> e.setName(s);
    BiConsumer<Employee, String> f4 = Employee::setName;
    BiFunction<String, String, Integer> f5 = (String s1, String s2) -> s1.compareTo(s2);
    BiFunction<String, String, Integer> f6 = String::compareTo;
    BiFunction<Integer, Integer, Double> f7 = (Integer x, Integer y) -> Math.pow(x, y);
    BiFunction<Integer, Integer, Double> f8 = Math::pow;
    Function<Apple, Double> f9 = (Apple a) -> a.getWeight();
    Function<Apple, Double> f10 = Apple::getWeight;
    Function<String, Integer> f11 = (String x) -> Integer.parseInt(x);
    Function<String, Integer> f12 = Integer::parseInt;
    EmployeeNameComparator comp = new EmployeeNameComparator();
    Comparator<Employee> f13 = (e1, e2) -> comp.compare(e1, e2);
    Comparator<Employee> f14 = comp::compare;

    public void evaluator() {
        System.out.println(f2.apply(new Employee("John", 1000)));
        
        Employee e = new Employee("John", 1000);
        f4.accept(e, "Smith");
        System.out.println(e.getName());

        System.out.println(f6.apply("John", "Doe"));

        System.out.println(f8.apply(2, 3));

        Apple a = new Apple();
        System.out.println(f10.apply(a));

        System.out.println(f12.apply("123"));

        Employee e1 = new Employee("John", 1000);
        Employee e2 = new Employee("Doe", 2000);
        System.out.println(f14.compare(e1, e2));
    }

    public static void main(String[] args) {
        Examples ex = new Examples();
        ex.evaluator();
    }
}
```