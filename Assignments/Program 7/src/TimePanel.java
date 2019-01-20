/**
 * @author Levi VonBank
 */

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class TimePanel extends JPanel {

	private JComboBox<String> cbOccursOn;
	private JComboBox<String> cbHour;
	private JComboBox<String> cbMinutes;
	private JComboBox<String> cb_Am_Pm;
	
	/**
	 * Create the panel.
	 */
	public TimePanel() {
		setLayout(null);
		
		cbOccursOn = new JComboBox<String>();
		cbOccursOn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbOccursOn.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"OneTime", "Monthly", "Daily"}));
		cbOccursOn.setBounds(88, 16, 104, 28);
		add(cbOccursOn);
		
		cbHour = new JComboBox<String>();
		cbHour.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		cbHour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbHour.setBounds(229, 16, 53, 28);
		add(cbHour);
		
		cbMinutes = new JComboBox<String>();
		cbMinutes.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			     "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", 
			     "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", 
			     "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", 
			     "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", 
			     "55", "56", "57", "58", "59"}));
		cbMinutes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbMinutes.setBounds(294, 16, 53, 28);
		add(cbMinutes);
		
		cb_Am_Pm = new JComboBox<String>();
		cb_Am_Pm.setModel(new DefaultComboBoxModel<String>(new String[] {"AM", "PM"}));
		cb_Am_Pm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb_Am_Pm.setBounds(377, 16, 58, 28);
		add(cb_Am_Pm);
		
		JLabel lable = new JLabel("Time:                                          :");
		lable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lable.setBounds(25, 16, 410, 30);
		add(lable);
	}

	/**
	 * Allows for access of the occurance
	 * @return the occurrence
	 */
	public String getInstance()
	{
		String occursOn = (String)cbOccursOn.getSelectedItem();
		return occursOn;
	}
	
	/**
	 * Allows for access of the date and time
	 * @return string of date and time
	 */
	public String getSelection()
	{
		String hour = (String)cbHour.getSelectedItem();
		String minutes = (String)cbMinutes.getSelectedItem();
		String am_pm = (String)cb_Am_Pm.getSelectedItem();
		
		return (hour+":"+minutes+" "+am_pm);
		
	}
}
