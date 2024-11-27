package Lab3.prob3B;

import Lab3.prob3B.*;

public class Main {
  public static void main(String[] args){
      // Create a Circle object
      Circle circle = new Circle(5);
      System.out.println("Circle Area: " + circle.computeArea());

      // Create a Cylinder object
      Cylinder cylinder = new Cylinder(5, 10);
      System.out.println("Cylinder Volume: " + cylinder.computeVolume());
      System.out.println("Base Area (Circle Area): " + cylinder.computeArea());
  }
}
