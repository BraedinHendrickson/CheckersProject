package checkers;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/***************************************************
 * The class for the option menu.
 *
 ************************************************/
public class PopupMenu {
	
	/** The variable for the dimensions. */
	private Dimension screenSize;
	
	/** The containers for the pop up. */
	private Container cp;
	
	/** The array of options. */
	private String[] description;
	
	/** The frame of the pop up/ */
	private final JFrame frame;
	
	/** The panel of the pop up. */
	private JPanel panel;
	
	/** The label. */
	private JLabel label;
	
	/** The text field. */
	private final JTextField textField;
	
	/** The combo box for the options. */
	private JComboBox<String> cBox;
	
	/** The combo box for color zero. */
	private JComboBox<String> cBoxZero;
	
	/** The combo box for color one. */
	private JComboBox<String> cBoxOne;
	
	/** The button. */
	private JButton button;
	
	/** Variable for whether or not is new game. */
	private boolean newGame;
	
	/** Variable for whether or not is running. */
	private boolean running;
	
	/** Variable for helper. */
	private boolean helper;
	
	/** Int used to assign color zero. */
	private int colorSelectZero;
	
	/** Int used to assign color one. */
	private int colorSelectOne;
	
	/** Int value for count. */
	private int count;

	/***************************************************************
	 * The class that handles the creation and function of
	 * the drop down menus.
	 *************************************************************/
	
	public PopupMenu() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		description = new String[5];
		description[0] = "Return to Game";
		description[4] = "Start New Game";
		description[2] = "Move Helper Settings";
		description[3] = "Adjust Colors";
		description[1] = "Request Fluffy Bunny";
		
		frame = new JFrame("Opion Menu");
		frame.setAlwaysOnTop(true);
		
		panel = new JPanel();
		panel.setSize(new Dimension(300, 200));
		
		label = new JLabel();
		
		textField = new JTextField(25);
		textField.setText("Select an Option Below");
		
		cBox = new JComboBox<String>();
		cBoxZero = new JComboBox<String>();
		cBoxOne = new JComboBox<String>();
		
		button = new JButton("Select");
		
		newGame = false;
		running = false;
		helper = false;
		
		colorSelectZero = 1;
		colorSelectOne = 0;
		count = 0;
		
		init();
		    
		panel.add(label);
		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(300, 230);
		frame.setLocation(670, 155);
		frame.setUndecorated(false);
		frame.setVisible(false);

	}
	
	/***********************************************************
	 * Initializes the Java combo boxes.
	 **********************************************************/
	public void init() {
	    for (int i = 0; i < 5; i++) {
	    	cBox.addItem(description[count++]);
	    }
	    
	    cBoxZero.addItem("Red");
		cBoxZero.addItem("Black");
		cBoxZero.addItem("White");
		cBoxZero.addItem("Blue");
		cBoxZero.addItem("Gray");
		cBoxZero.addItem("Yellow");
		cBoxZero.addItem("Pink");
		cBoxZero.addItem("Green");
		cBoxZero.addItem("Orange");
		
		cBoxOne.addItem("Red");
		cBoxOne.addItem("Black");
		cBoxOne.addItem("White");
		cBoxOne.addItem("Blue");
		cBoxOne.addItem("Gray");
		cBoxOne.addItem("Yellow");
		cBoxOne.addItem("Pink");
		cBoxOne.addItem("Green");
		cBoxOne.addItem("Orange");
	    
	    
	    textField.setEditable(false);
	   
	    cBox.addActionListener(new ActionListener() {
	      @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	        textField.setText("index: " + cBox.getSelectedIndex() + "   "
	            + ((JComboBox<String>) e.getSource()).getSelectedItem());
	      }
	    });

	    cp = frame.getContentPane();
	    cp.setLayout(new FlowLayout());
	    cp.add(textField);
	    cp.add(cBox);
	    cp.add(button);
	  }
	
	void addBoardListener(ActionListener listenForBoardButton) {
		button.addActionListener(listenForBoardButton);
		cBoxZero.addActionListener(listenForBoardButton);
		cBoxOne.addActionListener(listenForBoardButton);
	}
	
	
	
	/************************************************************
	 * Performs and option based on which option is selected in
	 * the menu.
	 * @param i The value used to decide which operation is
	 * performed.
	 ***********************************************************/
	public void performOperation(int i) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		
		if (i == 0) {
			close();
			
		} else if (i == 1) {
			label.setText(null);
			ImageIcon icon = new ImageIcon("./src/fluffybunny.jpg");
					//(ClassLoader.getSystemResource("fluffyBunny.jpg"));
			label.setIcon(icon);

			Timer timer = new Timer(); 
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					label.setIcon(null);
					label.setText("Sorry About That...");
					
					Timer timer2 = new Timer(); 
					timer2.schedule(new TimerTask() {
						@Override
						public void run() {
							panel.remove(label);
							panel.revalidate();
							panel.repaint();
						}
					}, 2000);
					
				}
			}, 2000);
		
		// Helper settings
		} else if (i == 2) {
			
			JButton helperButton = new JButton();
			panel.add(label);
			panel.add(helperButton);
			
			if (helper) {
				label.setText("Currently Active");
			} else {
				label.setText("Currently Not Active");
			}
			
			
			helperButton.addActionListener(new ActionListener() {
			      @SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e) {
			    	  helper = !(helper);
			    	  if (helper) {
			    		  label.setText("    Currently Active   ");
			   
			    	  } else {
			    		  label.setText("Currently Not Active");
			    		
			    	  }
			      }
			    });
			
		// Board color options
		} else if (i == 3) {

			panel.add(cBoxZero);
			panel.add(cBoxOne);	
			panel.revalidate();
			panel.repaint();
			
		} else if (i == 4) {
			newGame = true;
			running = false;
			
			close();
		} else {
			System.out.println("hello");
		}
		
	}
	
	/******************************************************************
	 * Closes the window.
	 ****************************************************************/
	public void close() {
		if (frame != null) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
	/******************************************************************
	 * GETTERS.
	 *********************************************************************/
	
	/**********************************************************
	 * Gets the integer code number for color zero.
	 * @return Integer for color zero.
	 **********************************************************/
	public int getColorSelectZero() {
		return colorSelectZero;
	}
	
	/**********************************************************
	 * Gets the integer code number for color one.
	 * @return Integer for color one.
	 ***********************************************************/
	public int getColorSelectOne() {
		return colorSelectOne;
	}
	
	/******************************************************
	 * Gets the helper.
	 * @return The helper.
	 *******************************************************/
	public boolean getHelper() {
		return helper;
	}
	
	/********************************************************
	 * Returns a boolean of whether or not it is a new game.
	 * @return Boolean of whether or not is new game.
	 *****************************************************/
	public boolean getNewGame() {
		return newGame;
	}
	
	/*******************************************************
	 * Returns whether or not the game is running.
	 * @return Boolean for whether or not the game is running.
	 *********************************************************/
	public boolean getRunning() {
		return running;
	}
	
	/*******************************************************
	 * Gets the select button.
	 * @return The select button.
	 *******************************************************/
	public JButton getSelectButton() {
		return button;
	}
	
	/*******************************************************
	 * Gets CBoxZero.
	 * @return Java Combo Box cBoxZero.
	 *********************************************************/
	public JComboBox<String> getCBoxZero() {
		return cBoxZero;
	}
	
	/********************************************************
	 * Gets CBoxOne.
	 * @return Java Combo Box cBoxOne. 
	 ********************************************************/
	public JComboBox<String> getCBoxOne() {
		return cBoxOne;
	}
	
	/********************************************************
	 * Gets option
	 * @return The textField.
	 *******************************************************/
	public String getOption() {
		return textField.getText();
	}

	
	/******************************************************************
	 * SETTERS.
	 ********************************************************************/
	
	/******************************************************************
	 * Sets color select zero.
	 * @param x Integer for which color to be selected.
	 *******************************************************************/
	public void setColorSelectZero(int x) {
		colorSelectZero = x;
	}
	
	/******************************************************************
	 * Sets color select one.
	 * @param x Integer for which color to be selected.
	 ****************************************************************/
	public void setColorSelectOne(int x) {
		colorSelectOne = x;
	}

	/****************************************************************
	 * Sets helper to true or not.
	 * @param x Boolean for whether or not helper is true or not.
	 ****************************************************************/
	public void setHelper(boolean x) {
		helper = x;
	}
	
	/*************************************************************
	 * Sets newGame to false.
	 ************************************************************/
	public void setNewGame() {
		newGame = false;
	}
	
	/**************************************************************
	 * Sets running to true. 
	 **************************************************************/
	public void setRunning() {
		running = true;
	}
	
	/*********************************************************
	 * Sets frame visibility to true.
	 ***********************************************************/
	public void setVisible() {
		frame.setVisible(true);
	}
	
	/********************************************************
	 * Sets cBox index to zero.
	 *********************************************************/
	public void setCBoxIndex() {
		cBox.setSelectedIndex(0);
	}
	
	/*********************************************************
	 * Sets the cBox intro.
	 **********************************************************/
	public void setCBoxIntro() {
		textField.setText("Select an Option Below");
	}
	
	/***********************************************************
	 * Clears out the content in the panel.
	 **********************************************************/
	public void clearPanel() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	

}