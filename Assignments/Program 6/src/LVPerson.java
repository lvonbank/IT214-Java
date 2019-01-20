// Levi VonBank

/**
 * A Person that is represents by a first name,
 * last name, and phone number
 */
public class LVPerson implements Comparable<LVPerson>
{
	String firstName;
	String lastName;
	String phoneNumber;
	
	/**
	 *  Constructs default Person
	 */
	public LVPerson()
	{
		this(null, null, null);
	}
	
	/**
	 * Constructs Person
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 */
	public LVPerson(String firstName, String lastName, String phoneNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Sets first name
	 * @param first name
	 */
    public void setFirstName(String name) 
    {
        firstName = name;
    }
    
	/**
	 * Gets first name
	 * @return first name
	 */
    public String getFirstName() 
    {
        return firstName;
    }
    
	/**
	 * Sets last name
	 * @param last name
	 */
    public void setLastName(String name) 
    {
        lastName = name;
    }
    
	/**
	 * Gets first name
	 * @return last name
	 */
    public String getLastName() 
    {
        return lastName;
    }
    
	/**
	 * Sets phone number
	 * @param a phone number
	 */
    public void setPhoneNumber(String number) 
    {
    	phoneNumber = number;
    }
    
	/**
	 * Gets phone number
	 * @return a phone number
	 */
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    /**
     * String version of Person
     */
    public String toString()
    {
    	String num = getPhoneNumber();
    	if (getPhoneNumber().length() == 10)
    	{
    		num = ("(" + num.substring(0,3) +  ")-" + num.substring(3,6) + "-" + num.substring(6,10));
    	}
        return getFirstName() + " " + getLastName() + ": " + num;
    }
    
    /**
     * Compares two Person objects by last, first name, phone number
     * @param other the other person object.
     */
	public int compareTo(LVPerson other)
	{		
		if (other.toString() == null)
		{
			throw new NullPointerException();
		}
		
    	// Compares last names
		int result = lastName.compareToIgnoreCase(other.lastName);
		
		// Compares first names if last names were the same
		if (result == 0)
		{
			result = firstName.compareToIgnoreCase(other.firstName);
		}
		
		// Compares phone number if first names were the same
		if (result == 0)
		{
			result = phoneNumber.compareToIgnoreCase(other.phoneNumber);
		}
		
		return result;
	}
}