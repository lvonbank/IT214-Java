/**
 * @author Levi VonBank
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class AppUtil 
{
	private static Map<String, ArrayList<OneTime>> oneTimeApps = new TreeMap<>();
	private static Map<String, ArrayList<Monthly>> monthlyApps = new TreeMap<>();
	private static ArrayList<Daily> dailyApps = new ArrayList<Daily>();
	
	private static ArrayList<String> badData = new ArrayList<String>();
	
	/**
	 * Constructs appointments obtain thought the dates file
	 * @throws IOException
	 */
	public static void buildAppointments() throws IOException
	{
		ArrayList<String> appStrings  = DataUtil.getData();
		for (int index = 0; index < appStrings.size(); index++)
		{	
			String testApp = appStrings.get(index);
			
			// Initializes variables
			String occurrence = null;
			String description = null;
			Date date_time = null;
			
			// Used to validate content
			String[] splitStr = testApp.split("[,]");

			if(splitStr.length > 2)
			{
				int firstComma = testApp.indexOf(',');
				int lastComma = (testApp.lastIndexOf(',') + 1);
				
				// Used to provide general date/time formats
				// Divides data accordingly
				occurrence = testApp.substring(0, firstComma).trim();
				description = testApp.substring(lastComma).trim();
				date_time = DateUtil.toDate(testApp.substring(firstComma+1,lastComma-1).trim());
			}
			
			// Checks if data was successfully converted
			if (occurrence == null || description == null || date_time == null)
			{
				badData.add(testApp);
			}
			else
			{
				Appointment appointment = DataUtil.constructAppointment(occurrence, date_time, description);
				if (appointment == null)
				{
					badData.add(testApp);
				}
				else
				{
					addAppointment(appointment);
				}
			}
		}
		
		// Presents uses with the bad data
		if (badData.size() > 0)
		{
			Run_Program.display(badData, "Invalid date found");
		}
	}
	
	/**
	 * Puts all apps in one data structure
	 * @return a arrayList of apps
	 */
	public static ArrayList<Appointment> getAllApps()
	{
		ArrayList<Appointment> allApps = new ArrayList<Appointment>();
		
		// Adds all OneTime Appointments
		for (ArrayList<OneTime> otList : oneTimeApps.values()) 
		{
			allApps.addAll(otList);
		}
		
		// Adds all Monthly Appointments
		for (ArrayList<Monthly> mList : monthlyApps.values()) 
		{
			allApps.addAll(mList);
		}
		
		// Adds all Daliy Appointments
		allApps.addAll(dailyApps);
		
		return allApps;
	}
	
	/**
	 * Searches data for any dates occurring on given date
	 * @param date the date in question
	 * @return the arrayList of all related data
	 */
	public static ArrayList<Appointment> searchOccurrence(Date date)
	{
		ArrayList<Appointment> foundApps = new ArrayList<Appointment>();
		
		// Retrieves all OneTime appointments
		String otKey = new OneTime(date, "").key();
		if (oneTimeApps.containsKey(otKey))
		{
			foundApps.addAll(oneTimeApps.get(otKey));
		}
		
		// Retrieves all Monthly appointments that occur on or after date
		String mKey = new Monthly(date, "").key();
		if (monthlyApps.containsKey(mKey))
		{
			ArrayList<Monthly> mList = monthlyApps.get(mKey);
			for (Monthly testApp : mList)
			{
				// Checks if date happened on/after
				if (testApp.occursOn(date))
				{
					foundApps.add(testApp);
				}
			}
		}
		
		// Adds all Daily appointments that occur on or after date
		for (Daily testApp : dailyApps)
		{
			// Checks if date happened on/after
			if (testApp.occursOn(date))
			{
				foundApps.add(testApp);
			}
		}
		
		return foundApps;
	}
	
	/**
	 * Searches data for a specified date
	 * @param date the date in question
	 * @return the arrayList of all related data
	 */
	public static ArrayList<Appointment> searchDate(Date date)
	{
		ArrayList<Appointment> allApps = AppUtil.getAllApps();
		ArrayList<Appointment> foundApps = new ArrayList<Appointment>();
		
		// Searches apps for exact date matches
		Appointment app = new OneTime(date, "");
		for (int index = 0; index < allApps.size(); index++)
		{
			if (app.occursOn(allApps.get(index).getDate()))
			{
				foundApps.add(allApps.get(index));
			}
		}
		return foundApps;
	}
	
	/**
	 * Searches data for a specified description
	 * @param keyDescription the description in question
	 * @return the arrayList of all related data
	 */
	public static ArrayList<Appointment> searchDescription(String keyDescription)
	{	
		ArrayList<Appointment> allApps = getAllApps();
		ArrayList<Appointment> foundApps = new ArrayList<Appointment>();
		
		// Runs through app data
		String key = keyDescription.toLowerCase();
		for (int index = 0; index < allApps.size(); index++)
		{
			// Checks if app contains key description  
			String test = allApps.get(index).getDescription().toLowerCase();
			if (test.contains(key))
			{
				foundApps.add(allApps.get(index));
			}
		}
		
		return foundApps;
	}
	
	/**
	 * Adds the Appointment from the data
	 * @param app the Appointment to be added
	 */
	public static void addAppointment(Appointment app)
	{
		
		if (app instanceof OneTime)
		{
			OneTime castApp = (OneTime) app;
			if (!oneTimeApps.containsKey(castApp.key()))
			{
				oneTimeApps.put(castApp.key(), new ArrayList<OneTime>());
			}
			oneTimeApps.get(castApp.key()).add(castApp);
		}
		else if (app instanceof Monthly)
		{
			Monthly castApp = (Monthly) app;
			if (!monthlyApps.containsKey(castApp.key()))
			{
				monthlyApps.put(castApp.key(), new ArrayList<Monthly>());
			}
			monthlyApps.get(castApp.key()).add(castApp);
		}
		else if (app instanceof Daily)
		{
			dailyApps.add((Daily) app);
		}
	}	
	
	/**
	 * Removes the Appointment from the data
	 * @param app the Appointment to remove
	 */
	public static void removeAppointment(Appointment app)
	{		
		if (app instanceof OneTime)
		{
			OneTime castApp = (OneTime) app;
			if (!oneTimeApps.containsKey(castApp.key()))
			{
				oneTimeApps.put(castApp.key(), new ArrayList<OneTime>());
			}
			oneTimeApps.get(castApp.key()).remove(castApp);
		}
		else if (app instanceof Monthly)
		{
			Monthly castApp = (Monthly) app;
			if (!monthlyApps.containsKey(castApp.key()))
			{
				monthlyApps.put(castApp.key(), new ArrayList<Monthly>());
			}
			monthlyApps.get(castApp.key()).remove(castApp);
		}
		else if (app instanceof Daily)
		{
			dailyApps.remove((Daily) app);
		}
	}
}
