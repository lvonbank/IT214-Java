/**
 * @author Levi VonBank
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Onetime are Appointment that happen once
 */
public class OneTime extends Appointment
{
	/**
	 * Constructs a onetime appointment
	 * @param date the date of the event
	 * @param description a string about the event
	 */
    public OneTime(Date date, String description)
    {
		super(date, description);
	}
    
    /**
     * Creates a key relative to date and occurrence
     * @return the date
     */
    public String key()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("Mdyyyy");
		return sdf.format(this.getDate());
    }
    
    /**
     *  Returns a True Boolean if day, month and year match-up
     *  @param date the date check the occurrence
     */
    public boolean occursOn(Date keyDate)
    {  
    	Calendar checkDate = Calendar.getInstance();
    	checkDate.setTime(keyDate);
    	Calendar thisDate = Calendar.getInstance();
    	thisDate.setTime(this.getDate());
    	
		return (checkDate.get(Calendar.MONTH) == thisDate.get(Calendar.MONTH) &&
				checkDate.get(Calendar.DAY_OF_MONTH) == thisDate.get(Calendar.DAY_OF_MONTH) &&
				checkDate.get(Calendar.YEAR) == thisDate.get(Calendar.YEAR));
    }
}