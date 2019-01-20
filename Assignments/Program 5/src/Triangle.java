// Levi VonBank

public class Triangle extends Poly
{
	private String name;
	
	private Point vert1;
	private Point vert2;
	private Point vert3;
	
	private double side1;
	private double side2;
	private double side3;
	
	public Triangle(String triName, Point point1, Point point2, Point point3)
	{
		name = triName;
		
		vert1 = point1;
		vert2 = point2;
		vert3 = point3;
		
		side1 = sideLength(point1, point3);
		side2 = sideLength(point2, point3);
		side3 = sideLength(point2, point1);
	}
	
	/**
	 * Lists the vertice’s coordinates
	 * @return the vertice’s coordinates
	 */
	public String listVertices()
	{
		String vertOne = ("(" + vert1.getX() + "," + vert1.getY() + "),");
		String vertTwo = ("(" + vert2.getX() + "," + vert2.getY() + "),");
		String vertThree = ("(" + vert3.getX() + "," + vert3.getY() + ")");
		
		return ("Vertices: " + vertOne + vertTwo + vertThree);
	}
	
	/**
	 * Lists the triangle’s sides’ lengths
	 * @return triangle’s sides’ lengths
	 */
	public String listSides()
	{
		String sideLengths = String.format("%-8.2f%-8.2f%.2f", side1, side2, side3);
		return "Side lengths:   " + sideLengths;
	}
	
	/**
	 * Returns the triangle’s name
	 * @return triangle’s name
	 */
	public String printName()
	{
		return "The name of the triangle is " + name + ".";
	}
	
	/**
	 * Indicates if any of the sides are of equal length
	 * @return a string describing the status of equal side lengths
	 */
	public String equalSides()
	{
		// Starts with a default
		String result = "No ";
		
		// Checks an arrangement of sides for equality
		if (isEqual(side1, side2) || isEqual(side2, side3) || isEqual(side3, side1))
		{
			// Corrects result if at least two sides where equal
			result = "At least two ";
		}
		
		return result + "sides are of equal length.";
	}
	
	/**
	 * String version of triangle
	 * @return a string version of the triangle
	 */
	public String toString()
	{
		return (printName() + "\r\n" + 
				listVertices() + "\r\n" + 
				listSides() + "\r\n" + 
				equalSides());
	}
}
