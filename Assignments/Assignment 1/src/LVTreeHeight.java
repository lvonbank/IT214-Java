/*
 * Levi VonBank
 * Group: Joshua Thurston, Jordan Mullan, Matt Miller, Ali Niitsu
 */

import java.util.Scanner;

/**
 * This program calculates the height of a tree 
 * using its shadow and the sun's angle
 */
public class LVTreeHeight
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		
		// Read shadow length in feet
		System.out.print("Enter shadow length in feet: ");
		int shadowLen = in.nextInt();
		
		// Read Sun angle in degrees
		System.out.print("Enter the Sun angle in degrees: ");
		double sunAngle = in.nextDouble();
	
		// Converting the sunangle from degrees to radians as math.tan reads in radians.
		double treeHeight = shadowLen * Math.tan(Math.toRadians(sunAngle));
		
		System.out.printf("Height of Tree: %5.2f", treeHeight);
		System.out.println();
	}
}