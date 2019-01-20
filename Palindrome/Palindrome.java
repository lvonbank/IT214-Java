//Levi VonBank

import java.util.Scanner;

public class Palindrome 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner (System.in);
		System.out.print("Enetr a String: ");
		String str = in.nextLine();
		
		boolean pal = isPalindrome(str);
		System.out.print(pal);
	}
	
	/*
	 *  Tests whether a text is a palindrome.
	 *  @param text a string that is being checked
	 *  @return True if text is a palindrome, False otherwise
	 */
	public static boolean isPalindrome(String text)
	{
		return substringIsPalindrome(text, 0, text.length() - 1);
	}

	/*
	 * Recursively tests whether a substring is a palindrome.
	 * @param text a string that is being checked.
	 * @param start the index of the first character of the substring
	 * @param end the index of the last character of the substring
	 * @return true if the substring is a palindrome
	 */
	private static boolean substringIsPalindrome(String text, int start, int end)
	{
		text = text.toLowerCase();
		
		// Separate case for substrings of length 0 and 1.
		if (start >= end)
		{ 
			return true; 
		}
		else
		{
			// Get first and last characters
			char first = text.charAt(start);
			char last = text.charAt(end);
			if (Character.isAlphabetic(first) && Character.isAlphabetic(last))
			{
				if (first == last)
				{
					// Test substring that doesn’t contain the matching letters.
					 return substringIsPalindrome(text, start + 1, end - 1);
				}
				else
				{
					return false;
				}
			}
			else if (!(Character.isAlphabetic(last)))
			{
				// Test substring that doesn’t contain the last character.
				return substringIsPalindrome(text, start, end - 1);
			}
			else
			{
				// Test substring that doesn’t contain the first character.
				return substringIsPalindrome(text, start + 1, end);
			}
		}
	}
}
