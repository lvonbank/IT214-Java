import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
   This program computes Fibonacci numbers using a recursive method.
*/ 
public class RecursiveFib
{  
   public static void main(String[] args) throws FileNotFoundException
   {  	
	        // Construct a PrintWriter for the output file
		PrintWriter out = new PrintWriter("RuntimeFib.csv");
		
		// Writes the header
		out.println("Fibonacci Runtime");
		out.println("n,time(ms)");
		
		for (int n = 0; n < 46; n++)
		{
			// Use stopwatch to time selection sort
		        StopWatch timer = new StopWatch();
			
			timer.start(); // Starts timer
			
			// Generates fibonacci number
			long f = fib(n);
			System.out.println("fib(" + n + ") = " + f);
			
			timer.stop(); // Stops timer
			
			// Prints n and time elapsed
			out.println(n + "," + timer.getElapsedTime());
		}
		
		// Closes data file
		out.close();
   }

   /**
      Computes a Fibonacci number.
      @param n an integer
      @return the nth Fibonacci number
   */
   public static long fib(int n)
   {  
      if (n <= 2) { return 1; }
      else { return fib(n - 1) + fib(n - 2); }
   }
}
