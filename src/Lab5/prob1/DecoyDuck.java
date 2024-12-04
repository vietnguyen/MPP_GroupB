package Lab5.prob1;

public class DecoyDuck extends Duck {
  public DecoyDuck() {
    super(new CannotFly(), new MuteQuack());
  }
}
