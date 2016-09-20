import java.util.Scanner;
import java.util.Random;
public class FunArray {
 
    /*
     * Generates the two dimensional array.
     * 
     * - Create a new two dimensional array of integers with dimensions **rows by cols**
     * - Use Random class to generate integers from 0 to 50 as array values
     * - Fill in the array in row -major order (Top Left to Bottom Right)
     * - Return the two dimensional integer array at the end.
     * @parameters   int rows, int cols (they are all user input)
     * @return int[][]         random number matrix
     */
 
    public static int[][] genArray(int rows, int cols) {
      int[][] list = new int[rows][cols];
      
      Random r = new Random();
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          list[i][j] = r.nextInt(51);
        }
      }
 
        return list;
    }
 
    /*
     * Copy a 2D array into a 1D array
     *
     * - Go through the entire 2D array, 
     * - from Bottom Right to Top Left(pay attention here) 
     * - Store each number in the one dimensional array in its index order
     * - Return your one dimensional array at the end.
     *
     * @param int[][] list, a 2D array
     * @return a one dimensional array
     */
    public static int[] twoToOne(int[][] list) {
      int p = list.length * list[0].length;
      int[] one = new int[p];
      int c = 0;
      for (int i = list.length - 1; i > -1; i--){
        for (int j = list[0].length - 1; j > -1; j--){
          one[c] = list[i][j];
          System.out.print(one[c] + " ");
          c++;
        }
      }
        System.out.println();
        return one; // CHANGE THIS TO RETURN YOUR OWN ARRAY.
    }
 
    /*
     * Bubble Sort
     * 
     * - Sort the list in ascending order using "Bubble Sort"
     * - means after sorting the biggest number 
     * - will be at the end of the list
     *
     * @param int[] list, a 1D array
     * 
     */
 
    public static void bubbleSort(int[] list) {     
      int counter = list.length;
      do {
        counter--;
      for (int i = 0; i < list.length-1; i++){
      if (list[i] > list[i + 1]){
        int l = list[i];
        list[i]= list[i+1];
        list[i+1]= l;
      }
      } 
      }
      while(counter > 0);
    
      
      
      
      
    }
    
    /* 
     * Prints the one dimensional array
     *
     * - print the items in the array 
     * - separates each number by a space
     * 
     * @param int[] list A one-dimensional array of integers
     */
 
    public static void printOneDArray(int[] list) { 
      int m = list.length;
      for (int i = 0; i < m; i++){
        System.out.print(list[i] + " ");
      
      }
      System.out.println();
    }
 
    /* 
     * Prints the Two dimensional array
     *
     * - print the items in the 2D array 
     * - From "Top Left to Bottom Right"
     * - separates each number by a space
     * - make sure it goes to a new line when you reach the end of a row
     * - follow the printing format specified in the handout for each number 
     * 
     * @param int[][] list A two-dimensional array of integers
     */
 
    public static void printTwoDArray(int[][] list) {
      int a = list.length;
      int b = list[0].length;
      
      for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
          System.out.printf("%2d ", list[i][j]);
        }
        System.out.println();
      }
    }
 
    /*
    * Main Method
    *
    * - You don't need to create a FunArray object
    * - because all the methods are Static
    * - create a Scanner object
    * - prompt the user for the number of rows and cols(rows >= 1 and cols >= 1)
    * - declare a Two-Dimensional Array 
    * - use genArray method to generate the 2D array 
    * - assign the generated array to the 2D array you just created 
    * - print out the 2D array by calling printTwoDArray method
    * - declare a one-dimensional array
    * - call twoToOne method to transfer the generated 2D array into
    * - a one-dimentional array and assign it to the array you just created
    * - print out the 1D array using printOneDArray method
    * - use bubbleSort method to sort the 1D array
    * - print out the sorted array using printOneDArray method
    *
    */
 
    public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.println("Please enter rows: ");
      int a = s.nextInt();
      System.out.println("Please enter columns: ");
      int b = s.nextInt();
      int[][] two = genArray(a,b);
      



 
 System.out.println("The generated two dimensional array: ");
 printTwoDArray(two);
 
 System.out.println("The list before bubble sort: ");
 int[] one = twoToOne(two);
 System.out.println("The list after bubble sort: ");
 bubbleSort(one);
 printOneDArray(one);
 
    }
 
}

