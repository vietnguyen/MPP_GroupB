package Lab5.prob1;

public class RubberDuck extends Duck {
  public RubberDuck() {
    super(new CannotFly(), new Squeak());
  }
}
