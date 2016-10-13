

package checkers;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/*******************************************************************************
 * Graphical user interface for checkers.
 ******************************************************************************/
final class CheckersGUI {

	/***************************************************************************
	 * Constructor.
	 **************************************************************************/
	private CheckersGUI() {
		// Prevent Instantiation 
		throw new AssertionError("Instantiating utility class...");
		// if this ever *is* called
	}

	/***************************************************************************
	 * MAIN METHOD.
	 * 
	 * @param  args  
	 **************************************************************************/
	public static void main(final String[] args) {
		
		JFrame frame = new JFrame("Checkers!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckersPanel panel = new CheckersPanel();
        
		frame.getContentPane().add(panel);

		// Frame sizing adjustment.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (screenSize.getWidth()), (int) 
				(screenSize.getHeight()));

		frame.pack();
		frame.setVisible(true);
	}
	
}
