// Levi VonBank

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Prog5
{
	public static void main(String[] args)
	{
		try
		{
			// Construct a PrintWriter for the output file
			PrintWriter out = new PrintWriter("LVmyPoly.txt");
			
			// Constructs ArrayLists which then receive the read data of each file
			ArrayList<String> polyList = new ArrayList<>(readFile("polyInput.txt"));
			
			// Builds and stores an ArrayLists of triangles
			ArrayList<Poly> myPoly = buildPoly(polyList, out);
			
			try
			{
				// Loops through all polygons
				for (int index = 0; index < myPoly.size(); index++)
				{
					// Obtains string version of triangle
					String description = myPoly.get(index).toString() + "\r\n";
					
					// Prints data
					System.out.println(description);
					out.println(description);
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
	 * Builds polygons for myPoly
	 * @param polyList a list of data strings
	 * @param out a PrintWriter for error messages 
	 * @return myPoly an arrayList containing polygons
	 */
	public static ArrayList<Poly> buildPoly(ArrayList<String> polyList, PrintWriter out)
	{
		// Creates an ArrayLists to store polygons
		ArrayList<Poly> myPoly = new ArrayList<>();
					
		// Loops through all the data
		for (int index = 0; index < polyList.size(); index++)
		{
			
			// Splits data string into parts (storing in ArrayList)
			ArrayList<String> data = splitData(polyList.get(index), ' ');
			
			try
			{
				// Verifies that their is sufficient data				
				if (data.size() == 7)
				{
					buildTri(data, myPoly);
				}
				else if (data.size() == 9)
				{
					buildQuad(data, myPoly);
				}
				else
				{
					throw new NoSuchElementException("Invalid/Missing data");
				}
			}
			catch (NoSuchElementException | NumberFormatException exception)
			{
				// Creates an error message
				String error = "Error line " + (index + 1) + ": " + polyList.get(index) 
				               + "\r\n" + exception.getMessage() + "\r\n";
				
				// Prints message to out file and console
				System.out.println(error);
				out.println(error);
			}
		}
		return myPoly;
	}
	
	/**
	 * Builds a Triangle
	 * @param data an array list containing a name and 6 values
	 * @param myPoly an array list to add the constructed triangle
	 */
	public static void buildTri(ArrayList<String> data, ArrayList<Poly> myPoly)
	{
		// Obtains triangles name
		String name = data.get(0);
		
		// Creates all three points
		Point p1 = new Point(toDouble(data.get(1)), toDouble(data.get(2)));
		Point p2 = new Point(toDouble(data.get(3)), toDouble(data.get(4)));
		Point p3 = new Point(toDouble(data.get(5)), toDouble(data.get(6)));
		
		// Verifies that the three points arn't duplicates or collinear
		if (p1.equals(p3) || p2.equals(p3) || p2.equals(p1))
		{
			throw new NoSuchElementException("At Least Two Points Are Duplicates");
		}
		else if (collinear(p1, p2, p3))
		{
			throw new NoSuchElementException("The Points Are Collinear");
		}
		else
		{
			// Creates a triangle with the points and name
			Triangle newTri = new Triangle(name, p1, p2, p3);
			myPoly.add(newTri);   // Add triangle to list
		}
	}
	
	/**
	 * Builds a Quad
	 * @param data an array list containing a name and 8 values
	 * @param myPoly an array list to add the constructed quad
	 */
	public static void buildQuad(ArrayList<String> data, ArrayList<Poly> myPoly)
	{
		// Obtains the Quad's name
		String name = data.get(0);
		
		// Creates all four points
		Point p1 = new Point(toDouble(data.get(1)), toDouble(data.get(2)));
		Point p2 = new Point(toDouble(data.get(3)), toDouble(data.get(4)));
		Point p3 = new Point(toDouble(data.get(5)), toDouble(data.get(6)));
		Point p4 = new Point(toDouble(data.get(7)), toDouble(data.get(8)));
		
		// Verifies that the points arn't duplicates or collinear
		if (p1.equals(p2) || p1.equals(p3) || p1.equals(p4) || p2.equals(p3) || p2.equals(p4) || p3.equals(p4))
		{
			throw new NoSuchElementException("At Least Two Points Are Duplicates");
		}
		else if (collinear(p1, p2, p3) || collinear(p4, p1, p2) || collinear(p1, p4, p3) || collinear(p2, p3, p4))
		{
			throw new NoSuchElementException("At Least Three Points Are Collinear");
		}
		else
		{
			// Creates a Quad with the points and name
			Quad newQuad = new Quad(name, p1, p2, p3, p4);
			myPoly.add(newQuad);   // Add Quad to list
		}
	}
	
	/**
	 * Checks if three points are collinear
	 * @param point1 the first point
	 * @param point2 the second point
	 * @param point3 the third point
	 * @return boolean based on calculated result
	 */
	public static boolean collinear(Point point1, Point point2, Point point3)
	{
		// Sets a default
		boolean result = false;
		
		// Calculates using "rise over run"
		// Format prevents division by zero
		double calc1 = (point2.getY() - point1.getY()) * (point3.getX() - point2.getX());
		double calc2 = (point3.getY() - point2.getY()) * (point2.getX() - point1.getX());
		
		// If slopes are equal they are collinear
		if (calc1 == calc2)
		{
			result = true;
		}
		
		return result;
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
				
				// Adds line of data to array
				data.add(line.trim());
			}
			return data;
		}
		finally
		{
			in.close();
		}
	}
}