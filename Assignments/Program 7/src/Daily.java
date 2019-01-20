/**
 * @author Levi VonBank
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Daily are Appointment that happen everyday
 */
public class Daily extends Appointment
{
	/**
	 * Constructs a daily appointment
	 * @param date the date of the event
	 * @param description a string about the event
	 */
    public Daily(Date date, String description)
    {
		super(date, description);
	}
    
	/**
	 *  Returns a Boolean no mater what day, month or year (from set date on)
	 *  @param date the date check the occurrence
	 */
    public boolean occursOn(Date keyDate)
    {
    	// Note: done this way to reformat both dates to remove time
    	SimpleDateFormat sdf = new SimpleDateFormat("M,d,yyyy");
    	
    	Calendar checkDate = Calendar.getInstance();
    	checkDate.setTime(DateUtil.toDate(sdf.format(keyDate)));
    	
    	Calendar thisDate = Calendar.getInstance();
    	thisDate.setTime(DateUtil.toDate(sdf.format(this.getDate())));
    	
        return (checkDate.compareTo(thisDate) >= 0);
    }
}