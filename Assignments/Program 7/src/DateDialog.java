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
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DateDialog dialog = new DateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DateDialog() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Run_Program.enable();
			}
		});
		setTitle("Enter Date");
		setBounds(100, 100, 485, 180);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(15, 16, 445, 56);
		contentPanel.add(datePanel);
		{		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String entry = datePanel.getSelection();
						
						// Constructs date for validation
						Date date = DateUtil.toDate(entry);
						
						// Determines how to handle date outcome
						if (date == null)
						{
							JOptionPane.showMessageDialog(null, "Invalid Date");
						}
						else
						{
							// Searches for apps happening on given date
							ArrayList<Appointment> apps = AppUtil.searchOccurrence(date);
							if (apps.size() > 0)
							{
								Run_Program.display(apps);
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
