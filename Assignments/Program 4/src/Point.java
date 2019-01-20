// Levi VonBank

public class Point
{
	private double xValue;
	private double yValue;
	
	public Point(double x, double y)
	{
		xValue = x;
		yValue = y;
	}
	
	public Point(String x, String y) throws NumberFormatException
	{
		xValue = Double.parseDouble(x);
		yValue = Double.parseDouble(y);
	}
	
	/**
	 * @return X Coordinate
	 */
	public double getX()
	{
		return xValue;
	}
	
	/**
	 * @return Y Coordinate
	 */
	public double getY()
	{
		return yValue;
	}
	
	/**
	 * Checks if two points are equal
	 * @return result of checking both the points
	 */
	public boolean equals(Point otherPoint)
	{
		boolean result = false;
		
		if (this.getX() == otherPoint.getX() && this.getY() == otherPoint.getY())
		{
			result = true;
		}
		
		return result;
	}
}
