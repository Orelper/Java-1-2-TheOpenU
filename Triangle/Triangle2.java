/*This program will receive 3 numbers from user and check if they can represent a triangle , if so, 
the program will check the type of it (equilateral, isosceles, right angle or common triangle)*/
import java.util.Scanner;
public class Triangle2 { //start of class Triangle2
    public static void main(String[] args) { //start of method main
        Scanner scan = new Scanner(System.in); //creates a new object of type Scanner
        System.out.println("This program checking if three sides make a "
                + "triangle, and if so, what type of triangle.");
        System.out.println("Please enter three numbers");
        int x = scan.nextInt(); //scan an input from type int to variable x
        int y = scan.nextInt(); //scan an input from type int to variable y
        int z = scan.nextInt(); //scan an input from type int to variable z
            if ((x + y > z && x + z > y && y + z > x)) //checks if the received variables can make a triangle
            {
                if (x == z && x == y && y == z) { //checks if it's an equilateral triangle (all sides are equal)
                    System.out.println("The numbers: " +x+ ", " +y+ " and " +z+ " represent an equilateral triangle");
                } else if ((x == y || x == z || y == z) && (x != y || x != z || y != z)) { //checks if it's an isosceles triangle (two sides equal)
                    System.out.println("The numbers: " +x+ ", " +y+ " and " +z+ " represent an isosceles triangle");
                } else if (((Math.pow(x, 2) + Math.pow(y, 2)) == Math.pow(z, 2)) ||
                           ((Math.pow(x, 2) + Math.pow(z, 2)) == Math.pow(y, 2)) ||
                           ((Math.pow(z, 2) + Math.pow(y, 2)) == Math.pow(x, 2))) { //checks if it's a right-angle triangle (pythagorean theorem)
                    System.out.println("The numbers: " +x+ ", " +y+ " and " +z+ " represent a right-angle triangle");
                } else //the remaining triangle type - common triangle
                    System.out.println("The numbers: " +x+ ", " +y+ " and " +z+ " represent a common triangle");        
            } else //the numbers don't meets the conditions = it's not a triangle
                System.out.println("The numbers: " +x+ ", " +y+ " and " +z+ " cannot represent a triangle");               
        scan.close();
} // end of method main
} //end of class Triangle2
