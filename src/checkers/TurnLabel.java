package checkers;

// http://stackoverflow.com/questions/17524080/enable-blinking-of-jlabel-3-times-and-then-remain-invisible-disappear

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnLabel implements ActionListener {

	private javax.swing.JLabel label;
	private java.awt.Color cor1 = java.awt.Color.red;
	private java.awt.Color cor2 = java.awt.Color.black;
	private java.awt.Color cor3 = java.awt.Color.gray;
	private int count;
	private Player player;

	public TurnLabel(javax.swing.JLabel label, Player player) {
		this.label = label;
		this.player = player;
	}

	public void actionPerformed(ActionEvent e) {
		if (player == Player.Other) {
			if (count % 2 == 0)
				label.setForeground(cor2);
			else
				label.setForeground(cor3);
			count++;
		}
		else {
			if (count % 2 == 0)
				label.setForeground(cor1);
			else
				label.setForeground(cor3);
			count++;
		}
	}

}
