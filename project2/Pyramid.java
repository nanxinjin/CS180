/**
 * Project 2 -- Pyramid
 * 
 * This program generates and displays a pyramid of a given height. 
 * The height is given by the user.
   
 * @author Nanxin Jin (jinn@purdue.edu)
 * @project #2
 * @02/07/2014
 */

import java.util.Scanner;

/**
 * The height of the pyramid is provided as input from the user.
 * The height should be a non-negative integer no greater than 24
 * 
 */

public class Pyramid {

  
  
  /*
 * Get the height of Pyramid from user input
 * re-prompt the user if the height is not valid (you need a loop to do so...)
 * (think about which kind of loop is best to do that...)
 */

    public int getHeight() {
      int heig;
      do {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter height:");
        heig = in.nextInt();
      } while (heig<0 || heig>24);// insert your code here
    
        return heig;
    }
    
    
/*
 * main() Method
 * the main() Method must do the following:
 * create a Pyramid object
 * call getHeight method to get the height of the pyramid from user
 * Generates the desired Pyramid with the given height using loops
 *
 * @param args can be ignored.
 */

    public static void main(String[] args) {
      Pyramid p = new Pyramid();// TODO create a Pyramid object
      int h = p.getHeight();// TODO input Height for the pyramid
      // TODO call the getHeight method to get the height
      
      for (int i = 0; i < 3; i++) {
      System.out.print(i); 
      System.out.print(" ");
}// TODO generate the pyramid with the height given by the user

      // Note: be sure to adhere to the output format described above
    }
    
}