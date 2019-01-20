/* 
 * Fibonacci/part1 has a big-O of 2^n. Each call starts two 
 * additional calls, so there is a repeated doubling that is 
 * dependent on the value on n.
 * 
 * part2 has a big-O of n. There are two loops. They are not 
 * nested, and each goes through each element in the list (2*n). 
 * The constant is disregarded, so O(n)
 * 
 * part3 is a binary search, The list is repeatedly cut in 
 * half until either the target value is found or there is 
 * nothing left in the list. O(log n)
 */

//import java.util.Random;
//import java.util.ArrayList;
import java.util.*;

public class Quiz3
    
{
	
   public static int total=0;  // Use global total for counting
	
   public static void main(String[] args)
   {
	int n=8;
	
	total=0;
	System.out.println("Series number: "+n);
	System.out.println("Fibonacci number: " + part1(n));
	System.out.println("Total count is: " + total);
	System.out.println();
	
	n=50; // new value for n

	// create an array and fill it with random integers
	int[] randomRay = new int[n];
	
	Random rand = new Random();
	for (int i = 0; i < n; i++) {
		randomRay[i] = rand.nextInt(n*2);
	}
	
	total=0; // reset total to zero
	
	part2(randomRay);
	
	System.out.println("Total count for part2 is: " + total);
	System.out.println();
	
	// create an array list, fill it with random integers, and sort it
	ArrayList<Integer> arrayRandom = new ArrayList<Integer>(n);

	for (int i=0; i<n; i++) {
		arrayRandom.add(rand.nextInt(n*2));
	}
	
	// insert for testing
	// int lookFor = 17;
	// arrayRandom.add(42,lookFor);
	
	Collections.sort(arrayRandom);
	
	// just checking values are in order
	// for (int i=0; i<10; i++) {
	// 	System.out.println(arrayRandom.get(i));
	

	int lookFor=rand.nextInt(n*2); // generate a number to look for
	System.out.println("Searching for "+lookFor);
	
	total=0; // reset total to zero
	
	// look for the value in the arraylist
	int spot = part3(arrayRandom, lookFor);
	
	if (spot==-1)
		System.out.println("Target "+lookFor+" not found");
	else
		System.out.println("Target "+arrayRandom.get(spot)+" found at location "+spot);

	System.out.println("Total count for part3 is "+total);
   }

   public static int part1(int number)
   // What is the nth number in the Fibonacci series
   // 1 1 2 3 5 8 13 21 … (each is the sum of the previous two)

   {
	total++;
	if (number <= 1)
		return number;
	else
		return part1(number - 2) + part1(number - 1);
   }
   
   public static void part2(int[] values)

   {
	int evenSum = 0;
	int oddSum = 0;
	   
	for (int i = 0; i < values.length; i++) {
		if (values[i] % 2 == 0)
			evenSum=evenSum+values[i];
		total++;
	}
	
	for (int i = 0; i < values.length; i++) {
		if (values[i] % 2 == 1)
			oddSum=oddSum+values[i];
		total++;
	}
	System.out.println("n = "+values.length+" and sums are "+evenSum+" and "+oddSum);
	
   }

   public static int part3(ArrayList<Integer> numbers,int target)
   
   {
	int low = 0;
        int high = numbers.size() - 1;

        while (low <= high) {
	    total++;
            int mid = (low + high) / 2;
            int midVal = numbers.get(mid);

            if (target < midVal) {
                high = mid - 1;
            }
            else if (target > midVal) {
                low = mid + 1;
            } 
	    else {
		return mid;
            }
        }
	
        return -1;
   }
   
}