package Lab3.prob3A;

public class Circle {
    protected double radius; // Protected to allow access in subclasses

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    // Getter and Setter for radius
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Method to compute the area of the circle
    public double computeArea() {
        return Math.PI * radius * radius; // Area = πr²
    }
}