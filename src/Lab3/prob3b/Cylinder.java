package Lab3.prob3B;

public class Cylinder {
    private Circle base;
    private double height;

    // Constructor
    public Cylinder(double radius, double height) {
        base = new Circle(radius); // Call the Circle constructor
        this.height = height;
    }

    // Getter and Setter for height, radius
    public double getHeight() {
        return height;
    }
    public double getRadius(){return base.getRadius();}
    public void setHeight(double height) {
        this.height = height;
    }
    public void setRadius(double radius){this.base.setRadius(radius);}

    // Method to compute the volume of the cylinder
    public double computeVolume() {
        return base.computeArea() * height; // Volume = Circle Area * Height
    }
}