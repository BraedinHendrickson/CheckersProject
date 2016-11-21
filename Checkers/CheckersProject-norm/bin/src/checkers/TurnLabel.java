package checkers;

//http://stackoverflow.com/questions/17524080/enable-blinking-
//of-jlabel-3-times-and-then-remain-invisible-disappear

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************************************************
* Allows label to blink.
*********************************************************************/
public class TurnLabel implements ActionListener {

	/** JLabel to be modified. */
	private javax.swing.JLabel label;
	
	/** Color, type red. */
	private java.awt.Color cor1 = java.awt.Color.red;
	
	/** Color, type black. */
	private java.awt.Color cor2 = java.awt.Color.black;
	
	/** Color, type gray. */
	private java.awt.Color cor3 = java.awt.Color.gray;
	
	/** Counter to be incremented. */
	private int count;
	
	/** The player whose turn it is. */
	private Player player;

	/**********************************************************************
	 * Constructor, sets label and player for class.
	 * 
	 * @param  xlabel   Label that will blink.
	 * @param  xplayer  The current player.
	 *********************************************************************/
	public TurnLabel(final javax.swing.JLabel xlabel, final Player xplayer) {
		label = xlabel;
		player = xplayer;
	}

	/**********************************************************************
	 * Constantly increments the count to switch between colors
	 * creating the blink effect.
	 * 
	 * @param  e  Action event.
	 *********************************************************************/
	public final void actionPerformed(final ActionEvent e) {
	
		if (player == Player.Red) {
			if (count % 2 == 0) {
				label.setForeground(cor1);
			} else {
				label.setForeground(cor3);
			}
			count++;
			
		} else {
			if (count % 2 == 0) {
				label.setForeground(cor2);
			} else {
				label.setForeground(cor3);
			}
			count++;
		}
	}

}
