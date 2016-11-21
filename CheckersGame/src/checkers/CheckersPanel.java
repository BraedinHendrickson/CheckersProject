package checkers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**********************************************************************
 * Panel class for displaying the game of checkers.
 *********************************************************************/
@SuppressWarnings("serial")
public class CheckersPanel extends JPanel {
	
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

	/** The label during reds turn. */
	private TurnLabel labelRed;
	
	/** The label during blacks turn. */
	private TurnLabel labelWhite;
	
	/** The timer during reds turn. */
	private Timer timerRed;
	
	/** The timer during blacks turn. */
	private Timer timerWhite;

	/** The playing board. */
	private JButton[][] board;
	
	/** Game mechanics/values. */
	private CheckersModel model;
	
	/** A temporary move. */
	private Move move;

	/** Used to check that a piece has been selected. */
	private boolean firstClick;

	/** Holds the row value of the piece selected. */
	private int startRow;
	
	/** Holds the column value of the piece selected. */
	private int startCol;

	/** Action Event handler. */
	private ButtonListener buttonListener = new ButtonListener();

	/** Icon for red label. */
	private ImageIcon redCirclePlain = 
			new ImageIcon(ClassLoader.getSystemResource("RedCircle20.png"));
	/** Icon for red single disk. */
	private ImageIcon redCircle = 
			new ImageIcon(ClassLoader.getSystemResource("BlackSquareRS.png"));
	/** Icon for red double disk. */
	private ImageIcon redCircleDouble 
	= new ImageIcon(ClassLoader.getSystemResource("BlackSquareRD.png"));
	/** Icon for black label. */
	private ImageIcon blackCirclePlain 
	= new ImageIcon(ClassLoader.getSystemResource("BlackCircle20.png"));
	/** Icon for black single disk. */
	private ImageIcon blackCircle 
	= new ImageIcon(ClassLoader.getSystemResource("BlackSquareBS.png"));
	/** Icon for black double disk. */
	private ImageIcon blackCircleDouble 
	= new ImageIcon(ClassLoader.getSystemResource("BlackSquareBD.png"));
	/** Icon for red tile. */
	private ImageIcon redSquare = 
			new ImageIcon(ClassLoader.getSystemResource("RedSquare.png"));
	/** Icon for black tile. */
	private ImageIcon blackSquare = 
			new ImageIcon(ClassLoader.getSystemResource("BlackSquare.png"));

	/** Main panel. */
	private JPanel center = new JPanel();
	/** North panel, place on center panel. */
	private JPanel top = new JPanel();
	/** South panel, place on center panel. */
	private JPanel bottom = new JPanel();

	/** Label displays the current players turn. */
	private JLabel playerTurn = new JLabel();
	/** Label displays left icon piece next to playerTurn label. */
	private JLabel playerTurnIcon1 = new JLabel();
	/** Label displays right icon piece next to playerTurn label. */
	private JLabel playerTurnIcon2 = new JLabel();

	/*******************************************************************
	 * Panel containing all sub panels and buttons.
	 ******************************************************************/
	public CheckersPanel() {
		firstClick = false;
		model = new CheckersModel();
		board = new JButton[BOARDSIZE][BOARDSIZE];

		// For the blinking labels.
		labelRed = new TurnLabel(playerTurn, Player.Red);
		labelWhite = new TurnLabel(playerTurn, Player.White);
		timerRed = new Timer(THREEZEROZERO, labelRed);
		timerWhite = new Timer(THREEZEROZERO, labelWhite);

		// Layouts for the panels.
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		bottom.setLayout(new GridLayout(BOARDSIZE, BOARDSIZE));

		// Create the board buttons, set backgrounds.
		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(
						new Dimension(EIGHTZERO, EIGHTZERO));
				board[row][col].addActionListener(buttonListener);
				bottom.add(board[row][col]);
				if ((row + col) % TWO == ZERO) {
					board[row][col].setIcon(blackSquare);
				} else {
					board[row][col].setIcon(redSquare);
				}
			}
		}

		// Set label alignments.
		playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurn.setVerticalAlignment(SwingConstants.CENTER);
		playerTurnIcon1.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurnIcon1.setVerticalAlignment(SwingConstants.CENTER);

		// Add labels to panel.
		top.add(playerTurnIcon1);
		top.add(playerTurn);
		top.add(playerTurnIcon2);

		// Add sub panels to center panel.
		center.add(top);
		center.add(bottom);

		// Add center panel to frame.
		add(center, BorderLayout.CENTER);

		displayBoard();
	}

	/*******************************************************************
	 * Updates the playing board to display checkers pieces.
	 ******************************************************************/
	private void displayBoard() {
		// (Label Adjustment)If it is user Red's turn, stop white's blinking, 
		// set the label to "red's turn", start red's timer, set icons to red.
		// Else repeat except do for White's turn (with white's settings).
		if (model.currentPlayer() == Player.Red) {
			timerWhite.stop();
			playerTurn.setText("     R e d ' s      T u r n     ");
			timerRed.start();
			playerTurnIcon1.setIcon(redCirclePlain);
			playerTurnIcon2.setIcon(redCirclePlain);
		} else {
			timerRed.stop();
			playerTurn.setText("   W h i t e ' s     T u r n   ");
			timerWhite.start();
			playerTurnIcon1.setIcon(blackCirclePlain);
			playerTurnIcon2.setIcon(blackCirclePlain);
		}

		// Loop through the entire board, hit all row/column combinations.
		for (int row = ZERO; row < BOARDSIZE; row++) {
			for (int col = ZERO; col < BOARDSIZE; col++) {
				
				// If a piece is found.
				if (model.pieceAt(row, col) != null) {
					
					// If the piece belongs to user White.
					if (model.pieceAt(row, col).player() == Player.White) {
						
						// If the piece is a single disk.
						if (model.pieceAt(row, col).type().equals(
								"SingleDisk")) {
							
							// Update the board icon to correct piece.
							board[row][col].setIcon(blackCircle);
							
						// If the piece is a double disk.
						} else if (model.pieceAt(row, col).type().equals(
								"DoubleDisk")) {
							
							// Update the board icon to correct piece.
							board[row][col].setIcon(blackCircleDouble);
						}
					// If the piece belongs to user Red.
					} else if (model.pieceAt(row, col).player() 
							== Player.Red) {
						
						// If the piece is a single disk.
						if (model.pieceAt(row, col).type().equals(
								"SingleDisk")) {
							
							// Update the board icon to correct piece.
							board[row][col].setIcon(redCircle);
							
						// If the piece is a double disk.
						} else if (model.pieceAt(row, col).type().equals(
								"DoubleDisk")) {
							
							// Update the board icon to correct piece.
							board[row][col].setIcon(redCircleDouble);
						}
					}
					
				// If there is no piece at that position set the icon to 
				// a solid red or black tile.
				} else if (model.pieceAt(row, col) == null) {
					if ((row + col) % TWO == ZERO) {
						board[row][col].setIcon(blackSquare);
					} else {
						board[row][col].setIcon(redSquare);
					}
				}
					
				}
			}
		
	if (model.isGameOver()) {
		JOptionPane jpane = new JOptionPane();
		jpane.showMessageDialog(null, "You Win");
	}
	}

	/*******************************************************************
	 * Action events for mouse clicks.
	 ******************************************************************/
	private class ButtonListener implements ActionListener {

		/*******************************************************************
		 * Action event detection.
		 * 
		 * @param  e  Action Event.
		 ******************************************************************/
		public void actionPerformed(final ActionEvent e) {

			// Loop through the entire board, hit all row/column combinations.
			for (int row = ZERO; row < BOARDSIZE; row++) {
				for (int col = ZERO; col < BOARDSIZE; col++) {
					
					// If the ActionEvent was found.
					if (e.getSource() == board[row][col]) {
						
						// If this is the users first of two clicks for making
						// a move, save the coordinates, and set firstClick 
						// to false.
						if (!firstClick) {
							startRow = row;
							startCol = col;
							firstClick = true;

						// If this is the users second click, create a new Move
						// using the first click values and the second click
						// values. Have the Move executed in the model class, 
						// reset the firstClick variable.
						} else if (firstClick) {
							move = new Move(startRow, startCol, row, col);
							model.move(move);
							firstClick = false;
							}
						}
					}
				}
			// Update the board to reveal pieces new positions after movements.
			displayBoard();
		}
		
	}

}
