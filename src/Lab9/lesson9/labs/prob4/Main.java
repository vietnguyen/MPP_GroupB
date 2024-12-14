package lesson9.labs.prob4;

public class Main {

  public static void main(String[] args) {
    PrimeStream ps = new PrimeStream(); //PrimeStream is enclosing class
    ps.printFirstNPrimes(10);
    System.out.println("====");
    ps.printFirstNPrimes(5);
  }
}
