package Lab5.prob1;

public class RubberDuck extends Duck{
    @Override
    public void display() {
        System.out.println("Displaying");
    }

    @Override
    public void swim() {
        System.out.println("cannot swim");
    }

    @Override
    public void fly() {
        System.out.println("cannot fly");
    }

    @Override
    public void quack() {
        System.out.println("squeaking");
    }
}
