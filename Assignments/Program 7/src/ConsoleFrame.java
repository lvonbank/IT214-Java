/**
 * @author Levi VonBank
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConsoleFrame extends JFrame {

	private static JPanel contentPane;
	private JTextArea display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsoleFrame frame = new ConsoleFrame();
				    frame.setVisible( true );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsoleFrame() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Run_Program.enable();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 405);
		contentPane = new JPanel();
		contentPane.setBorder( new TitledBorder ( new EtchedBorder (), "Console" ) );
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	    // create the panel components
	    display = new JTextArea ();
	    display.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    display.setEditable( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane( display );
	    scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

	    //Add Textarea in to panel
	    contentPane.add( scroll );
	}
	
	/**
	 * Adds data to Display
	 * @param str the item to add
	 */
	public void print(String str)
	{
		display.append(str + "\r\n");
	}
	
	/**
	 * Adds a dialog title
	 * @param str the item to add
	 */
	public void title(String str)
	{
		setTitle(str);
	}
}
