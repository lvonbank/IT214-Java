/**
 * @author Levi VonBank
 */

public class Unicode_2
{	
	public static void main(String[] args)
	{
		System.out.println("Just a delta: " + '\u0394');  // for hexidecimal values
		System.out.println("And another: " + Character.toString((char)916)); // for decimal values
		// because 394 in base 16 = 3*16^2 + 9*16 + 4 = 916 in base 10

		char nummy = '\u0394';
		System.out.println("And its unicode name is: "+ Character.getName(nummy));

		System.out.println("Here is a checkmark: " + '\u2713');
		System.out.println("And its unicode name is: "+ Character.getName('\u2713'));
		System.out.println("And the inverted question mark: " + Character.toString((char)191));
		System.out.println("And its unicode name is: "+ Character.getName('\u00BF'));
		// because 191 in base 10 is BF in base 16
		System.out.println("Or like this: "+ Character.getName((char)191));

		System.out.println("");

		// Greek lower case from 945 through 969
		System.out.println("GREEK LOWER CASE");
		for (int code=945; code < 970; ++code)
		{
			System.out.print((char)code);
		}
		System.out.println("");
		
		// Greek lower case from 913 through 937 (930 has none)
		System.out.println("GREEK UPPER CASE");
		for (int code=913; code < 938; ++code)
		{
			System.out.print((char)code);
		}
		System.out.println("");

		// Basic Cyrillic lower case
		System.out.println("CYRILLIC LOWER CASE");
		for (int code=1072; code < 1104; ++code)
		{
			System.out.print((char)code);
		}
		System.out.println("");

		// Basic Cyrillic upper case
		System.out.println("CYRILLIC UPPER CASE");
		for (int code=1040; code < 1072; ++code)
		{
			System.out.print((char)code);
		}
		System.out.println("");
	}
}
