// Levi VonBank

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This program reads a file whose lines contain dates,
 * high temperatures, low temperatures:
 * date 1, high, low
 * date 2, high, low
 * ...
 * The values are separated by commas.
 * The program writes a file in which the dates are left-aligned and the
 * temperatures are right-aligned. The last line has the average high temperature
 */
public class Temps
{
	public static void main(String[] args)throws FileNotFoundException
	{
		// Prompt for the input and output file names.
	    Scanner console = new Scanner(System.in);
	    System.out.print("Input file: ");
	    String inputFileName = console.next();
	    System.out.print("Output file: ");
	    String outputFileName = console.next();

		// Open the input and output files.
	    File inputFile = new File(inputFileName);
	    Scanner in = new Scanner(inputFile);
	    PrintWriter out = new PrintWriter(outputFileName);

		// Set the totals for averaging to zero
		double totalHigh = 0;
		double totalLow = 0;

		// Read the input and write the output.
		while (in.hasNextLine())
		{
			String line = in.nextLine();

			// Make sure there is a comma in the input line, otherwise skip the line.
			if (line.contains(","))
			{
				// Split the record at the commas
			    String[] parts = line.split(",");
			    
			    // Extract the three data fields.
			    String date = parts[0];
			    int high = Integer.parseInt(parts[1]);
			    int low = Integer.parseInt(parts[2]);
		      
			    // Increment the total of high temperatures
			    totalHigh = totalHigh + high;
			    totalLow = totalLow + low;
			
			    // Write the output.
			    out.printf("%10s %5s %5s", date, parts[1], parts[2]);
			    out.println();
			}
		}
		
		// Write the average high and low.
		out.println();
		out.printf("%-28s%5.2f", "Average high temperature:", (totalHigh)/365.00);
		out.println();
		out.printf("%-28s%5.2f", "Average low temperature:", (totalLow)/365.00);

		// Close the files.
		in.close();
		out.close();
	}
}