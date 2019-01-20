/*
 * first( O(n) )
 * second( O(n) )
 * third( O(n^2) )
 * fourth( O(n) )
 * fifth( O(n log(n)) )
 */

import java.util.Random;

public class Quiz2
{
	public static void main(String[] args)
	{
		int n = 50;
		int[] randomRay = new int[n];

		Random rand = new Random();
		for(int i =0; i < n; i++){
			randomRay[i] = rand.nextInt(100);
		}

		first(randomRay);
		second(randomRay);
		third(randomRay);
		fourth(randomRay);
		fifth(randomRay);

	}
	
	public static void first(int[] theArray)
	{
		int total = 0;

		for(int item : theArray){
			
			total++;
		}
		
		for(int item : theArray){
			total++;
		}

		System.out.println("First total is: "+total);
	}
	
	public static void second(int[] theArray)
	{
		int total = 0;

		total++;

		int middlelndex = theArray.length / 2;
		int index = 0;
		
		while(index < middlelndex){
			index++;
			total++;
		}

		for (int x = 0; x < (theArray.length*2); x++){
			total++;
		}

		System.out.println("Secondtotalis: "+total);
	}

	public static void third (int[] arrayOfNumbers)
	{
		int total = 0;

		for (int number : arrayOfNumbers ){
			total++; 
		}

		for (int firstNumber : arrayOfNumbers){
			total ++;
			for (int secondNumber : arrayOfNumbers){
				total ++;
			}
		}

		System.out.println( "Third total is: " +total);
	}
	
	public static void fourth (int[] arrayOfNumbers)
	{
		int total = 0; 
		int dex = 1;

		for(int number : arrayOfNumbers){
			total ++; 
		}

		while (dex<arrayOfNumbers.length){
			total++; 
			dex=dex*2;
		}
		
		System.out.println ("Fourth total is: " +total);
	}
	
	public static void fifth (int[] arrayOfNumbers)
	{
		int total = 0; 

		for (int number : arrayOfNumbers) {
			total ++; 
			int dex=1;
			
			while (dex<arrayOfNumbers.length){
				total ++;
				dex=2*dex; 
			}
		}
		
		System.out.println ("Fifth total is: " +total );
	}

}