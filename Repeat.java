// Levi VonBank

public class Repeat
{
	/**
	 * Creates a string of repeated characters
	 * @param str is the text interested in repeating
	 * @param times how many times text is to be repeated
	 * @return a string containing repeated text
	 */
	public static String repeat(String str, int times)
	{
		String repeatedStr = "";
		
		for(int i = 0; i < times; i++) 
		{
			repeatedStr += str;
		}
		return repeatedStr;
	}
}
