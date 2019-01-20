/**
 * @author Levi VonBank
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DescriptionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DescriptionDialog dialog = new DescriptionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DescriptionDialog() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Run_Program.enable();
			}
		});
		setTitle("Enter Description");
		setBounds(100, 100, 478, 175);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		DescriptionPanel descriptionPanel = new DescriptionPanel();
		descriptionPanel.setBounds(15, 16, 445, 63);
		contentPanel.add(descriptionPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String description = DescriptionPanel.getSelection();
						
						// Validates input
						if (description.contains(","))
						{
							JOptionPane.showMessageDialog(null, "Descriptions cannot contain commas");
						}
						else
						{
							// Searches for a given description
							ArrayList<Appointment> foundApps = AppUtil.searchDescription(description);
							if (foundApps.size() > 0)
							{
								Run_Program.display(foundApps);
								dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No Appointments Found");
							}
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
