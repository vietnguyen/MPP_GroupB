package Lab3.prob3A;

public class Cylinder extends Circle {
    private double height;

    // Constructor
    public Cylinder(double radius, double height) {
        super(radius); // Call the Circle constructor
        this.height = height;
    }

    // Getter and Setter for height
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Method to compute the volume of the cylinder
    public double computeVolume() {
        return computeArea() * height; // Volume = Base Area * Height
    }
}