package checkers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class CheckersGUI {

public static void main(String[] args) {
		
		JFrame frame = new JFrame("Checkers!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckersPanel panel = new CheckersPanel();
		 
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
	    menuBar.add(menu);
	    
	    JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
      
        JMenuItem item2 = new JMenuItem("New Game: Keep Previous Settings");
        item2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//panel.newGame(true);
            }
        });
        
        JMenuItem item4 = new JMenuItem("New Game: Change Number of Players/Color");
        item4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//panel.newGame(false);
            }
        });
        
        
       
        menu.add(item2);
        menu.add(item4);
        menu.add(item);
        frame.setJMenuBar(menuBar);
        
		frame.getContentPane().add(panel);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight()));

		frame.pack();
		frame.setVisible(true);
	}
	
}
