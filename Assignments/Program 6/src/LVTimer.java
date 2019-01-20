// Levi VonBank

/**
 * A timer accumulates time when it is running. It can be
 * repeatedly start and stop.
 * (Measures the run time of a code.)
 */
public class LVTimer
{  
   private long elapsedTime;
   private long startTime;
   private boolean isRunning;

   /**
    * Constructs a timer that is in the stopped state
    * and has no time accumulated.
    */
   public LVTimer()
   {  
      reset();
   }

   /**
    * Starts the timer. Time starts accumulating now.
    */
   public void start()
   {  
      if (isRunning) { return; }
      isRunning = true;
      startTime = System.currentTimeMillis();
   }
   
   /**
    * Stops the timer. Time stops accumulating and is
    * is added to the elapsed time.
    */
   public void stop()
   {  
      if (!isRunning) { return; }
      isRunning = false;
      long endTime = System.currentTimeMillis();
      elapsedTime = elapsedTime + endTime - startTime;
   }
   
   /**
    * Returns the total elapsed time.
    * @return the total elapsed time
    */
   public long getElapsedTime()
   {  
      if (isRunning) 
      {  
         long endTime = System.currentTimeMillis();
         return elapsedTime + endTime - startTime;
      }
      else
      {
         return elapsedTime;
      }
   }
   
   /**
    * Stops the timer and resets the elapsed time to 0.
    */
   public void reset()
   {  
      elapsedTime = 0;
      isRunning = false;
   }
}
