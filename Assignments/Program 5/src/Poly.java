// Levi VonBank

public class Poly
{
	/**
	 * Calculates the side length (called from the constructor)
	 * @param point1 first point
	 * @param point2 second point
	 * @return the distance between two points
	 */
	protected double sideLength(Point point1, Point point2)
	{
		// Calculates using the distance formula
		double calc1 = Math.pow((point2.getX() - point1.getX()),2);
		double calc2 = Math.pow((point2.getY() - point1.getY()),2);
		double sideLength = Math.sqrt(calc1 + calc2);
		
		return sideLength;
	}
	
	/**
	 * Checks if two values are equal up to 0.0001
	 * @param value1 the first value
	 * @param value2 the second value
	 * @return the resulting boolean after the check
	 */
	protected boolean isEqual(double value1, double value2)
	{
		// Creates a state for being “close enough”
		final double EPSILON = 0.0001;
		
		// Sets a default equality
		boolean equal = false;
		
		// Checks if the values are with in the EPSILON range
		if (Math.abs(value1 - value2) < EPSILON)
		{
			equal = true;
		}
		
		// The determined result of equality
		return equal;
	}
}
