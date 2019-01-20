// Levi VonBank

/**
 * Simulates "lets Make A Deal". In each iteration, the program randomly
 * selects a door number between 1 and 3 for the car, player, and game 
 * show host. The host's door is a door containing a goat (but not the 
 * door that the player picked). The program then increment a counter for 
 * if the player would win by switching or by sticking with the original
 * choice. Running a 1,000 iterations and print both counters. (P4.24)
 */
public class LVMontyHall
{
	public static void main(String[] args)
	{
		final int ITERATIONS = 1000; // Number of games to be played
		
		int switchWin = 0; // Wins by switching
		int stickWin = 0; // Wins by sticking with the original choice
		
		for (int i = 0; i < ITERATIONS; i++)
		{
			// Randomly selects a door for the car
			int carDoor = (int) (Math.random() * 3) + 1;
			
			// Does a random selection for the players choice
			int player = (int) (Math.random() * 3) + 1;
			
			/* 
			 * Randomly picks a door for the game host that has a goat 
			 * (but not a door the player picked).
			 */
			int gameHost;
			do
			{
				gameHost = (int) (Math.random() * 3) + 1;
			}
			while (gameHost == carDoor || gameHost == player);
			
			/*
			 *  Keeps track of all the wins by simulates
			 *  if the player would have switch or 
			 *  otherwise stayed with their first choice.
			 */
			if (carDoor == player)
			{
				/*
				 * Player wins for sticking 
				 * with their first choice.
				 */
				stickWin++;
			}
			else
			{
				/*
				 *  Player would have won if 
				 *  they had switched choices.
				 */
				switchWin++;
			}
		}
		// Prints the results
		System.out.println("Origanal choice Wins: " + stickWin);
		System.out.println("Switching choice Wins: " + switchWin);
	}

}
