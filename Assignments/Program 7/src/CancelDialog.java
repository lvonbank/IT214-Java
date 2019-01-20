/**
 * @author Levi VonBank
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CancelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CancelDialog dialog = new CancelDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CancelDialog() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Run_Program.enable();
			}
		});
		setTitle("Cancel By...");
		setBounds(100, 100, 529, 242);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Adds date panel
		DatePanel datePanel = new DatePanel();
		datePanel.setToolTipText("Cancel By Date");
		datePanel.setBorder(null);
		datePanel.setBounds(54, 12, 445, 56);
		contentPanel.add(datePanel);
		datePanel.accessibility(false);
		
		// Adds date description panel
		DescriptionPanel descriptionPanel = new DescriptionPanel();
		descriptionPanel.setToolTipText("Cancel By Description");
		descriptionPanel.setBounds(54, 80, 445, 63);
		contentPanel.add(descriptionPanel);
		descriptionPanel.accessibility(false);
		
		// Adds date radio button
		JRadioButton rbDate = new JRadioButton("");
		rbDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rbDate.setBounds(22, 12, 29, 56);
		rbDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datePanel.accessibility(true);
				descriptionPanel.accessibility(false);
			}
		});
		contentPanel.add(rbDate);
		
		// Adds description radio button
		JRadioButton rbDescription = new JRadioButton("");
		rbDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rbDescription.setBounds(22, 80, 29, 63);
		rbDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descriptionPanel.accessibility(true);
				datePanel.accessibility(false);
			}
		});
		contentPanel.add(rbDescription);

		// Adds radio buttons to button group
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbDate);
		buttonGroup.add(rbDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String dateStr = datePanel.getSelection();
						String description = DescriptionPanel.getSelection();
						
						// Determines what was selected
						if (rbDescription.isSelected())
						{
							// Validates description
							if (description.contains(","))
							{
								JOptionPane.showMessageDialog(null, "Descriptions cannot contain commas");
							}
							else
							{
								// Searches for all apps containing description
								ArrayList<Appointment> apps = AppUtil.searchDescription(description);
								if (apps.size() > 0)
								{
									dispose();
									RemoveDialog dialog = new RemoveDialog(apps);
									dialog.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "No Appointments Found");
								}
							}
						}
						else if (rbDate.isSelected())
						{
							// Validates date
							Date date = DateUtil.toDate(dateStr);
							if (date == null)
							{
								JOptionPane.showMessageDialog(null, "Invalid Date");
							}
							else
							{
								// Searches for a given description
								ArrayList<Appointment> foundApps = AppUtil.searchDate(date);
								
								// Verifies that date were found
								if (foundApps.size() > 0)
								{
									dispose();
									RemoveDialog dialog = new RemoveDialog(foundApps);
									dialog.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "No Appointments Found");
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Nothing Was Selected");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Run_Program.enable();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
