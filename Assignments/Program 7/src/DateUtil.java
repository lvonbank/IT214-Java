/**
 * @author Levi VonBank
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil
{
    // Creates a list of date formats that can be parse.
    private static List<SimpleDateFormat>
            dateFormats = new ArrayList<SimpleDateFormat>() {{
            add(new SimpleDateFormat("M,d,yyyy,h:mm a"));
            add(new SimpleDateFormat("M,d,yyyy, h:mm a"));
            add(new SimpleDateFormat("M/d/yyyy,h:mm a"));
            add(new SimpleDateFormat("M/d/yyyy h:mm a"));
            add(new SimpleDateFormat("M.d.yyyy h:mm a"));
            add(new SimpleDateFormat("M,d,yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
        }
    };
 
    /**
     * Convert String with various formats into java.util.Date
     * @param input a date as a string
     * @return a java.util.Date object if parsed successfully else null
     */
    public static Date toDate(String input) {
        Date date = null;
        if(null == input) 
        {
            return null;
        }
        // Checks if date correspond to list of formats
        for (SimpleDateFormat format : dateFormats) {
            try 
            {
            	// Converts a string to a Date
                format.setLenient(false);
                date = format.parse(input);
            } 
            catch (ParseException e){} // try another format
            if (date != null) 
            {
                break;
            }
        }
 
        return date;
    }
    
    /**
     * Increments a Date by a given amount of days
     * @param Date the date to increase the days of
     * @param days the amount of days (negative will decrement the days)
     * @return the new date
     */
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}