/**
 * @author Levi VonBank
 */

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DescriptionPanel extends JPanel {

	private static JTextField tfDescription;
	
	/**
	 * Create the panel.
	 */
	public DescriptionPanel() {
		setLayout(null);

		tfDescription = new JTextField();
		tfDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfDescription.setColumns(10);
		tfDescription.setBackground(SystemColor.menu);
		tfDescription.setBounds(140, 16, 262, 26);
		add(tfDescription);
		
		JLabel label = new JLabel("Description:");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(28, 16, 117, 26);
		add(label);
	}
	
	/**
	 * Returns description input
	 */
	public static String getSelection()
	{
		String description = tfDescription.getText().trim();
		return (description);
	}
	
	/**
	 * Sets the edibility of box
	 * @param flag boolean
	 */
	public void accessibility(boolean flag)
	{
		tfDescription.setEnabled(flag);
	}
}
