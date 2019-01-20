// Levi VonBank

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Prog4
{
	public static void main(String[] args)
	{
		try
		{
			// Construct a PrintWriter for the output file
			PrintWriter out = new PrintWriter("LVmyTri.txt");
			
			// Constructs ArrayLists which then receive the read data of each file
			ArrayList<String> triList = new ArrayList<>(readFile("triInput.txt"));
			
			// Builds and stores an ArrayLists of triangles
			ArrayList<Triangle> myTri = buildTri(triList, out);
			
			try
			{
				// Loops through all triangles
				for (int index = 0; index < myTri.size(); index++)
				{
					// Obtains string version of triangle
					String name = myTri.get(index).printName() + "\r\n";
					String listVertices = myTri.get(index).listVertices()+ "\r\n";
					String listSides = myTri.get(index).listSides();
					
					// Prints data
					System.out.println(name + listVertices + listSides + "\n");
					out.println(name + listVertices + listSides + "\r\n");
				}
			}
			finally
			{
				out.close();   // Closes the output document
			}
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
	 * Builds triangles for myTri
	 * @param triList a list of data strings
	 * @param out a PrintWriter for error messages 
	 * @return myTri an arrayList containing triangles
	 */
	public static ArrayList<Triangle> buildTri(ArrayList<String> triList, PrintWriter out)
	{
		// Contains an ArrayLists of triangles
		ArrayList<Triangle> myTri = new ArrayList<>();
					
		// Loops through all the data
		for (int index = 0; index < triList.size(); index++)
		{
			// Splits data string into parts (storing in ArrayList)
			ArrayList<String> lineList = splitData(triList.get(index), ' ');
			
			try
			{
				// Verifies that their is sufficient data
				if (lineList.size() == 7)
				{
					// Obtains triangles name
					String name = lineList.get(0);
					
					// Creates all three points
					Point p1 = new Point(toDouble(lineList.get(1)), toDouble(lineList.get(2)));
					Point p2 = new Point(toDouble(lineList.get(3)), toDouble(lineList.get(4)));
					Point p3 = new Point(toDouble(lineList.get(5)), toDouble(lineList.get(6)));
					
					// Verifies that the three points arn't duplicates or collinear
					if (p1.equals(p3) || p2.equals(p3) || p2.equals(p1))
					{
						throw new NoSuchElementException("Duplicate points");
					}
					else if (slope(p1, p2) == slope(p2, p3))
					{
						throw new NoSuchElementException("Collinear points");
					}
					else
					{
						// Creates triangle with points (with name)
						Triangle newTri = new Triangle(name, p1, p2, p3);
						myTri.add(newTri);   // Add triangle to list
					}
				}
				else
				{
					throw new NoSuchElementException("Invalid/Missing data");
				}
			}
			catch (NoSuchElementException | NumberFormatException exception)
			{
				// Creates an error message
				String error = "Error line " + (index + 1) + ": " + triList.get(index) 
				               + "\r\n" + exception.getMessage() + "\r\n";
				
				// Prints message to out file and console
				System.out.println(error);
				out.println(error);
			}
		}
		return myTri;
	}
	
	/**
	 * Calculates the slope for two points
	 * @param point1 a point
	 * @param point2 a point
	 * @return slope
	 */
	public static double slope(Point point1, Point point2)
	{
		// Calculates using "rise over run"
		double calc1 = (point2.getY() - point1.getY());
		double calc2 = (point2.getX() - point1.getX());
		double slope = calc1 / calc2;
		
		return slope;
	}
	
	/**
	 * Converts string to a double
	 * @param string thats a number
	 * @return the number as a double
	 */
	public static double toDouble(String number)
	{
		return Double.parseDouble(number);
	}
	
	/**
	 * Splits data into an arrayList
	 * @param string the string to split
	 * @param token the deliminator
	 * @return the data in an arrayList
	 */
	public static ArrayList<String> splitData(String string, Character token)
	{
		// Stores split data 
		ArrayList<String> dataList = new ArrayList<>();
		
		string.trim();   // Cleans up string
		
		// Creates a string version of the token
		String tokenString = "" + token;
		
		// Verifies that string contains token
		if (string.contains(tokenString))
		{
			// Stores positions of token
			ArrayList<Integer> tokenPos = new ArrayList<>();
			
			// Creates a starting and ending position
			string = token + string + token;
			
			// Loops string finding all matching positions
			for (int index = 0; index < string.length(); index++)
			{
				if (string.charAt(index) == token)
				{
					tokenPos.add(index);
				}
			}
			
			// Loops string dividing it based on tokenPos
			for (int index = 0; index < tokenPos.size() - 1; index++)
			{
				String data = string.substring(tokenPos.get(index) + 1, tokenPos.get(index + 1));
				
				if (!data.equals(""))
				{
					dataList.add(data.trim());
				}
			}
		}
		return dataList;
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
}