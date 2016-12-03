package checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	/** Final value 1. */
	private static final int ONE = 1;
	/** Final value 2. */
	private static final int TWO = 2;
	/** Final value 3. */
	private static final int THREE = 3;
	/** Final value 4. */
	private static final int FOUR = 4;
	/** Final value 5. */
	private static final int FIVE = 5;
	/** Final value 6. */
	private static final int SIX = 6;
	/** Final value 7. */
	private static final int SEVEN = 7;
	/** Final value 8. */
	private static final int EIGHT = 8;
	/** Final value 9. */
	private static final int NINE = 9;
	/** Final value 10. */
	private static final int TEN = 10;
	/** Final value 27. */
	private static final int TWOSEVEN = 27;
	/** Final value 30. */
	private static final int THREEZERO = 30;
	/** Final value 31. */
	private static final int THREEONE = 31;
	/** Final value 350. */
	private static final int THREEFIVEZERO = 350;
	/** Final value 500. */
	private static final int FIVEZEROZERO = 500;
	/** Final value 80. */
	private static final int EIGHTZERO = 80;
	/** Final value 300. */
	private static final int THREEZEROZERO = 300;

	/** Icon for red label. */
	private ImageIcon redCirclePlain = new 
			ImageIcon(ClassLoader.getSystemResource("RedCircle20.png"));
	/** Icon for red single disk. */
	private ImageIcon redCircle = new 
			ImageIcon(ClassLoader.getSystemResource("BlackSquareRST.png"));
	/** Icon for red double disk. */
	private ImageIcon redCircleDouble = new 
			ImageIcon(ClassLoader.getSystemResource("BlackSquareRDT.png"));
	/** Icon for black label. */
	private ImageIcon blackCirclePlain = new 
			ImageIcon(ClassLoader.getSystemResource("BlackCircle20.png"));
	/** Icon for black single disk. */
	private ImageIcon blackCircle = new 
			ImageIcon(ClassLoader.getSystemResource("BlackSquareBST.png"));
	/** Icon for black double disk. */
	private ImageIcon blackCircleDouble = new 
			ImageIcon(ClassLoader.getSystemResource("BlackSquareBDT.png"));

	/**  Array of icons. */
	private ImageIcon[] iconList = new ImageIcon[EIGHT];
	
	/** Array of colors. */
	private Color[] colorlist = new Color[NINE];
	
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
	/** Array of timers. */
	private Timer[] timers = new Timer[2];
	


	/** Options menu. */
	private JButton options = new JButton("Options");
	/** Current info of game. */
	private JButton gameInfo = new JButton("Session");
	/** Previous game histories. */
	private JButton scoreBoard = new JButton("Rankings");

	/** JTextArea for displaying text information to user. */
	private JTextArea ta = new JTextArea(THREEONE, TWOSEVEN);
	/**
	 * JScollPane allows scrolling area for the information in the text area.
	 */
	private JScrollPane sp = new 
			JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	/***********************************************************************
	 * 
	 ***********************************************************************/
	public CheckersView() {
		
		iconList[ZERO] = blackCirclePlain;
		iconList[ONE] = redCirclePlain;
		iconList[TWO] = null;
		iconList[THREE] = null;
		iconList[FOUR] = redCircle;
		iconList[FIVE] = blackCircle;
		iconList[SIX] = redCircleDouble;
		iconList[SEVEN] = blackCircleDouble;
		
		colorlist[ZERO] = Color.RED;
		colorlist[ONE] = Color.BLACK;
		colorlist[TWO] = Color.WHITE;
		colorlist[THREE] = Color.BLUE;
		colorlist[FOUR] = Color.GRAY;
		colorlist[FIVE] = Color.YELLOW;
		colorlist[SIX] = Color.PINK;
		colorlist[SEVEN] = Color.GREEN;
		colorlist[EIGHT] = Color.ORANGE;

		board = new JButton[BOARDSIZE][BOARDSIZE];

		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col] = new JButton();
				board[row][col].setPreferredSize(new 
						Dimension(EIGHTZERO, EIGHTZERO));
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
		right.setPreferredSize(new Dimension(THREEFIVEZERO, FIVEZEROZERO));

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
		bottom.add(Box.createRigidArea(new Dimension(TEN, ZERO)));
		bottom.add(gameInfo);
		bottom.add(Box.createRigidArea(new Dimension(TEN, ZERO)));
		bottom.add(scoreBoard);

		// Add scroll pane to panel.
		text.add(sp);

		// Add content to panel.
		right.add(Box.createRigidArea(new Dimension(ZERO, THREEZERO)));
		right.add(top);
		right.add(Box.createRigidArea(new Dimension(ZERO, THREEZERO)));
		right.add(bottom);
		right.add(Box.createRigidArea(new Dimension(ZERO, THREEZERO)));
		right.add(text);

		// Add sub panels to center panel.
		center.add(left);
		center.add(right);

		// Add center panel to frame.
		add(center, BorderLayout.CENTER);
	}
	
	/*****************************************************
	 * Adds action listeners to the board buttons.
	 * 
	 * @param listenForBoardButton  the action listener. 
	 *****************************************************/
	final void addBoardListener(final ActionListener listenForBoardButton) {

		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col].addActionListener(listenForBoardButton);
			}
		}
		options.addActionListener(listenForBoardButton);
		gameInfo.addActionListener(listenForBoardButton);
		scoreBoard.addActionListener(listenForBoardButton);
	}
	
	/*****************************************************
	 * Gets the text in the text area.
	 * @return String of text in area.
	 *****************************************************/
	public String getTextArea() {
		return ta.getText();
	}
	
	/*****************************************************
	 * Sets the text in the text area.
	 * @param str String that will be set.
	 ******************************************************/
	public void setTextArea(final String str) {
		ta.setText(str);
	}
	
	/*******************************************************
	 * Gets the JButton at the coordinates. 
	 * @param row The row of the button.
	 * @param col The column of the button. 
	 * @return The button at the coordinates.
	 *******************************************************/
	public JButton getButton(final int row, final int col) {
		return board[row][col];
	}
	
	/***************************************************
	 * Gets the option button.
	 * @return The option button.
	 *****************************************************/
	public JButton getOptionButton() {
		return options;
	}
	
	/*********************************************************
	 * Gets the game info button.
	 * @return The game info button.
	 **********************************************************/
	public JButton getGameInfoButton() {
		return gameInfo;
	}
	
	/***********************************************************
	 * Gets the score board button.
	 * @return The score board button.
	 **********************************************************/
	public JButton getScoreBoardButton() {
		return scoreBoard;
	}

	/********************************************************
	 * Sets the icon for the buttons.
	 * @param row The row of the button.
	 * @param col The column of the button. 
	 * @param image The image to be set. 
	 *******************************************************/
	public void setButtonIcon(final int row, 
			final int col, final int image) {
		board[row][col].setIcon(iconList[image]);
	}
	
	/************************************************************
	 * Sets the background color to the colors chosen from the
	 * popup menu.
	 * @param row The value for the rows on the board.
	 * @param col The value for the columns on the board.
	 * @param c1 The color from the popup menu that will be
	 * applied to half the board.
	 * @param c2 The color from the popup menu that will be
	 * applied to the other half of the board. 
	 **************************************************************/
	public void setBackground(final int row, 
			final int col, final int c1, final int c2) {
		board[row][col].setBorder(null);
		if ((row + col) % 2 == 0) {
			board[row][col].setBackground(colorlist[c1]);
		} else {
			board[row][col].setBackground(colorlist[c2]);
		}
	}
	
	/***********************************************************
	 * Sets the turn label.
	 * @param str THe color of the player.
	 ********************************************************/
	public void setTurnLabel(final String str) {
		playerTurn.setText(str);
	}
	
	/*********************************************************
	 * Sets an image for the players turn. 
	 * @param image The image for the player. 
	 **********************************************************/
	public void setTurnImage(final int image) {
		playerTurnIconLeft.setIcon(iconList[image]);
		playerTurnIconRight.setIcon(iconList[image]);
	}
	
	/*****************************************************
	 * Controls the blinking words in the GUI.
	 * @param i The integer used to decide which players
	 * label will blink.
	 ****************************************************/
	public void setTimer(final int i) {
		timerRed.stop();
		timerWhite.stop();
		timers[i].start();
	}
	
	/*****************************************************
	 * Disables the board when the game is over.
	 ******************************************************/
	public void gameOver() {
		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col].setEnabled(false);
			}
		}
	}
	
	/******************************************************************
	 * Re-enables the buttons on the board.
	 *****************************************************************/
	public void reset() {
		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col].setEnabled(true);
			}
		}
	}
	
	/**************************************************************
	 * Creates a border around possible moves.
	 * @param row  the row position.
	 * @param col  the column position.
	 ****************************************************************/
	public void setHelper(final int row, final int col) {
		board[row][col].setBorder(BorderFactory.createMatteBorder(
                THREE, THREE, THREE, THREE, Color.ORANGE));
	}
	
	
}