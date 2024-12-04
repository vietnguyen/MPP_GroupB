package Lab5.prob1;

public class main {
    public static void main (String[] args){
        Duck[] ducks = {new DecoyDuck(), new MallardDuck(), new RubberDuck(), new RedHeadDuck()};
        for (Duck duck : ducks){
            System.out.println(duck.getClass().getSimpleName() + ":");
            duck.display();;
            duck.swim();
            duck.fly();
            duck.quack();

            System.out.println("\n");
        }
    }
}
