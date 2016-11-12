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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopupMenu {
	
	Dimension screenSize;
	Container cp;
	private String[] description;
	private final JFrame frame;
	private final JTextField textField;
	private JComboBox<String> cBox;
	private JButton button;
	private int count;
	private boolean newGame;
	private boolean running;


	public PopupMenu() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		description = new String[5];
		description[0] = "Return to Game";
		description[4] = "Start New Game";
		description[2] = "Move Helper Settings";
		description[3] = "Adjust Colors & Speed";
		description[1] = "Request Fluffy Bunny";
		frame = new JFrame("Opion Menu");
		frame.setAlwaysOnTop(true);
		textField = new JTextField(25);
		textField.setText("Select an Option Below");
		cBox = new JComboBox<String>();
		button = new JButton("Select");
		count = 0;
		newGame = false;
		running = false;

		    
		init();
		    

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(300, 230);
		frame.setLocation(670, 155);
		frame.setUndecorated(false);
		frame.setVisible(false);

	}
	
	public void init() {
	    for (int i = 0; i < 5; i++)
	    cBox.addItem(description[count++]);
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
	}
	
	
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
	
	public String getOption() {
		return textField.getText();
	}
	
	public void performOperation(int i) {
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(300, 200));
		JLabel label = new JLabel();
		panel.add(label);
		frame.add(panel);
		
		
		if (i == 0) {
			close();
			
		} else if (i == 1) {
			label.setText(null);
			ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("fluffyBunny.jpg"));
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
		
		} else if (i == 2) {
			
		} else if (i == 3) {
			
		} else if (i == 4) {
			newGame = true;
			running = false;
			close();
		} else {
			System.out.println("hello");
		}
		
	}
	
	public boolean getNewGame() {
		return newGame;
	}
	
	public void setNewGame() {
		newGame = false;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public void setRunning() {
		running = true;
	}
	

}
