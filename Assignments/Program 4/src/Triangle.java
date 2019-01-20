// Levi VonBank

public class Triangle
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
	 * Calculates the side length (called from the constructor)
	 * @param point1 first point
	 * @param point2 second point
	 * @return the distance between two points
	 */
	private double sideLength(Point point1, Point point2)
	{
		// Calculates using the distance formula
		double calc1 = Math.pow((point2.getX() - point1.getX()),2);
		double calc2 = Math.pow((point2.getY() - point1.getY()),2);
		double sideLength = Math.sqrt(calc1 + calc2);
		
		return sideLength;
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
		String sideOne = String.format("Side 1 is %.2f\r\n", side1);
		String sideTwo = String.format("Side 2 is %.2f\r\n", side2);
		String sideThree = String.format("Side 3 is %.2f", side3);
		
		return sideOne + sideTwo + sideThree;
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
	 * String version of triangle
	 * @return a string version of the triangle
	 */
	public String toString()
	{
		return (printName() + "\r\n" + listVertices() + "\r\n" + listSides());
	}
}
