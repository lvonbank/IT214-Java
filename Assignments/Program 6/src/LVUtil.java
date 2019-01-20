// Levi VonBank

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LVUtil
{
	/**
	 * Opens a file and reads the data.
	 * @param filename the name of the file holding the data
	 * @return the data in the file
	 */
	public static ArrayList<String> readFile(String filename) throws IOException
	{
		// Creates a new file and scanner object
		File inFile = new File(filename);
		Scanner in = new Scanner(inFile);
		
        // read file line by line
		Scanner line = null;
        
        // Creates an array for file data
		ArrayList<String> fileData = new ArrayList<>();
		
		try
		{	
			// Removes first line
			if (in.hasNextLine()) {in.nextLine();}
						
			while (in.hasNextLine())
			{	
				// Scans first item of each line
				line = new Scanner(in.nextLine());
				line.useDelimiter(",");

				// Obtains item
				String data = line.next().trim();
				
				// Excludes empty lines
				if (!data.equals("")) 
				{
					// Adds item
					fileData.add(data);
				}
			}
		}
		finally
		{
			//close reader
	        in.close();
		}
		return fileData;
	}
	
	/**
	 * Builds a legal United States phone number
	 * @return the last 7 digits of a phone number 
	 */
    public static String randomDigits()
    {
        String digits;
        int max = 9;
        int min = 2;

        //first number cannot be zero or one
        digits = Integer.toString((int)(Math.random() * ((max + 1) - min) + min));
        
        // Appends 6 random numbers to the end 0-9
        for (int index = 0; index < 6; index++)
        {
            String addNumber = Integer.toString((int)(Math.random() * 10));
            digits += addNumber;
        }
        
        return digits;
    }
    
    /**
     * Allows for a randomly drawn item
     * @param list the list to draw from
     * @return the random item
     */
    public static String randomSelection(ArrayList<String> list)
    {
    	int index = (int)(Math.random()*list.size());
        return list.get(index);
    }
    
    /**
     * This method checks if a String contains only numbers
     * @param a string to inspect
     * @return a boolean based on results
     */
    public static boolean isNumbers(String str) 
    {
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
        {
        	return false;
        }
        
        for (int i = 0; i < str.length(); i++)
        {
            //If a non-digit character found return false.
            if (!Character.isDigit(str.charAt(i)))
            {
            	return false;
            }
        }
        
        return true;
    }
    
    /**
     * This method checks if a String contains only letters
     * @param a string to inspect
     * @return a boolean based on results
     */
    public static boolean isLetter(String str) 
    {
        //It can't contain only letters if it's null or empty...
        if (str == null || str.length() == 0)
        {
        	return false;
        }
        
        for (int i = 0; i < str.length(); i++)
        {
            //If a non-digit character found return false.
            if (!Character.isLetter(str.charAt(i)))
            {
            	return false;
            }
        }
        
        return true;
    }
    
    /**
     * Removes phone number separators.
     * @param item an item containing a name, followed by a number
     * @return string with phone number separators removed
     */
    public static String removeSeparators(String item)
    {  
    	String newItem = "";
    	for (int i = 0; i < item.length(); i++)
    	{ 
    		if (!(item.charAt(i) == '(' || item.charAt(i) == ')' || item.charAt(i) == '-' || item.charAt(i) == '.'))
    		{
    			newItem += item.charAt(i);
    		}
    	}
    	return newItem;
    }

    /**
     * Obtains a legal integer from user
     * @param prompt the message to display
     * @return the integer value
     */
    public static int getInt(String prompt)
    {
    	Scanner in = new Scanner(System.in);
    	while (true)
    	{
    		// Print message
    		System.out.print(prompt);
    		
    		// Loops until user inputs a integer
    		String input = in.next();
    		try
    		{
    			return Integer.parseInt(input);
    		}
    		catch (NumberFormatException exception)
    		{
    			System.out.println("Invalid input\n");
    		}
    	}
    }
}
