// Levi VonBank

public class Quad extends Poly
{
	private String name;
	
	private Point vert1;
	private Point vert2;
	private Point vert3;
	private Point vert4;
	
	private double side1;
	private double side2;
	private double side3;
	private double side4;
	
	private double diag1;
	private double diag2;
	
	public Quad(String quadName, Point point1, Point point2, Point point3, Point point4)
	{
		name = quadName;
		
		vert1 = point1;
		vert2 = point2;
		vert3 = point3;
		vert4 = point4;
		
		side1 = sideLength(point1, point2);
		side2 = sideLength(point2, point3);
		side3 = sideLength(point3, point4);
		side4 = sideLength(point4, point1);
		
		diag1 = sideLength(point1, point3);
		diag2 = sideLength(point2, point4);
	}
	
	/**
	 * Lists the vertice’s coordinates
	 * @return the vertice’s coordinates
	 */
	public String listVertices()
	{
		String vertOne = ("(" + vert1.getX() + "," + vert1.getY() + ")");
		String vertTwo = (",(" + vert2.getX() + "," + vert2.getY() + ")");
		String vertThree = (",(" + vert3.getX() + "," + vert3.getY() + ")");
		String vertFour = (",(" + vert4.getX() + "," + vert4.getY() + ")");
		
		return ("Vertices: " + vertOne + vertTwo + vertThree + vertFour);
	}
	
	/**
	 * Lists the quad’s sides’ lengths
	 * @return quad’s sides’ lengths
	 */
	public String listSides()
	{
		String sideLengths = String.format("%-8.2f%-8.2f%-8.2f%.2f", side1, side2, side3, side4);
		return "Side lengths:   " + sideLengths;
	}
	
	/**
	 * Lists the diagonal’s lengths
	 * @return the diagonal’s lengths
	 */
	public String listDiagonals()
	{
		String diagonals = String.format("%-8.2f%.2f", diag1, diag2);
		return "Diagonals:      " + diagonals;
	}
	
	/**
	 * Returns the quad’s name
	 * @return quad’s name
	 */
	public String printName()
	{
		return "The name of the quadrilateral is " + name + ".";
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
		if (isEqual(side1, side2) || isEqual(side2, side3) || isEqual(side3, side4) || isEqual(side4, side1))
		{
			// Corrects result if at least two sides where equal
			result = "At least two ";
		}
		
		return result + "sides are of equal length.";
	}
	
	/**
	 * String version of quad
	 * @return a string version of the quad
	 */
	public String toString()
	{
		return (printName() + "\r\n" + 
				listVertices() + "\r\n" + 
				listSides() + "\r\n" + 
				listDiagonals() + "\r\n" + 
				equalSides());
	}
}
