package Lab5.prob1;

public class Duck  {
  private FlyBehavior flyBehavior;
  private QuackBehavior quackBehavior;
  
  public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
    this.flyBehavior = flyBehavior;
    this.quackBehavior = quackBehavior;
  }
  
  public void quack() {
    quackBehavior.quack();
  }

  public void fly() {
    flyBehavior.fly();
  }
  
  public void display() {
    System.out.println("  displaying");
  }
  
  public void swim() {
    System.out.println("  swimming");
  }
}
