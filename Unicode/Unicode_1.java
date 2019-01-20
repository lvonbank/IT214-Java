/**
 * @author Levi VonBank
 */

public class Unicode_1
{
	public static void main(String[] args)
	{
		System.out.println("\u00F1");
		System.out.println("resum\u00E9");
		System.out.println("\u00C5");
		System.out.println("\u03A9");
		System.out.println("\u00FC");
	
		System.out.println("\nGreek alphabet");
		for(char alphabet = '\u0370'; alphabet <='\u03FF'; alphabet ++ )
		{
			System.out.println(alphabet);
		}
		
		System.out.println("\nCyrillic script");
		for(char alphabet = '\u0400'; alphabet <='\u04FF'; alphabet ++ )
		{
			System.out.println(alphabet);
		}
		
		System.out.println("\nChinese, Japanese, and Korean characters");
		for(char alphabet = '\u4E00'; alphabet <='\u9FFF'; alphabet ++ )
		{
			System.out.println(alphabet);
		}
	}
}