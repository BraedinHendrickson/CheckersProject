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

public class PopupMenu {
	
	private Dimension screenSize;
	private Container cp;
	private String[] description;
	private final JFrame frame;
	private JPanel panel;
	private JLabel label;
	private final JTextField textField;
	private JComboBox<String> cBox;
	private JComboBox<String> cBoxZero;
	private JComboBox<String> cBoxOne;
	private JButton button;
	private boolean newGame;
	private boolean running;
	private boolean helper;
	private int colorSelectZero;
	private int colorSelectOne;
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
			ImageIcon icon = new ImageIcon
					(ClassLoader.getSystemResource("fluffyBunny.jpg"));
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
	
	public int getColorSelectZero() {
		return colorSelectZero;
	}
	
	public int getColorSelectOne() {
		return colorSelectOne;
	}
	
	public boolean getHelper() {
		return helper;
	}
	
	public boolean getNewGame() {
		return newGame;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public JButton getSelectButton() {
		return button;
	}
	
	public JComboBox<String> getCBoxZero() {
		return cBoxZero;
	}
	
	public JComboBox<String> getCBoxOne() {
		return cBoxOne;
	}
	
	public String getOption() {
		return textField.getText();
	}

	
	/******************************************************************
	 * SETTERS.
	 ********************************************************************/
	
	public void setColorSelectZero(int x) {
		colorSelectZero = x;
	}
	
	public void setColorSelectOne(int x) {
		colorSelectOne = x;
	}

	public void setHelper(boolean x) {
		helper = x;
	}
	
	public void setNewGame() {
		newGame = false;
	}
	
	public void setRunning() {
		running = true;
	}
	
	public void setVisible() {
		frame.setVisible(true);
	}
	
	public void setCBoxIndex() {
		cBox.setSelectedIndex(0);
	}
	
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