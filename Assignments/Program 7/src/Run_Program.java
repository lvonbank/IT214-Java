/**
 * @author Levi VonBank
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Run_Program {

	private static JFrame frmAppointmentCalendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					Run_Program window = new Run_Program();
					window.frmAppointmentCalendar.setVisible(true);
					
					AppUtil.buildAppointments();
				}
				catch (FileNotFoundException exception)
				{
					JOptionPane.showMessageDialog(null, "File[s] not found.\r\nA new file will be created.");
				}
				catch (NoSuchElementException exception)
				{
					JOptionPane.showMessageDialog(null, "File[s] contents invalid.");
				}
				catch (IOException exception)
				{
					exception.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Run_Program() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppointmentCalendar = new JFrame();
		frmAppointmentCalendar.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				save();
			}
		});
		frmAppointmentCalendar.setTitle("Appointment Calendar");
		frmAppointmentCalendar.setBackground(Color.WHITE);
		frmAppointmentCalendar.getContentPane().setBackground(SystemColor.menu);
		frmAppointmentCalendar.setBounds(100, 100, 950, 750);
		frmAppointmentCalendar.setResizable(false);
		frmAppointmentCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppointmentCalendar.getContentPane().setLayout(null);
		
		// Adds a today button with listener
		JButton btnToday = new JButton("Today");
		btnToday.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Search for dates occurring today
				Date currentDate = new Date();
				ArrayList<Appointment> foundApps = AppUtil.searchOccurrence(currentDate);
				display(foundApps, "Today's Appointments");
			}
		});
		btnToday.setBounds(265, 118, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnToday);
		
		// Adds a tomorrow button with listener
		JButton btnTomorrow = new JButton("Tomorrow");
		btnTomorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Search for dates occurring tomorrow
				Date currentDate = new Date();
				ArrayList<Appointment> foundApps = AppUtil.searchOccurrence(DateUtil.addDays(currentDate, 1));
				display(foundApps, "Tomorrow's Appointments");
			}
		});
		btnTomorrow.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTomorrow.setBounds(265, 186, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnTomorrow);
		
		// Adds a seven day button with listener
		JButton btnSevenDays = new JButton("Seven days (including today)");
		btnSevenDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Search for dates occurring in the next seven days
				Set<Appointment> foundApps = new TreeSet<Appointment>();
		    	Date currentDate = new Date();
		    	for (int index = 0; index < 7; index++)
				{
		    		foundApps.addAll(AppUtil.searchOccurrence(currentDate));
		    		currentDate = DateUtil.addDays(currentDate, 1);
				}
				display(foundApps, "Next Seven Day");
			}
		});
		btnSevenDays.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSevenDays.setBounds(265, 254, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnSevenDays);
		
		// Adds an search specific date button with listener
		JButton btnSpecificDate = new JButton("A specific date");
		btnSpecificDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateDialog dateDialog = new DateDialog();
				dateDialog.setVisible(true);
				disable();  // Disables Main Frame
			}
		});
		btnSpecificDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSpecificDate.setBounds(265, 320, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnSpecificDate);
		
		// Adds a cancel button with listener
		JButton btnCancelAppointment = new JButton("Cancel an appointment");
		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelDialog cancelDialog = new CancelDialog();
				cancelDialog.setVisible(true);
				disable();  // Disables Main Frame
			}
		});
		btnCancelAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCancelAppointment.setBounds(265, 440, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnCancelAppointment);
		
		// Adds an add appointment button with listener
		JButton btnAddAppointment = new JButton("Add an appointment");
		btnAddAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDialog addDialog = new AddDialog();
				addDialog.setVisible(true);
				disable();  // Disables Main Frame
			}
		});
		btnAddAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddAppointment.setBounds(265, 508, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnAddAppointment);
		
		// Adds a close button with listener
		JButton btnCloseProgram = new JButton("Close this program");
		btnCloseProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				frmAppointmentCalendar.dispose();
			}
		});
		btnCloseProgram.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCloseProgram.setBounds(265, 568, 200, 44);
		frmAppointmentCalendar.getContentPane().add(btnCloseProgram);
		
		// Adds a application title
		JLabel lblAppointmentCalendar = new JLabel("Appointment Calendar");
		lblAppointmentCalendar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblAppointmentCalendar.setBounds(265, 16, 402, 73);
		frmAppointmentCalendar.getContentPane().add(lblAppointmentCalendar);
		
		// Adds a warning label
		JLabel lblWarning = new JLabel("Warning if NOT closed properly data could be lost!!!");
		lblWarning.setFont(new Font("Monospaced", Font.PLAIN, 14));
		lblWarning.setBounds(258, 658, 420, 20);
		frmAppointmentCalendar.getContentPane().add(lblWarning);
		
		// Adds a save button with listener
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBounds(467, 568, 200, 44);
		frmAppointmentCalendar.getContentPane().add(btnSave);
		
		// Adds a description button with listener
		JButton btnADescription = new JButton("A description");
		btnADescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DescriptionDialog descriptionDialog = new DescriptionDialog();
				descriptionDialog.setVisible(true);
				disable();  // Disables Main Frame
			}
		});
		btnADescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnADescription.setBounds(265, 380, 402, 44);
		frmAppointmentCalendar.getContentPane().add(btnADescription);
	}
	
	/**
	 * Saves data
	 */
	public void save()
	{
		try 
		{
			// Writes all date to the text file then confirmation displays a message
			DataUtil.putData(AppUtil.getAllApps());
			JOptionPane.showMessageDialog(null, "Appointments were saved");
		} 
		catch (IOException e)
		{
			// Displays an error if data couldn't be written
			JOptionPane.showMessageDialog(null, "An Error occurred while saving");
		}
	}
	
	/**
	 * Prints data to a console frame
	 * @param <T> generic
	 * @param apps an arrayList of Appointment objects
	 * @param title the title on the frame
	 */
	public static <T> void display(Collection<T> list, String title)
	{
		if (list.size() > 0)
		{
			ConsoleFrame screen = new ConsoleFrame();
			screen.setVisible(true);
			disable();
			
			screen.title(title);
			for (T item : list) 
			{
				screen.print(item.toString());
	    	}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No Appointments Found");
			enable();
		}
	}	
	
	/**
	 * display helper prints data to a console frame
	 * @param <T> generic
	 * @param apps an arrayList of Appointment objects
	 */
	public static <T> void display(ArrayList<T> list)
	{
		display(list, "");
	}
	
	/**
	 * Enables main frame
	 */
	public static void enable()
	{
		frmAppointmentCalendar.setEnabled(true);
	}
	
	/**
	 * Disables main frame
	 */
	public static void disable()
	{
		frmAppointmentCalendar.setEnabled(false);
	}
}
