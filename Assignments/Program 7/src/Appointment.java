/**
 * @author Levi VonBank
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Creates a class Appointment to manage schedule
 */
public abstract class Appointment implements Comparable<Appointment>
{
	private Date date;
	private String description;
	
	/**
	 * Constructs an Appointment
	 * @param date the date of the event
	 * @param description a string about the event
	 */
    public Appointment(Date date, String description)
    {
    	this.date = date;
    	this.description = description;
    }
    
    /**
     *  Abstract method for Appointment
     * @param keyDate the date in question
     * @return boolean of the occurrence
     */
    public abstract boolean occursOn(Date keyDate);
    
    /**
     * Checks if a date happens today
     * @return occursOn boolean
     */
    public boolean today()
    {
    	Date currentDate = new Date();
    	return occursOn(currentDate);
    }
    
    /**
     * Checks if a date happens tomorrow
     * @return occursOn boolean
     */
    public boolean tomorrow()
    {
    	Date currentDate = new Date();
    	return occursOn(DateUtil.addDays(currentDate, 1));
    }
    
    /**
     * Checks if a date happens within the next seven days
     * @return occursOn boolean
     */
    public boolean sevenDay()
    {
    	Date currentDate = new Date();
    	for (int index = 0; index < 7; index++)
		{
    		boolean result = occursOn(currentDate);
    		if (result)
    		{
    			return true;
    		}
    		currentDate = DateUtil.addDays(currentDate, 1);
		}
    	return false;
    }
    
    /**
     *  Returns appointments description
     * @return description
     */
    public String getDescription()
    {
    	return description;
    }
    
    /**
     * Returns appointment date
     * @return date
     */
    public Date getDate()
    {
    	return date;
    }
    
    /**
     *  A string representation of the Appointment
     *  @return String of appointment
     */
    public String toString()
    {
    	String occurrence  = this.getClass().getName();
    	SimpleDateFormat sdf = new SimpleDateFormat("M,d,yyyy,h:mm a");
    	return occurrence + "," + sdf.format(date) + "," + description;
    }
    
    /**
     * Compares two Appointment objects by date
     * @param other the other Appointment object.
     */
	public int compareTo(Appointment other)
	{		
		if (other.toString() == null)
		{
			throw new NullPointerException();
		}
		
    	// Compares date and returns
		return date.compareTo(other.date);
	}
}


