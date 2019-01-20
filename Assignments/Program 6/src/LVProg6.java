// Levi VonBank

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LVProg6
{
	public static void main(String[] args)
	{
		try
		{
			// Constructs ArrayLists which then receive data
			ArrayList<String> areaCodes = new ArrayList<>(LVUtil.readFile("AreaCodes.csv"));
			ArrayList<String> firstNames = new ArrayList<>(LVUtil.readFile("FirstNames.csv"));
			ArrayList<String> lastNames = new ArrayList<>(LVUtil.readFile("LastNames.csv"));
			
			telephoneLookup(personGenerator(areaCodes, firstNames, lastNames));
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
	 * Generates a list of person objects
	 * @param areaCodesList list of area codes
	 * @param firstNamesList a list of first names
	 * @param lastNamesList a list of last names
	 * @return a list of person objects
	 */
	public static ArrayList<LVPerson> personGenerator(ArrayList<String> areaCodesList, 
													  ArrayList<String> firstNamesList, 
													  ArrayList<String> lastNamesList)
	{
		// Gets an integer <= 0 from the user
		int amount;
		do
		{
			// Amount of persons to generate
			amount = LVUtil.getInt("Enter the number of people (to generate): ");
    		
			if  (amount < 0)
    		{
    			System.out.println("Invalid input\n");
    		}
		}
		while (amount < 0);
		
		// Construct a randomized array
		ArrayList<LVPerson> people = new ArrayList<>();
		for(int i = 0; i < amount; i++)
		{
			// Makes sure area code string is numbers
			String areaCode;
			do
			{
				areaCode = LVUtil.randomSelection(areaCodesList);
			} 
			while (!(LVUtil.isNumbers(areaCode)));
			
			// Randomly selects data from read files
			String firstName = (LVUtil.randomSelection(firstNamesList));
			String lastName = (LVUtil.randomSelection(lastNamesList));
			String phoneNumber = (areaCode + LVUtil.randomDigits());
			
			// Constructs person
			people.add(new LVPerson(firstName, lastName, phoneNumber));
		}
		
		if (!people.isEmpty())
		{
			// Sorts people objects
			Collections.sort(people);
			
			// Prints first and last person
			System.out.println("[First] " + people.get(0));
			System.out.println("[Last] " + people.get(people.size()-1));
			
			/* Prints the people list
			for (LVPerson person : people)
			{
				System.out.println(person);
			}
			*/
		}
		return people;
	}
	
	/**
	 * Creates a user interface to search person list
	 * @param peopleList a list of person objects
	 */
	public static void telephoneLookup(ArrayList<LVPerson> peopleList)
	{
		// Constructs a scanner object
		Scanner in = new Scanner(System.in);
		
		while (true)
		{
			try
			{
				// Constructs a search menu
				LVMenu searchMenu = new LVMenu();
				searchMenu.addOption("Full Name");
				searchMenu.addOption("First Name");
				searchMenu.addOption("Last Name");
				searchMenu.addOption("Phone Number");
				searchMenu.addOption("Exit");
			    int input = searchMenu.getInput();
			    System.out.println();
			    
			    // Exits loop if option 5/Exit is entered
			    if (input == 5) { break; }
				
				LVPerson targetPerson = new LVPerson(); // Person to search
				
				// Determines how to handle menu option...
				if (input == 1 || input == 2) // First Name
				{
					System.out.print("First Name: ");
					String fname = in.next().trim();
					
					letterTest(fname);
					targetPerson.setFirstName(fname);
				}
				if (input == 1 || input == 3) // Last Name
				{
					System.out.print("Last Name: ");
					String lname = in.next().trim();
					
					letterTest(lname);
					targetPerson.setLastName(lname);
				}
				if (input == 4) // Phone Number
				{
					System.out.print("Phone Number: ");
					String pnumber = LVUtil.removeSeparators(in.next().trim());
					
					numberTest(pnumber);
					targetPerson.setPhoneNumber(pnumber);
				}
				
				// Use timer to record search time
			    LVTimer timer = new LVTimer();
			    
			    // Constructs a comparator
			    PersonComparator comparer = new PersonComparator(input);
			    
			    // Sorts people objects
				Collections.sort(peopleList, comparer);
				
				// Times the run on a binary search
				timer.start();
				int pos = Collections.binarySearch(peopleList, targetPerson, comparer);
				timer.stop();
				
	            if (pos < 0) // Can't be found
	            {
	            	System.out.println("Nothing found by that " + searchMenu.getOption(input));
	            }
	            else // found
	            {
	            	int left = pos;
	            	int right = pos;
	            	
	            	// Checks if other matches exist to the left
	                while (left - 1 >= 0 && comparer.compare(peopleList.get(left-1), targetPerson) == 0) 
	                {
	                	left--;
	                }
	                
	                // Checks if other matches exist to the right
	                while (right + 1 < peopleList.size() && comparer.compare(peopleList.get(right+1), targetPerson) == 0)
	                {
	                	right++;
	                }
	            	
	                // Prints results
	                for (int i = left; i <= right; i++)
	                {
	                	System.out.println(peopleList.get(i));
	                }
	            }
	            // Prints time spent on search
	            System.out.println("Elapsed time: " + timer.getElapsedTime() + " milliseconds");
			}
			catch (NoSuchElementException exception)
			{
				System.out.println(exception.getMessage());
			}
		}
		in.close();
	}
	
	/*
	 * Tests whether a string contains only letters
	 * @param item a string to inspect
	 */
	private static void letterTest(String item)
	{
		if (!(LVUtil.isLetter(item)))
		{
			throw new NoSuchElementException("Only letters are accepted");
		}
	}
	
	/*
	 * Tests whether a string contains only numbers
	 * @param item a string to inspect
	 */
	private static void numberTest(String item)
	{
		if (!(LVUtil.isNumbers(item)))
		{
			throw new NoSuchElementException("Only numbers are accepted");
		}
	}
}

/**
 * A comparator to compare person objects by 
 * different attributes depending on flag
 */
class PersonComparator implements Comparator<LVPerson>
{
	int flag;
	
	/**
	 * Constructor to compare person objects
	 * @param flag the attributes to compare by
	 * 		1 = Full name
	 * 		2 = First Name
	 * 		3 = Last Name
	 * 		4 = Phone Number
	 */
	public PersonComparator(int flag)
	{
		this.flag = flag;
	}
	
	public int compare(LVPerson a, LVPerson b)
	{		
		int result = 0;
		
		if (flag == 1) // Full Name
		{
			result = a.getLastName().compareToIgnoreCase(b.getLastName());
			if (result == 0)
			{
				result = a.getFirstName().compareToIgnoreCase(b.getFirstName());
			}
		}
		else if (flag == 2) // First Name
		{
			result = a.getFirstName().compareToIgnoreCase(b.getFirstName());
		}
		else if (flag == 3) // Last Name
		{
			result = a.getLastName().compareToIgnoreCase(b.getLastName());
		}
		else if (flag == 4) // Phone Number
		{
			result = a.getPhoneNumber().compareToIgnoreCase(b.getPhoneNumber());
		}
		return result;
	}
}