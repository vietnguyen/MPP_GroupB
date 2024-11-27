# Lab 3 Solutions
## Question 3

<img src="./images/prob3A.png">

A. The idea that "a Cylinder is a Circle" is conceptually flawed.While a cylinder has a circular base, it is not a circle.
And also violates the LSP principle.
The relationship between a Circle and a Cylinder is better described as "a Cylinder has-a Circle as its base."
So we should use Composition instead.

**B. Why Composition Makes Sense**

<img src="./images/prob3B.png">

Using composition, the Cylinder has-a Circle. The Circle class is embedded in the Cylinder class, representing the circular base.

**This design:**

* Separates the concerns of 2D (Circle) and 3D (Cylinder) shapes.
* Avoids misusing inheritance to create artificial relationships.