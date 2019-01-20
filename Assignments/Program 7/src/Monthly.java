/**
 * @author Levi VonBank
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Monthly are Appointment that happen on a given day of a given month
 */
public class Monthly extends Appointment
{
	/**
	 * Constructs a monthly appointment
	 * @param date the date of the event
	 * @param description a string about the event
	 */
    public Monthly(Date date, String description)
    {
		super(date, description);
	}
    
    /**
     * Creates a key relative to date and occurrence
     * @return the day
     */
    public String key()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("d");
		return sdf.format(this.getDate());
    }
    
	/** 
	 * Returns a Boolean if day and month match-up (from set date on)
	 * @param date the date check the occurrence
	 */
    public boolean occursOn(Date keyDate)
    {   
    	// Note: done this way to reformat both dates to remove time
    	SimpleDateFormat sdf = new SimpleDateFormat("M,d,yyyy");
    	
    	Calendar checkDate = Calendar.getInstance();
    	checkDate.setTime(DateUtil.toDate(sdf.format(keyDate)));
    	
    	Calendar thisDate = Calendar.getInstance();
    	thisDate.setTime(DateUtil.toDate(sdf.format(this.getDate())));
    	
    	return (checkDate.get(Calendar.DAY_OF_MONTH) == thisDate.get(Calendar.DAY_OF_MONTH) &&
    			checkDate.compareTo(thisDate) >= 0);
    }
}