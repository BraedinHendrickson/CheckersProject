package checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**********************************************************************
 *
 *********************************************************************/
public class CheckersPanel extends JPanel {
	
	private TurnLabel labelRed;
	private TurnLabel labelBlack;
	private Timer timerRed;
	private Timer timerBlack;
	
	private JButton[][] board;
	private CheckersModel model;
	private Move move;
	
	boolean firstTurn;
	
	private int startRow;
	private int startCol;
	
	private final int BOARDSIZE = 8;
	
	private ButtonListener buttonListener = new ButtonListener();
	//private String iconPaths = "C:/Users/Norman/Documents/GVSU/YEAR 4/FALL/CIS 350";
	//private String iconPaths = "/icons/";
	
	private ImageIcon redCirclePlain = new ImageIcon("icons/RedCircle20.png");         
	private ImageIcon redCircle = new ImageIcon("icons/BlackSquareRS.png");          // 2
	private ImageIcon redCircleDouble = new ImageIcon("icons/BlackSquareRD.png");    // 4
	private ImageIcon blackCirclePlain = new ImageIcon("icons/BlackCircle20.png");
	private ImageIcon blackCircle = new ImageIcon("icons/BlackSquareBS.png");        // 2
	private ImageIcon blackCircleDouble = new ImageIcon("icons/BlackSquareBD.png");  // 6
	private ImageIcon redSquare = new ImageIcon("icons/RedSquare.png");
	private ImageIcon blackSquare = new ImageIcon("icons/BlackSquare.png");
	
	JPanel center = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	
	JLabel playerTurn = new JLabel();
	JLabel playerTurnIcon1 = new JLabel();
	JLabel playerTurnIcon2 = new JLabel();
	
	Color dark = new Color(30, 30, 30);
	Color light = new Color(150, 150, 150);
	
	/*******************************************************************
	 *
	 ******************************************************************/
	public CheckersPanel() {
		firstTurn = false;
		model = new CheckersModel();
		board = new JButton[BOARDSIZE][BOARDSIZE];
		
		labelRed = new TurnLabel(playerTurn, Player.User);
		labelBlack = new TurnLabel(playerTurn, Player.Other);
		timerRed = new Timer(300, labelRed);
		timerBlack = new Timer(300, labelBlack);	
		
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		bottom.setLayout(new GridLayout(BOARDSIZE, BOARDSIZE));
		
		for (int row = 0; row < BOARDSIZE; row++)
			for (int col = 0; col < BOARDSIZE; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(80, 80));
				board[row][col].addActionListener(buttonListener);
				bottom.add(board[row][col]);
				if ((row + col) % 2 == 0)
					board[row][col].setIcon(blackSquare);
				else
					board[row][col].setIcon(redSquare);
			}
		
		playerTurn.setHorizontalAlignment(playerTurn.CENTER);
		playerTurn.setVerticalAlignment(playerTurn.CENTER);
		playerTurnIcon1.setHorizontalAlignment(playerTurnIcon1.CENTER);
		playerTurnIcon1.setVerticalAlignment(playerTurnIcon1.CENTER);
		
		top.add(playerTurnIcon1);
		top.add(playerTurn);
		top.add(playerTurnIcon2);
		
		center.add(top);
		center.add(bottom);
		
		add(center, BorderLayout.CENTER);
		
		displayBoard();
	}
	
	/*******************************************************************
	 *
	 ******************************************************************/
	private void displayBoard() {
		if (model.currentPlayer() == Player.Other){
			timerBlack.stop();
			playerTurn.setText("     R e d ' s      T u r n     ");
			timerRed.start();
			playerTurnIcon1.setIcon(redCirclePlain);
			playerTurnIcon2.setIcon(redCirclePlain);
		}
		else {
			timerRed.stop();
			playerTurn.setText("   B l a c k ' s     T u r n   ");
			timerBlack.start();
			playerTurnIcon1.setIcon(blackCirclePlain);
			playerTurnIcon2.setIcon(blackCirclePlain);
		}
		
		for (int row = 0; row < BOARDSIZE; row++)
			for (int col = 0; col < BOARDSIZE; col++) {
				if (model.pieceAt(row, col) != null) {
					if (model.pieceAt(row, col).player() == Player.User) {
						if (model.pieceAt(row, col).type().equals("SingleDisk")) {
							board[row][col].setIcon(blackCircle);
						}
						else if (model.pieceAt(row, col).type().equals("DoubleDisk")){
							board[row][col].setIcon(blackCircleDouble);
						}
					} else if (model.pieceAt(row, col).player() == Player.Other){
						if (model.pieceAt(row, col).type().equals("SingleDisk")) {
							board[row][col].setIcon(redCircle);
						}
						else if (model.pieceAt(row, col).type().equals("DoubleDisk")) {
							board[row][col].setIcon(redCircleDouble);
						}
					}
				} else if (model.pieceAt(row, col) == null)
					if ((row + col) % 2 == 0)
						board[row][col].setIcon(blackSquare);
					else
						board[row][col].setIcon(redSquare);
			} 
	}
	
	/*******************************************************************
	 *
	 ******************************************************************/
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {	
			
			for (int row = 0; row < BOARDSIZE; row++)
				for (int col = 0; col < BOARDSIZE; col++) {
					if (e.getSource() == board[row][col])	
						if (model.pieceAt(row, col) != null && model.pieceAt(row, col).player() == model.currentPlayer() && firstTurn == false) {
							startRow = row;
							startCol = col;
							firstTurn = true;
						
						} else if (firstTurn == true){
							move = new Move(startRow, startCol, row, col);
							if (model.isValidMove(move) == true && model.validSameTurn(move) == true) {
								if (model.isValidMove(move) == true) {
									model.move(move);
								}	
							}
							firstTurn = false;
						}
					}
			displayBoard();
				}	
	}
	
}
