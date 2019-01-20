// Levi VonBank

// Imports Scanner
import java.util.Scanner;

/*
 * Why dew point is now preferred in most commercial weather reports rather
 * than relative humidity?
 * 
 * The dew point has a distinct correlation with moisture. An increase in the
 * dew point results in an elevated moisture level. Whereas relative humidity 
 * can be a distorting variable when it comes to exterior air conditions. For 
 * instance a day with high temperature and dew point can inadvertently have a
 * low relative humidity. This subsequently can feel more humidor then a day
 * with a lower temperature and dew point with a high relative humidity. Therefore
 * dew point is an extremely depictive tool for representing the actual feel of
 * external air conditions.
 * 
 * Source: http://www.weather.gov/arx/why_dewpoint_vs_humidity
 */

/**
 * This program reads the relative humidity (between 0 and 1)and the temperature
 * (in degrees Fahrenheit) and prints the resulting dew point value. (P2.29)
 */
public class LVDewPoint
{
	public static void main(String[] args)
	{
		// Creates a scanner object for obtaining user input
		Scanner in = new Scanner(System.in);
		
		// Creates two constants for calculating the dew point
		final double A = 17.27;
		final double B = 237.7; // Represented in Celcius
		
		// Obtains the temperature from the user (in degrees Fahrenheit)
		System.out.print("Enter the temperature (in degrees F): ");
		double temperature = in.nextDouble(); // Assigns input to temperature
		
		// Obtains the relative humidity from the user
		System.out.print("Enter the relative humidity (between 0 and 1): ");
		double relativeHumidity = in.nextDouble(); // Assigns input to relativeHumidity
		
		// Converts temperature from Fahrenheit to Celsius for use in calculation
		temperature = (temperature - 32) * 5.0/9.0;
		
		// Calculates the dew point using the relative humidity and temperature in Celcius
		double gamma = (A * temperature)/(B + temperature) + Math.log(relativeHumidity);
		double  dewPointTemperature = (B * gamma)/(A - gamma);
		
		// Converts the calculation output back from Celsius to Fahrenheit
		dewPointTemperature = (dewPointTemperature * 9.0/5.0) + 32;
		
		// Displays the results of the dew point calculation in Fahrenheit
		System.out.printf("The approximant dew point is %.2f°F \n", dewPointTemperature);
		
		in.close();
	}
}
