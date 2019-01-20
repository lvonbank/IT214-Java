/**
 * @author Levi VonBank
 */

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DatePanel extends JPanel {

	private JComboBox<String> cbMonth;
	private JComboBox<String> cbDay;
	private JTextField tfYear;
	
	/**
	 * Create the panel.
	 */
	public DatePanel() {
		setLayout(null);
		
		cbMonth = new JComboBox<String>();
		cbMonth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbMonth.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbMonth.setBounds(88, 16, 82, 28);
		add(cbMonth);
		
		cbDay = new JComboBox<String>();
		cbDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbDay.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				 "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", 
				 "27", "28", "29", "30", "31"}));
		cbDay.setBounds(205, 16, 58, 29);
		add(cbDay);
		
		tfYear = new JTextField();
		tfYear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				// Erases pre-set upon click
				if (tfYear.getText().equals("Year"))
				{
					tfYear.setText("");
				}
			}
			public void mouseExited(MouseEvent e) {
				// Resets pre-set if empty
				if (tfYear.getText().equals(""))
				{
					tfYear.setText("Year");
				}
			}
		});
		tfYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfYear.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		tfYear.setText("Year");
		tfYear.setBounds(290, 17, 117, 27);
		add(tfYear);
		
		JLabel lblNewLabel = new JLabel("Date:                       /                /");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(28, 16, 379, 28);
		add(lblNewLabel);

	}
	
	/**
	 * Returns date selections
	 */
	public String getSelection()
	{
		String month = (String)cbMonth.getSelectedItem();
		String day = (String)cbDay.getSelectedItem();
		String year = tfYear.getText().trim();
		return (month+","+day+","+year);	
	}
	
	/**
	 * Sets the edibility of boxes
	 * @param flag boolean
	 */
	public void accessibility(boolean flag)
	{
		cbMonth.setEnabled(flag);;
		cbDay.setEnabled(flag);;
		tfYear.setEnabled(flag);;
	}
}
