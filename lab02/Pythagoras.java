/**
  * CS180 - Lab 02
  * Explain briefly the functionality of the class.
  * @author Nanxin Jin, jinn@purdue.edu
  */
 
import java.util.Scanner;

public class Pythagoras {
  public static void main(String[] args) {
      Pythagoras p = new Pythagoras();
      Scanner s = new Scanner(System.in);
      System.out.println("Enter side 'a':");
      int a = s.nextInt();
      System.out.println("Enter side 'b':");
      int b = s.nextInt();
      double h;
      h = p.getHypotenuse(a,b);
      System.out.println("Hypotenuse:" + h);
      
  }
  
  public double getHypotenuse(int a, int b) {
      double h;
      h = Math.sqrt(a * a + b * b);
      return h;
  }
}