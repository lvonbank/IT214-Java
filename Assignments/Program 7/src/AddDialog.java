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
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDialog dialog = new AddDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDialog() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				Run_Program.enable();
			}
		});
		setTitle("Add Appointment");
		setBounds(100, 100, 500, 329);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Adds a date panel
		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(15, 16, 445, 56);
		contentPanel.add(datePanel);
		
		// Adds a time panel
		TimePanel timePanel = new TimePanel();
		timePanel.setBounds(15, 88, 445, 56);
		contentPanel.add(timePanel);
		
		// Adds a description panel
		DescriptionPanel descriptionPanel = new DescriptionPanel();
		descriptionPanel.setBounds(15, 159, 445, 63);
		contentPanel.add(descriptionPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Gets all needed info to construct app
						String occurrence = timePanel.getInstance();
						String date = datePanel.getSelection();
						String time = timePanel.getSelection();
						String description = DescriptionPanel.getSelection();
						
						// Validates date
						Date date_time = DateUtil.toDate(date+","+time);
						
						if (date_time == null)
						{
							JOptionPane.showMessageDialog(null, "Invalid Date");
						}
						else if (description.contains(","))
						{
							JOptionPane.showMessageDialog(null, "Descriptions cannot contain commas");
						}
						else
						{
							// Adds app to program data
							Appointment app = DataUtil.constructAppointment(occurrence, date_time, description);
							AppUtil.addAppointment(app);
							dispose();
							JOptionPane.showMessageDialog(null, app.toString()+"\r\nWas Added Successfully ");
							Run_Program.enable();
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
