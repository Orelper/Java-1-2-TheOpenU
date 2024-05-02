/*This program will receive 3 numbers for user that represent a 
triangle and calculate the perimeter and the area of it*/
import java.util.Scanner;
public class Triangle1 //start of class Triangle1
{
 public static void main (String [] args) //start of method main
 {
 Scanner scan = new Scanner (System.in); //Creates a new object of type Scanner
 System.out.println ("This program calculates the area "
 + "and the perimeter of a given triangle.");
 System.out.println ("Please enter the three lengths"
 + " of the triangle's sides");
 int a = scan.nextInt(); //scan an input from type int to variable a
 int b = scan.nextInt(); //scan an input from type int to variable b
 int c = scan.nextInt(); //scan an input from type int to variable c
  int perimeter = a + b + c; //perimeter = the perimeter of triangle
  double s = perimeter / 2.0; //s = half of the perimeter of triangle
  double area = Math.sqrt(s*(s-a)*(s-b)*(s-c)); //calculate the area of triangle with "heron" formula
  System.out.println ("The lengths of the triangle sides are: " +a+","+b+","+c+".");
  System.out.println ("The perimeter of the triangle is: " +perimeter);
  System.out.println ("The area of the triangle is: " +area);
  scan.close();
 } // end of method main
} //end of class Triangle1
