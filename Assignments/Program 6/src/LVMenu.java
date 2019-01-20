// Levi VonBank

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A menu that is displayed on a console.
 */
public class LVMenu
{
   private ArrayList<String> options;
   private Scanner in;

   /**
    * Constructs a menu with no options.
    */
   public LVMenu()
   {
      options = new ArrayList<String>();
      in = new Scanner(System.in);
   }

   /**
    * Adds an option at the end of the menu.
    * @param option the option to added
    */
   public void addOption(String option)
   {
      options.add(option);
   }
   
   /**
    * Gets an option at a certain location in the menu.
    * @param optionNumber the option to get
    * @return the option 
    */
   public String getOption(int optionNumber)
   {
	   return options.get(optionNumber - 1);
   }
   
   /**
    * Displays the menu, with options numbered starting at 1,
    * and prompts the user for input. Repeats until a valid input 
    * value is entered.
    * @return the number that the user supplied
    */
   public int getInput()
   {
      int input;
      
      do
      {
    	 System.out.println("\nSearch by ...");
    	 
         for (int i = 0; i < options.size(); i++)
         {
            int choice = i + 1;
            System.out.println(choice + ") " + options.get(i)); 
         }
         input = LVUtil.getInt("Enter your option: ");
         
         if (input < 1 || input > options.size())
         {
        	 System.out.println("Invalid option");
         }
         
      }
      while (input < 1 || input > options.size());
      
      return input;
   }
}