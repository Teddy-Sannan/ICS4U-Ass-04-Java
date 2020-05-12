/****************************************************************************
 *
 * Created by: Teddy Sannan
 * Created on: May 2020
 * Created for: ICS4U
 * This program is Triangle
 *
 ****************************************************************************/


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TriangleStub {
   
  /**
  * This class gets triangle values from the user.
  */
  
  public static void main(String[] args) {
    
    // Create objects
    Scanner userInput = new Scanner(System.in);
    DecimalFormat formatter = new DecimalFormat("#.##");
    
    System.out.println("Please enter the dimensions of your triangle. You must enter at least 1 "
        + "side length, and up to 2 angles.");
    
    while (true) {
      
      // Declare variables here to properely reset them for more than one triangle
      ArrayList<Double> sideLengths = new ArrayList<Double>(); 
      ArrayList<Double> angles = new ArrayList<Double>(); 
      int inputCounter = 0;
      double tempInput;
      
      // Get side lengths 
      while (inputCounter < 3) {
        if (inputCounter == 0) {
          System.out.print("Enter the first side length in cm: ");
          try {
            // Check input
            tempInput = userInput.nextDouble();
          
            if (tempInput > 0) {
              sideLengths.add(tempInput);
              inputCounter++; 
            } else {
              // Entered negative or zero
              System.err.println("Invalid. Side length must be positive.");
            }
          } catch (Exception e) {
            // Didn't enter double
            System.err.println("Invalid. Side lengths must be a number.");
            userInput.nextLine();
          }
        } else {
          // Not the first side length, so user doesn't need to enter anything
          System.out.print("Enter the next side length in cm (if none, enter 0): ");
          try {
            tempInput = userInput.nextDouble();
            if (tempInput == 0) {
              break;
            } else if (tempInput < 0) {
              // Entered negative or zero
              System.err.println("Invalid. Side length must be positive.");
            } else {
              // Valid input
              sideLengths.add(tempInput);
              inputCounter++;
            }
          } catch (Exception e) {
            // Didn't enter double
            System.err.println("Invalid. Side lengths must be a number.");
          }
        }
      }
    
      // Get angles
      while (inputCounter < 3) {
      
        System.out.print("Enter an angle in degrees: ");
        try {
          // Check input
          tempInput = userInput.nextDouble();
          
          if (tempInput > 0 && tempInput <= 178) {
            angles.add(tempInput);
            inputCounter++;
          } else {
            System.err.println("Invalid. Angles must be between 1 and 178 degrees.");
          }
        } catch (Exception e) {
          System.err.println("Invalid. Angles must be a number.");
          userInput.nextLine();
        }
      }  
    
      Triangle userTriangle = new Triangle(sideLengths, angles);
    
      if (userTriangle.isTriangleValid() == true) {
        System.out.println("The perimeter of this triangle is " 
            + formatter.format(userTriangle.getPerimeter()) + " cm.");
        System.out.println("The area of this triangle is " 
            + formatter.format(userTriangle.getTriangleArea()) + " cm^2.");
        System.out.println(userTriangle.getName());
        System.out.println("The height of this triangle is " 
            + formatter.format(userTriangle.getHeight()) + " cm.");
        System.out.println("The radius of this triangle's inscribed circle is " 
            + formatter.format(userTriangle.getInscribedRadius()) + " cm.");
        System.out.println("The area of this triangle's circumcircle is " 
            + formatter.format(userTriangle.getCircumcircleArea()) + " cm^2.");
        
        // Check if user wants to enter another triangle
        userInput.nextLine();
        System.out.print("Would you like to enter another triangle? (yes/no): ");
        if (!userInput.nextLine().toLowerCase().equals("yes")) {
          break;
        }
        
      } else {
        // Restart loop so user can try again
        System.out.println("Your triangle is invalid, please try again.");
      }
    }
    System.out.println("Program End.");
  }
}