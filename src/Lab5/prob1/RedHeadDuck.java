package Lab5.prob1;

public class RedHeadDuck extends Duck{
    @Override
    public void display() {
        System.out.println("Displaying");
    }

    @Override
    public void swim() {
        System.out.println("swimming");
    }

    @Override
    public void fly() {
        System.out.println("fly with wings");
    }

    @Override
    public void quack() {
        System.out.println("quacking");
    }
}
