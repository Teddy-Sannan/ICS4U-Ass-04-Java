/****************************************************************************
 *
 * Created by: Teddy Sannan
 * Created on: May 2020
 * Created for: ICS4U
 * This program is Triangle
 *
 ****************************************************************************/


import java.util.ArrayList;
 
public class Triangle {
  
  /**
  * This class is a triangle. Given any 3 measurements, it calculates all side lengths, 
  * then area, perimeter, height, inscribed circle radius, and circumcircle area.
  */
  
  ArrayList<Double> triangleLengths = new ArrayList<Double>(); 
  ArrayList<Double> triangleAngles = new ArrayList<Double>(); 
  double perimeter;
  double semiperimeter;
  double triangleArea;
  double sideA;
  double sideB;
  double sideC;
  double angleA;
  double angleB;
  double angleC;
  
  // Constructor
  public Triangle(ArrayList<Double> sideLengths, ArrayList<Double> angles) {
    triangleLengths = sideLengths;
    triangleAngles = angles;
  }
  
  // Assign dimensions to variables and check if the triangle is valid
  protected boolean isTriangleValid() {
    // Will always have at least 1 side
    sideA = triangleLengths.get(0);   
    if (triangleLengths.size() == 3) {
      // All 3 side lengths were given, can simply assign
      sideB = triangleLengths.get(1);
      sideC = triangleLengths.get(2);
    } else if (triangleLengths.size() == 2) {
      // 2 sides given, must find third
      sideB = triangleLengths.get(1);
      // Find with cosine Law, must have 1 angle
      angleC = triangleAngles.get(0);
      sideC = Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2) - 2 * sideA * sideB 
          * Math.cos(Math.toRadians(angleC)));
    } else {
      // Only 1 side given, use sin Law with 2 angles to find other sides
      angleA = triangleAngles.get(0);
      angleB = triangleAngles.get(1);
      angleC = 180 - angleA - angleB;
      // Check if angles are valid, C will be negaative if A + B are > 180
      if (angleC > 0) {
        // Angles are valid, can continue. Use Sin Law to find b, have 2 angles
        sideB = sideA * Math.sin(Math.toRadians(angleB)) / Math.sin(Math.toRadians(angleA));
        // Sin again to find c
        sideC = sideA * Math.sin(Math.toRadians(angleC)) / Math.sin(Math.toRadians(angleA));       
      } else {
        // Angles invalid, return false
        return false;
      }
    }
    
    // Triangle inequality theorum, sum of 2 sides must be > other side, check if sides are valid
    if ((sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA)) {
      // Valid 
      return true;
    } else {
      // Invalid
      return false;
    }
  }
  
  // get perimeter of triangle
  public double getPerimeter() {
    perimeter = sideA + sideB + sideC;
    return perimeter;
  }
  
  /**
  * This method gets area of triangle using Heron's formula.
  */
   
  public double getTriangleArea() {
    semiperimeter = perimeter / 2;
    triangleArea = Math.sqrt(semiperimeter * (semiperimeter - sideA) * (semiperimeter - sideB) 
        * (semiperimeter - sideC));
    return triangleArea;
  }
  
  /**
  * This method gets the name of triangle.
  */
  public String getName() {
    if ((sideA == sideB) && (sideB == sideC)) {
      // All sides are equal, equilateral  triangle.
      return "This triangle is an equilateral triangle.";
    } else if ((sideA != sideB) && (sideB != sideC) && (sideA != sideC)) {
      // All sides are different, scalene triangle
      return "This triangle is a scalene triangle.";
    } else {
      // Must be isosceles triangle
      return "This triangle is an isosceles triangle.";
    }
  }
  
  /**
  * This method gets the height of the triangle.
  */
  public double getHeight() {
    // Using area formula (A=bh/2) to solve for height
    double height;
    
    if ((sideA > sideB) && (sideA > sideC)) {
      // find height from sideA since it is largest
      height = 2 * triangleArea / sideA;
    } else if ((sideB > sideA) && (sideB > sideC)) {
      // find height from sideB since it is largest
      height = 2 * triangleArea / sideB;
    } else {
      // sideC is largest
      height = 2 * triangleArea / sideC;
    }
    return height;
  }
  
  /**
  * This method gets the radius of inscribed circle.
  */  
  public double getInscribedRadius() {
    // r = A/s
    double inscribedRadius = triangleArea / semiperimeter;
    return inscribedRadius;
  }

  /**
  * This method gets the area of circumcircle.
  */  
  public double getCircumcircleArea() {
    // given formula on https://keisan.casio.com/exec/system/1223429573
    double circleRadius = (sideA * sideB * sideC) / (4 * triangleArea);
    double circumcircleArea = Math.PI * Math.pow(circleRadius, 2); 
    return circumcircleArea;
  }
}