/*
 * part1: 0(1) It consistently runs no more than five times.
 * part2: 0(n log(n)) It consistently divides the data set in half, with a for loop running n times.
 * part3: 0(2^n) It grows exponentially. As n gets bigger the time taken is increased.
 * part4: 0(1) No matter what is given it makes only 3 calls counting the initial value.
 * part5: 0(n) The statements in the iterations are constant. Making the loop run n times.
 */

//importjava.util.Random; 
//importjava.util.ArrayList; 
import java.util.*; 

public class Quiz4 

{ 
	public static int total=0; //Use global total for counting 
 
	public static void main(String[] args) 
	{ 
		int n=50; 

		// create an array and fill it with random integers 
		int[] randomRay = new int[n];
		
		Random rand = new Random();
		for (int i = 0; i < n; i++) { 
			randomRay[i] = rand.nextInt(n*2); 
		} 
 
		// create an array list and fill it with random integers 
 
		n=6; //new value for n 
 
		ArrayList<Integer> arrayRandom = new ArrayList<Integer>(n);
 
		for (int i=0; i<n; i++) { 
			arrayRandom.add(rand.nextInt(n*2)); 
		} 
 
		// make a sorted copy of arrayRandom 
		ArrayList sortedRandom = new ArrayList(arrayRandom);
		Collections.sort(sortedRandom);
 
		// test for deep copy 
		// sortedRandom.set(s,88); 
		// System.out.println(arrayRandom.get(s)); 
		// System.out.println(sortedRandom.get(s)); 
 
		// just checking values are in order 
		// for (int i=0; i<10; i++) { 
		// 	System.out.println(sortedRandom.get(i)); 
	 	//} 
 
		total=0; // reset total to zero 
		System.out.println ("--------------------------"); 
		part1(randomRay); 
		System.out.println(); 
		System.out.println("total for part1 is " + total); 
		System.out.println("--------------------------");

		total=0; // reset total to zero 
		part2(arrayRandom, sortedRandom); 
		System.out.println();
		System.out.println("total for part2 is "+total);
		System.out.println ("--------------------------");

		total=0; // reset total to zero 
		n=3; //new value for n 
		part3(n,"A","B","C"); 
		System.out.println(); 
		System.out.println("total for part3 is "+total); 
		System.out.println ("--------------------------"); 
 
		total=0; // reset total to zero 
		n=5; // new value for n 
		part4(n,"A","B","C"); 
		System.out.println(); 
		System.out.println("total for part4 is "+total); 
		System.out.println("--------------------------"); 

		total=0; // reset total to zero 
		n=5; // new value for n 
		part5(randomRay); 
		System.out.println(); 
		System.out.println("total for part5 is "+total); 
		System.out.println("--------------------------"); 
 
 	} // ========== end of "main" ========== 
 
 	public static void part1(int[] values) 
 
 	{ 
 		int items=5; 
 		int stopper=values.length; 
 
 		if (stopper>5) 
 			stopper=5; 
 
 		for (int i = 0; i < stopper; i++) { 
 			System.out.print(values[i] +" "); 
 			total++; 
 		} 
 		System.out.println(); 
 	} 

	public static void part2(ArrayList<Integer> unsorted,ArrayList<Integer> sorted) 
 
 	{ 
 		int low = 0; 
 		int high = unsorted.size() - 1; 
 
		for (int i=0;i<unsorted.size();i++) 
		{ low = 0; 
		  high = unsorted.size(); 
 
		  while (low <= high) { 
			  total++; 
			  int mid = (low + high) / 2; 
			  int midVal = sorted.get(mid); 
 
			  if (unsorted.get(i)< midVal) { 
				  high = mid - 1; 
			  } 
			  else if (unsorted.get(i) > midVal) { 
				  low = mid + 1; 
			  } 
			  else { 
				  System.out.println("One instance of "+unsorted.get(i)+" at sorted location "+mid); 
				  break; 
			  } 
		  } // end while 
	   } // end for 
	} 
	
	public static void part3(int n, String start, String auxiliary, String end) 
	
	{ 
		total++; 
		if (n == 1) { 
			System.out.println("Move from "+start + " to " + end); 
		} 
		else { 
			part3(n - 1, start, end, auxiliary); 
			System.out.println("Move from "+start + " to " + end); 
			part3(n - 1, auxiliary, start, end); 
		} 
	} 
	
	public static void part4(int n, String start, String auxiliary, String end) 
	
	{ 
		total++; 
		if (n == 1) { 
			System.out.println("Move from "+start + " to " + end); 
		} 
		else { 
			part4(1, start, end, auxiliary); 
			System.out.println("Move from "+start + " to " + end); 
			part4(1, auxiliary, start, end); 
		} 
	} 
	
	public static void part5(int[] values) 
	
	{ 
		int items=5; 
		int dex=0; 
		int sum=0; 
		
		while (dex<values.length) { 
			total++; 
			sum = sum+values[dex]; 
			dex = dex+2; 
		} 
		System.out.println("The sum is "+sum); 
	}
}