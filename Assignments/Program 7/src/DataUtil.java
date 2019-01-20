/**
 * @author Levi VonBank
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DataUtil 
{
	/**
	 * Opens a file and reads a data set.
	 * @return appointments created from the data in the file
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String> getData() throws FileNotFoundException
	{
		// Creates a new file and scanner object
		File inFile = new File("dates.txt");
		Scanner in = new Scanner(inFile);
		
		// Creates an array for file data
		ArrayList<String> data = new ArrayList<String>();
		
		try
		{
			// Reads in all the files data
			while(in.hasNextLine())
			{
				String line = in.nextLine().trim();
				
				if (!(line.isEmpty()))
				{
					data.add(line);
				}
			}	
		}
		finally
		{
			in.close();
		}
		
		return data;
	}
	
	/**
	 * Opens a file and writes data.
	 * @param appointments to be write to a data file
	 */
	public static <T> void putData(ArrayList<T> list) throws IOException
	{
		// Construct a PrintWriter for the output file
		PrintWriter out = new PrintWriter("dates.txt");
		
		try
		{
			// Loops through all the data appointment
			for (int index = 0; index < list.size(); index++)
			{
				out.println(list.get(index).toString());
			}
		}
		finally
		{
			out.close();   // Closes the output document
		}
	}
	
	public static Appointment constructAppointment(String occurrence, Date date_time, String description)
	{
		Appointment app = null;
		
		if (occurrence.equalsIgnoreCase("OneTime"))
		{
			Appointment event = new OneTime(date_time, description);
			app = event;
		}
		else if (occurrence.equalsIgnoreCase("Daily"))
		{
			Appointment event = new Daily(date_time, description);
			app = event;
		}
		else if (occurrence.equalsIgnoreCase("Monthly"))
		{
			Appointment event = new Monthly(date_time, description);
			app = event;
		}
		
		return app;
	}
}