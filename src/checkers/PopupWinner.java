package checkers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
	
	/** The variable that controls the dimensions. */
	private Dimension screenSize;
	
	/** The container that holds the objects. */
	private Container cp;
	
	/** An array that holds description. */
	private String[] description;
	
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
	
	/** The count. */
	private int count;
	
	/***********************************************************
	 * Constructor.
	 *******************************************************/
	public PopupWinner() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		alphabet = new String[26];
		
		for (int i = 65;i <= 90;i++) {
			char c = ((char)i);
		   alphabet[i - 65] = String.valueOf(c);
		}
	
		frame = new JFrame("Congratulations on winning!!!");
		frame.setAlwaysOnTop(true);
		panel = new JPanel();
		panel.setSize(new Dimension(300, 200));
		frame.add(panel);
		textField = new JTextField(25);
		textField.setText("Please enter a three character name for rankings.");
		cBox1 = new JComboBox<String>();
		cBox2 = new JComboBox<String>();
		cBox3 = new JComboBox<String>();
		button = new JButton("Select");
		count = 0;
		
		
		init();
	    

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(300, 130);
		frame.setLocation(670, 105);
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
	      @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	    	  textField.setText("Selected Name:   " 
		+  cBox1.getSelectedItem() + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	
	cBox2.addActionListener(new ActionListener() {
	      @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	    	  textField.setText("Selected Name:   " 
		+  cBox1.getSelectedItem() + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	
	cBox3.addActionListener(new ActionListener() {
	      @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	    	  textField.setText("Selected Name:   "
		+  cBox1.getSelectedItem() + cBox2.getSelectedItem() + cBox3.getSelectedItem());
	      }
	    });
	}

	
	
	void addBoardListener(ActionListener listenForBoardButton) {
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
	
	public void setVisible() {
		frame.setVisible(true);
	}
	
	public JButton getSelectButton() {
		return button;
	}
	
	/*********************************************************
	 * Creates a string from the letters chosen in the boxes.
	 * @return A String of three letters.
	 ********************************************************/
	public String selection() {
		String s = "";
		s = (String)cBox1.getSelectedItem() 
				+ cBox2.getSelectedItem() + cBox3.getSelectedItem();
		return s;
	}
}