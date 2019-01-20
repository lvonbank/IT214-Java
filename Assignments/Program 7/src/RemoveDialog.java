/**
 * @author Levi VonBank
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RemoveDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveDialog dialog = new RemoveDialog(new ArrayList<Appointment>(){});
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoveDialog(ArrayList<Appointment> apps) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Run_Program.enable();
			}
		});
		setTitle("Delete Appointment");
		setBounds(100, 100, 549, 245);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the one to delete");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setBounds(147, 16, 222, 39);
		contentPanel.add(lblNewLabel);
		
		JComboBox<Appointment> cbItems = new JComboBox<Appointment>();
		cbItems.setBounds(81, 79, 380, 26);
		contentPanel.add(cbItems);
		
		// Adds apps arrayList data to combo box
		for (int index = 0; index < apps.size(); index++)
		{
			cbItems.addItem(apps.get(index));
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Removes the selected app from program data
						AppUtil.removeAppointment((Appointment) cbItems.getSelectedItem());
						dispose();
						// Presents a confirmation on deletion
						JOptionPane.showMessageDialog(null, cbItems.getSelectedItem()+"\r\nWas Deleted Successfully ");
						Run_Program.enable();
					}
				});
				deleteButton.setActionCommand("Delete");
				buttonPane.add(deleteButton);
				getRootPane().setDefaultButton(deleteButton);
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
