package checkers;

/**********************************************************************
 * Model class for executing the game logic of checkers.
 *********************************************************************/
public class CheckersModel implements ICheckersModel {
	
    /** Width and Height of the board. */
	private static final int BOARDSIZE = 8;
	
	private int prevRow = 0;
	private int prevCol = 0;
	
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
	
	/** Final value -2. */
	private static final int TWO_NEG = -2;
	
	/** The board on which checker pieces will be moved. */
	private ICheckersPiece[][] board;
	
	/** The Player in-game who is executing a command. */
	private Player player;
	
	
	private boolean firstClick;
	
	/** Boolean value to see if multiple 'jumps' can be made in-game. */
	private boolean sameTurn;
	
	/** Holds onto the last move made by the current Player. */
	private Move prevMove;
	
	private String sessionMove;
	
	/**********************************************************************
	 * Constructor.
	 *********************************************************************/
	public CheckersModel() {
		sessionMove = "Begin, White's move.";
		firstClick = true;
		player = Player.White;
		boardSetUp();
	}
	
	/**********************************************************************
	 * Sets the board to default beginning values.
	 * 
	 * @return      Returns the board.
	 *********************************************************************/
	private ICheckersPiece[][] boardSetUp() {
		board = new ICheckersPiece[BOARDSIZE][BOARDSIZE];
		
		/*board[ZERO][ZERO] = new SingleDisk(Player.Red);
		board[ZERO][TWO] = new SingleDisk(Player.Red);
		board[ZERO][FOUR] = new SingleDisk(Player.Red);
		board[ZERO][SIX] = new SingleDisk(Player.Red);
		board[ONE][ONE] = new SingleDisk(Player.Red);
		board[ONE][THREE] = new SingleDisk(Player.Red);
		board[ONE][FIVE] = new SingleDisk(Player.Red);
		board[ONE][SEVEN] = new SingleDisk(Player.Red);
		board[TWO][ZERO] = new SingleDisk(Player.Red);
		board[TWO][TWO] = new SingleDisk(Player.Red);
		board[TWO][FOUR] = new SingleDisk(Player.Red);
		board[TWO][SIX] = new SingleDisk(Player.Red);
		
		board[FIVE][ONE] = new SingleDisk(Player.White);
		board[FIVE][THREE] = new SingleDisk(Player.White);
		board[FIVE][FIVE] = new SingleDisk(Player.White);
		board[FIVE][SEVEN] = new SingleDisk(Player.White);
		board[SIX][ZERO] = new SingleDisk(Player.White);
		board[SIX][TWO] = new SingleDisk(Player.White);
		board[SIX][FOUR] = new SingleDisk(Player.White);
		board[SIX][SIX] = new SingleDisk(Player.White);
		board[SEVEN][ONE] = new SingleDisk(Player.White);
		board[SEVEN][THREE] = new SingleDisk(Player.White);
		board[SEVEN][FIVE] = new SingleDisk(Player.White);
		board[SEVEN][SEVEN] = new SingleDisk(Player.White);*/
		
		board[TWO][FOUR] = new DoubleDisk(Player.Red);
		board[TWO][SIX] = new DoubleDisk(Player.White);
		
		return board;
	}
	
	
	/**********************************************************************
	 * Changes the piece type at a board position.
	 * 
	 * @param  row    The row position.
	 * @param  col    The column position.
	 * @param  piece  The checkers piece that the position will be set to.
	 *********************************************************************/
	public final void setBoardPosition(final int row, final int col,
			final CheckersPiece piece) {
		board[row][col] = piece; 	
	}
	
	/**********************************************************************
	 * Evaluates which players turn it is.
	 * 
	 * @return     Returns the player.
	 *********************************************************************/
	public final Player currentPlayer() {
		return player;	
	}
	
	/**********************************************************************
	 * Takes coordinate parameters and returns that board position.
	 * 
	 * @param  row  The row coordinate.
	 * @param  col  The column coordinate.
	 * @return      Returns the board location.
	 *********************************************************************/
	public final ICheckersPiece pieceAt(final int row, final int col) {
		return board[row][col];
	}
	
	
	/**********************************************************************
	 * Executes the move being passed in the parameter. If multiple jumps
	 * are available sameTurn is set to true.
	 * 
	 * @param  move  The move to be executed.
	 *********************************************************************/
	public final void move(final Move move) {
		// Check that the move is valid.
		if (isValidMove(move) && validSameTurn(move)) {
			
			// Set same turn to false for default.
			sameTurn = false;
		
			// The piece is copied to the destination. 
			board[move.getToRow()][move.getToColumn()] 
					= board[move.getFromRow()][move.getFromColumn()];
			// The "from" position is cleared.
			board[move.getFromRow()][move.getFromColumn()] = null;
			
			setSessionMove(move, true);
		
			// The move was a jump.
			if (Math.abs(move.getToRow() - move.getFromRow()) == 2) {
				// Used for determining column movement.
				int cPos = move.getToColumn() - move.getFromColumn();
				// Used for determining row movement.
				int rPos = move.getToRow() - move.getFromRow();
				// Removes the piece that was jumped.
				takePiece(rPos, cPos, move);
				// Checks that another jump is possible.
				sameTurn = checkSameTurn(move);
			}
		
			// Check if promotion should occur and execute if necessary.
			promotion(move);
			
			isGameOver();
		
			// If it is still the same users turn do not 
			// change the current player.
			if (!sameTurn) {
				if (currentPlayer() == Player.White) {
					player = Player.Red;
				} else {
					player = Player.White;
				}
			}
		} else {
			setSessionMove(move, false);
		}
	}
	
	/**********************************************************************
	 * Checks to see if conditions are met for multiple jumps. It checks
	 * hypothetical jump conditions. Sets the current move to prevMove 
	 * and waits for the users next selection.
	 * 
	 * @param  move  The initial move being made.
	 * @return       Returns whether or not it is still the users turn.
	 *********************************************************************/
	public final boolean checkSameTurn(final Move move) {
		int mTC = move.getToColumn();
		int mTR = move.getToRow();
		int mFC = move.getFromColumn();
		int mFR = move.getFromRow();
		

		int[] twos = new int[2];
		twos[0] = -2;
		twos[1] = 2;
		for (int item1 : twos) {
			for (int item2 : twos) {
				if (mTR+item1 >= 0 && mTR+item1 < 8 && mTC+item2 >= 0 && mTC+item2 < 8) {
					Move tempMove = new Move(mTR, mTC, mTR+item1, mTC+item2);
					if (isValidMove(tempMove)) {
						prevMove = move;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**********************************************************************
	 * Checks that if it is still the same users turn(i.e. a jump has been
	 * made) then the piece that made the initial jump must be the piece moved.
	 * 
	 * @param  move    .
	 * @return         .
	 *********************************************************************/
	public final boolean validSameTurn(final Move move) {
		if (sameTurn) {
			if (prevMove.getToRow() != move.getFromRow() 
					|| prevMove.getToColumn() != move.getFromColumn()) {
				return false;
			}	
		}
		return true;
	}
	
	/**********************************************************************
	 * Checks that the move is valid.
	 * 
	 * @param  move  The move to be evaluated.
	 * @return       Returns the validity of the move.
	 *********************************************************************/
	public final boolean isValidMove(final Move move) {
		
		// Check the position selected contains a piece and the piece
		// belongs to the current player.
		if (pieceAt(move.getFromRow(), move.getFromColumn()) != null 
				&& pieceAt(move.getFromRow(), move.getFromColumn()).player() 
				== currentPlayer()) {
			
			// If the user is trying to move a piece onto another piece
			// return false.
			if (pieceAt(move.getToRow(), move.getToColumn()) != null 
					&& pieceAt(move.getToRow(), 
					move.getToColumn()).player() == player) {
				return false;
			}
		
			// Return true or false based on the restrictions of the piece type.
			return pieceAt(move.getFromRow(), 
					move.getFromColumn()).isValidMove(move, board);	
			}
		return false;
	}
	
	/**********************************************************************
	 * When a jump is executed the piece that was jumped is removed.
	 * 
	 * @param  rPos  The row position.
	 * @param  cPos  The column position.
	 * @param  move  The move that was executed. 
	 *********************************************************************/
	private void takePiece(final int rPos, final int cPos, final Move move) {
		if (rPos == TWO && cPos == TWO) {
			board[move.getFromRow() + 1][move.getFromColumn() + 1] = null;
		} else if (rPos == TWO && cPos == TWO_NEG) {
			board[move.getFromRow() + 1][move.getFromColumn() - 1] = null;
		} else if (rPos == TWO_NEG && cPos == TWO) {
			board[move.getFromRow() - 1][move.getFromColumn() + 1] = null;
		} else if (rPos == TWO_NEG && cPos == TWO_NEG) {
			board[move.getFromRow() - 1][move.getFromColumn() - 1] = null;
		}
	}
	
	/**********************************************************************
	 * Promotes a singleDisk to a doubleDisk if it scales the board.
	 * 
	 * @param  move  The move that was executed.
	 *********************************************************************/
	private void promotion(final Move move) {
		
		// If the piece is a single white disk and is moved to row zero,
		// promote the piece to a double white disk.
		if (board[move.getToRow()][move.getToColumn()].player() == Player.White 
				&& board[move.getToRow()][move.getToColumn()].type() 
				== "SingleDisk" && move.getToRow() == ZERO) {
			
			board[move.getToRow()][move.getToColumn()] 
					= new DoubleDisk(Player.White);
		}
		
		// If the piece is a single red disk and is moved to row seven,
		// promote the piece to a double red disk.
		if (board[move.getToRow()][move.getToColumn()].player() == Player.Red 
				&& board[move.getToRow()][move.getToColumn()].type() 
				== "SingleDisk" && move.getToRow() == SEVEN) {
			
			board[move.getToRow()][move.getToColumn()] 
					= new DoubleDisk(Player.Red);
		}
	}

	/**********************************************************************
	 * Determines if the game status is over or not.
	 * 
	 * @return    Returns true if the game is over.
	 *********************************************************************/
	public final boolean isGameOver() {
		int redPieceCount = 0;
		int whitePieceCount = 0;
		
		// Loops through the board and counts how many pieces each player
		// has on the board.
		for (int i = 0; i < EIGHT; i++) {
			for (int j = 0; j < EIGHT; j++) {
				if (pieceAt(i, j) != null 
						&& pieceAt(i, j).player() == Player.Red) {
					redPieceCount++;
				} else if (pieceAt(i, j) != null 
						&& pieceAt(i, j).player() == Player.White) {
					whitePieceCount++;
				}
			}
		}
		
		// If either player has zero pieces remaining the game is over.
		if (redPieceCount == 0) {
			sessionMove = "\n\nWhite wins the game!";
			return true;
		} else if (whitePieceCount == 0) {
			sessionMove = "\n\nRed wins the game!";
			return true;
		}
		return false;
		
	}
	
	public void actionHandling(int row, int col) {
		if (firstClick) {
			prevRow = row;
			prevCol = col;
			firstClick = false;
			setSessionMove(row, col);
		} else {
			Move move = new Move(prevRow, prevCol, row, col);
			move(move);
			firstClick = true;
		}
	}
	
	public String currentStatusLabel() {
		String str = "";
		if (isGameOver() == true) {
			str = "   G a m e   O v e r   ";
		} else if (currentPlayer() == Player.White) {
			str = "   W h i t e ' s     T u r n   ";
		} else if (currentPlayer() == Player.Red) {
			str = "     R e d ' s      T u r n     ";
		}
		return str;
	}
	
	public int boardLayout(int row, int col) {
		if (board[row][col] == null && (row + col) % TWO == ONE) {
			return 2;
		} else if (board[row][col] == null && (row + col) % TWO == ZERO) {
			return 3;
		} else if (board[row][col].player() == Player.White && board[row][col].type().equals("SingleDisk")) {
			return 5;
		} else if (board[row][col].player() == Player.White && board[row][col].type().equals("DoubleDisk")) {
			return 7;
		} else if (board[row][col].player() == Player.Red && board[row][col].type().equals("SingleDisk")) {
			return 4;
		} else if (board[row][col].player() == Player.Red && board[row][col].type().equals("DoubleDisk")) {
			return 6;
		} else {
			return 0;
		}
	}
	
	public String getSessionMove() {
		return sessionMove;
	}
	
	public void setSessionMove(Move move, boolean valid) {
		Player otherPlayer = null;
		if (currentPlayer() == Player.White) {
			otherPlayer = Player.Red;
		} else {
			otherPlayer = Player.White;
		}
		if (valid) {
			sessionMove = currentPlayer().toString() + " moved from R" + move.getFromRow() + "-C" + move.getFromColumn() +
				" to R" + move.getToRow() + "-C" + move.getToColumn() + ".\n\n" +  otherPlayer.toString() + "'s Turn.";
		} else {
			sessionMove = currentPlayer().toString() + " failed to move from R" + move.getFromRow() + "-C" + move.getFromColumn() +
					" to R" + move.getToRow() + "-C" + move.getToColumn() + " because the \nmove was not valid. Please try a different move.";
		} 
		
	}
	
	public void setSessionMove(int row, int col){
		sessionMove = currentPlayer().toString() + " has selected the board postion R" + row + "-C" + col + ".";
	}
	
	public void reset() {
		sessionMove = "Begin, White's move.";
		firstClick = true;
		player = Player.White;
		boardSetUp();
	}
	
	
	
	
}
