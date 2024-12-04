package Lab5.prob1.solution2;

public class DecoyDuck extends Duck {
  public DecoyDuck() {
    super(new CannotFly(), new MuteQuack());
  }
}
