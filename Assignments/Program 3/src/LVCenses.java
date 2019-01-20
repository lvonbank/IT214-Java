// Levi VonBank

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class LVCenses
{
	public static void main(String[] args)
	{
		try
		{
			// Constructs ArrayLists which then receive the read data of each file
			ArrayList<String> cityCounty = new ArrayList<>(readFile("CityCountyData.txt"));
			ArrayList<String> cityPop = new ArrayList<>(readFile("CityPopulation.txt"));
			ArrayList<String> countyPop = new ArrayList<>(readFile("CountyPopulations.txt"));
			
			// Removes any data that can't be extracted
			removeData(countyPop, true, "CountyPopulations.txt");
			removeData(cityPop, true, "CityPopulation.txt");
			removeData(cityCounty, false, "CityCountyData.txt");
			
			// Sorts county array to insure alphabetical order
			Collections.sort(countyPop);
			
			// Fixes the format (Removes "County" from cityCounty)
			countyFixer(cityCounty);
			
			// Fixes the format (changes all Saint to St.)
			saintFixer(cityCounty);
			saintFixer(cityPop);
			saintFixer(countyPop);
			
			// Prints all necessary data
			printData(cityCounty, cityPop, countyPop);
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File[s] not found.");
		}
		catch (NoSuchElementException exception)
		{
			System.out.print("File[s] contents invalid.");
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	/**
	 * Prints all county data to console and out file
	 * @param cityCounty array containing cities and their counties
	 * @param cityPop array containing cities and their population
	 * @param countyPop array containing counties and their populations
	 */
	public static void printData(ArrayList<String> cityCounty, ArrayList<String> cityPop, ArrayList<String> countyPop) throws IOException
	{
		// Construct a PrintWriter for the output file
		PrintWriter out = new PrintWriter("LVProg3.txt");
		
		try
		{
			// Creates a title for the data
			String title = "County: County Population | | (Largest City: Population) | | Percent Rural";
			
			// Prints title on console & out file
			System.out.println(title + "\n");
			out.println(title);
			out.println();
			
			// Loops through all the data in countyPop until all county data is printed
			for (int index = 0; index < countyPop.size(); index++)
			{
				// Obtains county name & population
				String county = extractData(countyPop.get(index), false);   // County
				double countyPopulation = extractValue(countyPop.get(index));   // Pop
				
				// Revives a lists containing largest city and urban Population
				ArrayList<String> countyData = countyData(cityCounty, cityPop, county);
				
				// Extract largest city data contained in countyData array
				String largestCityPop = countyData.get(0);   // Data String
				String city = extractData(largestCityPop, false);   // City
				double cityPopulation = extractValue(largestCityPop);   //Pop
				
				// Extract urban population data contained in countyData array
				double urbanPopulation = Double.parseDouble(countyData.get(1));
				double rural = ruralPercentage(urbanPopulation, countyPopulation);
				
				// Creates string of county data for printing
				String countyPopStr = (county + ": " + insertCommas(countyPopulation));
				String largestCityPopStr = ("(" + city + ": " + insertCommas(cityPopulation) + ")");
				
				// Creates a output line of data
				String outputLine = String.format("%-31s%-33s%.1f%%", countyPopStr, largestCityPopStr, rural);
				
				// Prints county population to out file and console
				System.out.println(outputLine);
				out.println(outputLine);
				}
			}
		finally
		{
			out.close();   // Closes the output document
		}
	}
	
	/**
	 * Opens a file and reads a data set.
	 * @param filename the name of the file holding the data
	 * @return the data in the file
	 */
	public static ArrayList<String> readFile(String filename) throws IOException
	{
		// Creates a new file and scanner object
		File inFile = new File(filename);
		Scanner in = new Scanner(inFile);
		
		// Creates an array for file data
		ArrayList<String> data = new ArrayList<>();
		
		try
		{
			// Removes the information line at the top of the file
			if (in.hasNextLine()) {in.nextLine();}
			
			// Reads in all the files data
			while(in.hasNextLine())
			{
				String line = in.nextLine();
						
				if (!line.trim().equals("")) 
				{
					// Adds line of data to array
					data.add(line.trim());
				}
			}
			return data;
		}
		finally
		{
			in.close();
		}
	}
	
	/**
	 * Extracts a string from a give line (with any deliminator).
	 * @param line a line containing a string.
	 * @param reverse reads the string in reverse (extracts last item)
	 * @return the first or last item of a string
	 */
	public static String extractData(String line, boolean reverse) throws NoSuchElementException
	{
		// Extracts the name based on the given side
		String str = line;
		if (reverse)
		{
		    int index = line.length() - 1;
		    
		    // Locate the beginning of a string (from reverse)
		    while (isValidChar(line.charAt(index))) 
		    { 
		    	index--; 
		    	
		    	// Prevents IndexOutOfBoundsException
		    	if (index < 0)
		    	{
		    		throw new NoSuchElementException();
		    	}
		    }
		    
			// Obtains right side of a given line
			str = line.substring(index + 1).trim();
		}
		else
		{
			int index = 0;
			
			// Locate the end of a string
			while (isValidChar(line.charAt(index))) 
			{ 
				index++;
				
				// Prevents IndexOutOfBoundsException
				if (index > line.length() - 1)
		    	{
		    		throw new NoSuchElementException();
		    	}
			}
			
			// Obtains the left side of a given line
			str = line.substring(0, index).trim();	
		}
		return str;
	}
	
	/**
	 * Checks character to verify if it is a letter, space, period, or hyphen
	 * @param character a character interested in checking
	 * @return a boolean if character is a letter, space, period, or hyphen
	 */
	private static boolean isValidChar(Character character)
	{
		// Initializes boolean
		boolean valid = false;
		
		// Checks character to verify if it is a letter, space, period, or  '
		if (Character.isLetter(character) || character == ' ' || character == '.' || character == '\'')
		{
			valid = true;
		}
		return valid;
	}
	
	/**
	 * Extracts a value at the end from an input line.
	 * @param line a line containing string followed by a value
	 * @return the value associated with that string
	 */
	public static double extractValue(String line) throws NoSuchElementException
    {
	    int index = line.length()-1;
	    
	    // Locate the start of the digit (in reverse)
	    while (Character.isDigit(line.charAt(index)))
	    { 
	    	index--; 
	    	
	    	// Prevents IndexOutOfBoundsException
	    	if (index < 0)
	    	{
	    		throw new NoSuchElementException();
	    	}
	    }
	    
	    // Prevents empty strings
	    if (index == line.length()-1)
    	{
    		throw new NoSuchElementException();
    	}
	    
	    // Extract and convert the value
	    return Double.parseDouble(line.substring(index + 1).trim());
    }
	
	/**
	 * Determines the largest city in a selected county
	 * @param cityCountyList the list containing cites and their county
	 * @param cityPopList the list containing city population
	 * @param county the county of interest
	 * @return an ArrayList with the largest city and urban population
	 */
	public static ArrayList<String> countyData(ArrayList<String> cityCountyList, ArrayList<String> cityPopList, String county)
       {
		// Creates an array list for returning processed data
		ArrayList<String> countyData = new ArrayList<>();
		
		String largest = "NA,0";
		double urbanPopulation = 0;
		
		// Looks through cityCounty for cities associated with the county of interests
		for (int indexI = 0; indexI < cityCountyList.size(); indexI++)
	    {
			// Extracts data from current index1 of cityCounty
	    	String cityCountyElement = cityCountyList.get(indexI);
	    	String cityCounty_county = extractData(cityCountyElement, true);
	    	
	    	// Checks if currents data contains similar counties
	    	if (cityCounty_county.equalsIgnoreCase(county))
	    	{
	    		// Obtains city if county matched
	    		String cityCounty_city = extractData(cityCountyElement, false);
	    		
	    		// Looks through cityPop for city of interests
	    		for (int indexJ = 0; indexJ < cityPopList.size(); indexJ++)
	    	    {
	    			// Extracts data from current index2 of cityPop
	    			String cityPopElement = cityPopList.get(indexJ);
	    			String cityPop_city = extractData(cityPopElement, false);
	    	    	
	    			// Checks if currents data contains city of interests
	    			if (cityPop_city.equalsIgnoreCase(cityCounty_city))
	    	    	{
	    				// Obtains current city population
	    	    		double value = extractValue(cityPopElement);
	    	    		
	    	    		// Adds together all city populations of the same county
	    	    		urbanPopulation += value;
    	    			
	    	    		// Checks for largest value
	    	    		if (value > extractValue(largest))
	    	    		{      
	    	    			largest = cityPopElement;   
	    	    		}
	    	    	}
	    	    }
	    	}
	    }
		// Adds the final concluded data to an array list
		countyData.add(largest);
		countyData.add(Double.toString(urbanPopulation));
		
		return countyData;
	}
    
	/**
	 * Fixes the the county inconsistency in cityCounty list
	 * @param cityCounty the list containing city and county names
	 */
	public static void countyFixer(ArrayList<String> cityCounty)
	{
		// Removes the word Country from a string in cityCounty if present.
		for (int index = 0; index < cityCounty.size(); index++)
		{
			String element = cityCounty.get(index);
			if (element.contains("County"))
			{
				element = element.replace("County", "").trim();
			}
			cityCounty.set(index, element);
		}
	}
	
	/**
	 * Fixes the the St. inconsistency in a given list
	 * @param list the list interested in fixing
	 */
	public static void saintFixer(ArrayList<String> list)
	{
		// Changes the word Saint to St.
		for (int index = 0; index < list.size(); index++)
		{
			String element = list.get(index);
			if (element.contains("Saint"))
			{
				element = element.replace("Saint", "St.").trim();
			}
			list.set(index, element);
		}
	}
	
	/**
	 * Removes data from a list that can't be extracted
	 * @param list the list interested in checking
	 * @param values true if lines contains numbers or false otherwise
	 * @param fileName lists name
	 */
	public static void removeData(ArrayList<String> list, boolean values, String fileName)
	{
		// Fixes line numbering
		final int OFFSET = 2;
		
		// Cycles through the provided list
		for (int index = 0; index < list.size(); index++)
		{
			String element = list.get(index);
			try
			{
				if (values)
				{
					// Checks strings associated with values
					extractData(element, false);
				    extractValue(element);
				}
				else
				{
					// Checks strings associated to strings
					extractData(element, false);
					extractData(element, true);
				}
				
			}
			catch (NoSuchElementException exception)
			{
				// Prints file name associated with error
				System.out.println("Error: " + fileName);
				
				// Removes bad data and prints error.
				System.out.println("Line " + (index + OFFSET) + ": " + list.remove(index) + "\n");
			}
		}
	}
	
	/**
	 * Calculates the rural percentage
	 * @param urbanPopulation the county's city population total
	 * @param countyPopulation the county's total population
	 * @return the rural percentage
	 */
	public static double ruralPercentage(double urbanPopulation, double countyPopulation)
	{
		double ruralPercentage = 0;
		if (countyPopulation > 0)
		{
			double ruralPop = (countyPopulation-urbanPopulation);
			ruralPercentage = (ruralPop/countyPopulation)*100;
		}
		return ruralPercentage;
	}
	
	/**
	 * Inserts comma[s] into a set of numbers
	 * @param num the set of numbers to add commas
	 * @return a string containing comma[s] separators
	 */
	public static String insertCommas(double num)
	{
		String str = Double.toString(num);
		
		// Removes unnecessary float
		if (str.contains("."))
		{
			str = str.substring(0, str.indexOf("."));
		}

	    return addCommas(str);
	}
	
	/**
	 * Inserts comma[s] into a string of numbers
	 * @param str the string of numbers to add commas
	 * @return a string containing comma[s] separators
	 */
	private static String addCommas(String str)
	{
	    if(str.length() < 4)
	    {
	        return str;
	    }

	    return addCommas(str.substring(0, str.length() - 3)) +  "," + str.substring(str.length() - 3, str.length());
	}
}