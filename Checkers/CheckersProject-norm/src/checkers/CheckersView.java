package checkers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer; 

/***************************************************************************
 * 
 **************************************************************************/
@SuppressWarnings("serial")
public class CheckersView extends JPanel {

	/** Final value 8. */
	private static final int BOARDSIZE = 8;
	/** Final value 0. */
	private static final int ZERO = 0;
	/** Final value 2. */
	private static final int TWO = 2;
	/** Final value 80. */
	private static final int EIGHTZERO = 80;
	/** Final value 300. */
	private static final int THREEZEROZERO = 300;

	/** Icon for red label. */
	private ImageIcon redCirclePlain = new ImageIcon(ClassLoader.getSystemResource("RedCircle20.png"));
	/** Icon for red single disk. */
	private ImageIcon redCircle = new ImageIcon(ClassLoader.getSystemResource("BlackSquareRS.png"));
	/** Icon for red double disk. */
	private ImageIcon redCircleDouble = new ImageIcon(ClassLoader.getSystemResource("BlackSquareRD.png"));
	/** Icon for black label. */
	private ImageIcon blackCirclePlain = new ImageIcon(ClassLoader.getSystemResource("BlackCircle20.png"));
	/** Icon for black single disk. */
	private ImageIcon blackCircle = new ImageIcon(ClassLoader.getSystemResource("BlackSquareBS.png"));
	/** Icon for black double disk. */
	private ImageIcon blackCircleDouble = new ImageIcon(ClassLoader.getSystemResource("BlackSquareBD.png"));
	/** Icon for red tile. */
	private ImageIcon redSquare = new ImageIcon(ClassLoader.getSystemResource("RedSquare.png"));
	/** Icon for black tile. */
	private ImageIcon blackSquare = new ImageIcon(ClassLoader.getSystemResource("BlackSquare.png"));
	private ImageIcon[] iconList = new ImageIcon[8];
	
	
	
	/** The playing board. */
	private JButton[][] board;

	/** Main panel. */
	private JPanel center = new JPanel();
	/** North panel, place on right panel. */
	private JPanel top = new JPanel();
	/** South panel, place on right panel. */
	private JPanel bottom = new JPanel();
	/** Left panel, place on center panel. */
	private JPanel left = new JPanel();
	/** Right panel, placed on center panel. */
	private JPanel right = new JPanel();
	/** text panel, placed on right panel. */
	private JPanel text = new JPanel();

	/** Label displays the current players turn. */
	private JLabel playerTurn = new JLabel("B e g i n  T h e  G a m e");
	/** Label displays left icon piece next to playerTurn label. */
	private JLabel playerTurnIconLeft = new JLabel();
	/** Label displays right icon piece next to playerTurn label. */
	private JLabel playerTurnIconRight = new JLabel();

	/** The label during reds turn. */
	private TurnLabel labelRed;
	/** The label during blacks turn. */
	private TurnLabel labelWhite;

	/** The timer during reds turn. */
	private Timer timerRed;
	/** The timer during blacks turn. */
	private Timer timerWhite;
	private Timer[] timers = new Timer[2];
	


	/** Options menu. */
	private JButton options = new JButton("Options");
	/** Current info of game. */
	private JButton gameInfo = new JButton("Session");
	/** Previous game histories. */
	private JButton scoreBoard = new JButton("Rankings");

	/** JTextArea for displaying text information to user. */
	private JTextArea ta = new JTextArea(31, 27);
	/**
	 * JScollPane allows scrolling area for the information in the text area.
	 */
	private JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	/***********************************************************************
	 * 
	 ***********************************************************************/
	public CheckersView() {
		
		iconList[0] = blackCirclePlain;
		iconList[1] = redCirclePlain;;
		iconList[2] = redSquare;
		iconList[3] = blackSquare;
		iconList[4] = redCircle;
		iconList[5] = blackCircle;
		iconList[6] = redCircleDouble;
		iconList[7] = blackCircleDouble;

		board = new JButton[BOARDSIZE][BOARDSIZE];

		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col] = new JButton();
				board[row][col].setPreferredSize(new Dimension(EIGHTZERO, EIGHTZERO));
				left.add(board[row][col]);
			}
		}

		// For the blinking labels.
		labelRed = new TurnLabel(playerTurn, Player.Red);
		labelWhite = new TurnLabel(playerTurn, Player.White);
		timerRed = new Timer(THREEZEROZERO, labelRed);
		timerWhite = new Timer(THREEZEROZERO, labelWhite);
		timers[1] = timerRed;
		timers[0] = timerWhite;

		// Layouts for the panels.
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		left.setLayout(new GridLayout(BOARDSIZE, BOARDSIZE));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

		// Set preferred sizes and preferences.
		//ta.setPreferredSize(new Dimension(300, 500));
		ta.setEditable(false);
		right.setPreferredSize(new Dimension(350, 500));

		// Set label alignments.
		playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurn.setVerticalAlignment(SwingConstants.CENTER);
		playerTurnIconLeft.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurnIconLeft.setVerticalAlignment(SwingConstants.CENTER);

		// Add labels to panel.
		top.add(playerTurnIconLeft);
		top.add(playerTurn);
		top.add(playerTurnIconRight);

		// Add buttons to panel
		bottom.add(options);
		bottom.add(Box.createRigidArea(new Dimension(10, 0)));
		bottom.add(gameInfo);
		bottom.add(Box.createRigidArea(new Dimension(10, 0)));
		bottom.add(scoreBoard);

		// Add scroll pane to panel.
		text.add(sp);

		// Add content to panel.
		right.add(Box.createRigidArea(new Dimension(0, 30)));
		right.add(top);
		right.add(Box.createRigidArea(new Dimension(0, 30)));
		right.add(bottom);
		right.add(Box.createRigidArea(new Dimension(0, 10)));
		right.add(text);

		// Add sub panels to center panel.
		center.add(left);
		center.add(right);

		// Add center panel to frame.
		add(center, BorderLayout.CENTER);
	}

	void addBoardListener(ActionListener listenForBoardButton) {

		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col].addActionListener(listenForBoardButton);
			}
		}
		options.addActionListener(listenForBoardButton);
		gameInfo.addActionListener(listenForBoardButton);
		scoreBoard.addActionListener(listenForBoardButton);
	}
	
	public String getTextArea() {
		return ta.getText();
	}
	
	public void setTextArea(String str) {
		ta.setText(str);
	}
	
	public JButton getButton(int row, int col) {
		return board[row][col];
	}
	
	public JButton getOptionButton() {
		return options;
	}
	
	public JButton getGameInfoButton() {
		return gameInfo;
	}
	
	public JButton getScoreBoardButton() {
		return scoreBoard;
	}

	public void setButtonIcon(int row, int col, int image) {
		board[row][col].setIcon(iconList[image]);
	}
	
	public void setTurnLabel(String str) {
		playerTurn.setText(str);
	}
	
	public void setTurnImage(int image) {
		playerTurnIconLeft.setIcon(iconList[image]);
		playerTurnIconRight.setIcon(iconList[image]);
	}
	
	public void setTimer(int i) {
		timerRed.stop();
		timerWhite.stop();
		timers[i].start();
	}
	
	public void gameOver() {
		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col].setEnabled(false);
			}
		}
	}
	
	
}
