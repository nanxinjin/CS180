/**
 * This class contains a main method as well as the static method 'multiply'
 * which computes the value of the multiplication of two matrices.
 * 
 */
public class Lab11 {

    /**
     * The main method will not be tested by WebCAT. It is for you to use
     * for debugging.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        // Try multiplying 
        double[][] a = {
                { 0.8147 , 0.1270 , 0.6324 },
                { 0.9058 , 0.9134 , 0.0975 }
        };
        double[][] b = {
                { 0.2785 , 0.9649 , 0.9572 , 0.1419 },
                { 0.5469 , 0.1576 , 0.4854 , 0.4218 },
                { 0.9575 , 0.9706 , 0.8003 , 0.9157 }
        };
        
        double[][] ab = {
                { 0.9019 , 1.4199 , 1.3476 , 0.7483 },
                { 0.8452 , 1.1126 , 1.3884 , 0.6031 }
        };
        
    }
    
    
    
    /**
     * This method spawns of several threads to concurrently compute the
     * solution to a * b. Also see the lab handout for more information
     * 
     * @param a           The left operand matrix
     * @param b           The right operand matrix
     * @param maxThreads  The maximum number of threads to use in calculation
     * @return The result of a * b where * represents matrix multiplication
     */
    public static double[][] multiply(double[][] a, double[][] b,
            int maxThreads) {
        // TODO: Ensure that the matrix dimensions agree.
        // TODO: Calculate the number of threads to use
        // TODO: Initialize the matrix that will store the solution
        // TODO: Create an array of threads    
        // TODO: Create and start each Thread. Hint: Use calculateBlocks(...)
        // TODO: Join each thread
        // TODO: Return the solution
        return null;
    }
    
    
    /**
     * This method is used to calculate the start and end rows for each thread's
     * block. This method must return an array of length (nThreads + 1). This
     * array represents the boundaries of the ranges for each thread. For
     * example, if there are  12 rows and 3 threads, then the resulting array
     * would be {0, 4, 8, 12}.
     * 
     * You MUST write this method in such a way that there is at most a
     * difference of 1 row between any of the threads. However, it does not
     * matter which threads carry the extra burden of 1 row. 
     * 
     * 
     * @param nRows    The number of rows in the solution matrix
     * @param nThreads The number of threads to utilize
     * @return An array of the range boundaries for each thread
     */
    public static int[] calculateBlocks(int nRows, int nThreads) {
        //TODO: Calculate start and end location for matrices
        return null;
    }
    
    
    /**
     * Prints a matrix to System.out in readable form.
     * 
     * @param mat The matrix to print
     */
    public static void printMatrix(double[][] mat) {
        if(mat == null || mat.length == 0 || mat[0].length == 0) {
            System.out.println("Null or empty matrix!");
            return;
        }
        
        for(int i = 0; i < mat[0].length; i++){
            System.out.print("----------");
        }
        System.out.println();
        
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                System.out.printf("%10.4f",mat[i][j]);
            }
            System.out.println();
        }
        
        for(int i = 0; i < mat[0].length; i++){
            System.out.print("----------");
        }
        System.out.println();
    }

}
