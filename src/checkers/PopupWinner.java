package checkers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/******************************************************
 * The pop up for when someone wins.
 *****************************************************/
public class PopupWinner {
	/** An array that holds the letters of the alphabet. */
	private String[] alphabet;
	
	/** The container that holds the objects. */
	private Container cp;
	
	/** The frame for the pop up. */
	private final JFrame frame;
	
	/** The panel of the pop up. */
	private JPanel panel;
	
	/** The text field in the pop up. */
	private final JTextField textField;
	
	/** One of the combo boxes. */
	private JComboBox<String> cBox1;
	
	/** One of the combo boxes. */
	private JComboBox<String> cBox2;
	
	/** One of the combo boxes. */
	private JComboBox<String> cBox3;
	
	/** The button. */
	private JButton button;
	
	/** Final value 25. */
	private static final int TWOFIVE = 25;
	
	/** Final value 26. */
	private static final int TWOSIX = 26;
	
	/** Final value 65. */
	private static final int SIXFIVE = 65;
	
	/** Final value 90. */
	private static final int NINEZERO = 90;
	
	/** Final value 130. */
	private static final int ONETHREEZERO = 130;
	
	/** Final value 184. */
	private static final int ONEEIGHTFOUR = 184;
	
	/** Final value 200. */
	private static final int TWOOZEROZERO = 200;
	
	/** Final value 291. */
	private static final int TWONINEONE = 291;
	
	/** Final value 300. */
	private static final int THREEZEROZERO = 300;

	
	/***********************************************************
	 * Constructor.
	 *******************************************************/
	public PopupWinner() {
		
		alphabet = new String[TWOSIX];
		
		for (int i = SIXFIVE; i <= NINEZERO; i++) {
			char c = ((char) i);
		   alphabet[i - SIXFIVE] = String.valueOf(c);
		}
	
		frame = new JFrame("Congratulations on winning!!!");
		frame.setAlwaysOnTop(true);
		panel = new JPanel();
		panel.setSize(new Dimension(THREEZEROZERO, TWOOZEROZERO));
		frame.add(panel);
		textField = new JTextField(TWOFIVE);
		textField.setText("Please enter a three character name for rankings.");
		cBox1 = new JComboBox<String>();
		cBox2 = new JComboBox<String>();
		cBox3 = new JComboBox<String>();
		button = new JButton("Select");
		
		
		init();
	    

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(THREEZEROZERO, ONETHREEZERO);
		frame.setLocation(ONEEIGHTFOUR, TWONINEONE);
		frame.setUndecorated(false);
		frame.setVisible(false);
		
	}
	
	/******************************************************
	 * Creates Java Combo Boxes and fills them with letters
	 * to allow players to enter their initials for 
	 * score keeping.
	 *********************************************************/
	public void init() {
	    for (String s : alphabet) {
	    	cBox1.addItem(s);
	    	cBox2.addItem(s);
	    	cBox3.addItem(s);
	    }
	    textField.setEditable(false);
  
	    cp = frame.getContentPane();
	    cp.setLayout(new FlowLayout());
	    cp.add(textField);
	    cp.add(cBox1);
	    cp.add(cBox2);
	    cp.add(cBox3);
	    cp.add(button);
	   

	cBox1.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
	    	  textField.setText("Selected Name:   " +  cBox1.getSelectedItem() 
	    	  + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	
	cBox2.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
	    	  textField.setText("Selected Name:   " +  cBox1.getSelectedItem() 
	    	  + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	
	cBox3.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
	    	  textField.setText("Selected Name:   " +  cBox1.getSelectedItem() 
	    	  + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	}

	
	/*****************************************************
	 * Adds action listener.
	 * 
	 * @param listenForBoardButton  the action listener. 
	 *****************************************************/
	final void addBoardListener(final ActionListener listenForBoardButton) {
		button.addActionListener(listenForBoardButton);
	}
	
	/**************************************************************
	 * Closes the window.
	 ***********************************************************/
	public void close() {
		if (frame != null) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	/**********************************************************
	 * Sets the frame to visible.
	 *********************************************************/
	public void setVisible() {
		frame.setVisible(true);
	}
	
	/********************************************************
	 * Gets the select button.
	 * @return The select button.
	 *********************************************************/
	public JButton getSelectButton() {
		return button;
	}
	
	/*********************************************************
	 * Creates a string from the letters chosen in the boxes.
	 * @return A String of three letters.
	 ********************************************************/
	public String selection() {
		String s = "";
		s = (String) cBox1.getSelectedItem() 
				+ cBox2.getSelectedItem() + cBox3.getSelectedItem();
		return s;
	}
}