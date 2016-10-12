package checkers;

/*******************************************************************************
* Class for a singly stacked checker piece.
*******************************************************************************/
public class SingleDisk extends CheckersPiece {
	
	/** Final value 0. */
	private static final int ZERO = 0;
	
	/** Final value 1. */
	private static final int ONE = 1;
	
	/** Final value 2. */
	private static final int TWO = 2;
	
	/** Final value -1. */
	private static final int ONE_NEG = -1;
	
	/** Final value -2. */
	private static final int TWO_NEG = -2;
	
	/***************************************************************************
	 * Constructor, sets the owner(player) for a given piece.
	 * 
	 * @param  player  The user(player) who will own the piece.
	 **************************************************************************/
	protected SingleDisk(final Player player) {
		super(player);
	}
	
	/***************************************************************************
	 * Specifies a singly stacked checker piece as a "SingleDisk".
	 * 
	 * @return       Returns the pieces name.
	 **************************************************************************/
	public final String type() {
		return "SingleDisk";
	}
	
	/***************************************************************************
	 * Logic for determining what constitutes as a valid movement across
	 * the player board.
	 * 
	 * @param  move   The current move in question of being valid.
	 * @param  board  The current layout of the playing board.
	 * @return        Returns whether or not the move was valid.
	 **************************************************************************/
	public final boolean isValidMove(final Move move, 
			final ICheckersPiece[][] board) {
		
		// Check that the move passes default restrictions.
		if (super.isValidMove(move, board)) {
			// Position between the from col position and the to col position.
			int xPos = ZERO;
			// Position between the from row position and the to row position.
			int yPos = ZERO;
		
			// If the piece being moved belongs to user White.
			if (board[move.getFromRow()][move.getFromColumn()].player() 
				== Player.White) {
				
				// Check moving UP one square, and either LEFT or RIGHT one
				// square is valid.
				if ((move.getToRow() - move.getFromRow()) == ONE_NEG 
					&& Math.abs(move.getToColumn() - move.getFromColumn())
					== ONE && board[move.getToRow()][ move.getToColumn()] 
							== null) {
					return true;
				}
				
				// Check moving UP two squares, and either LEFT or RIGHT two
				// squares and the to position is open.
				if ((move.getToRow() - move.getFromRow()) == TWO_NEG 
						&& Math.abs(move.getToColumn() - move.getFromColumn()) 
						== TWO && board[move.getToRow()][ move.getToColumn()] 
								== null) {
					
					// Check that the movement was LEFT.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						xPos = ONE;
						yPos = ONE;
						
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getToRow() + xPos]
							[ move.getToColumn() + yPos] != null 
							&& board[move.getToRow() + xPos]
									[move.getToColumn() + yPos].player() 
									== Player.Red) {
							return true;
						}
					}
		
					// Check that the movement was RIGHT.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						xPos = ONE;
						yPos = ONE_NEG;
						
						// If there was an opposing player piece between the 
						// from position and the to position return true.
						if (board[move.getToRow() + xPos]
								[ move.getToColumn() + yPos] != null 
								&& board[move.getToRow() + xPos]
										[ move.getToColumn() + yPos].player() 
										== Player.Red) {
							return true;
						}
					}				
				}	
				
			// If the piece being moved belongs to user Red.
			} else if (board[move.getFromRow()]
					[move.getFromColumn()].player() == Player.Red) {
				
				// Check moving DOWN one square, and either LEFT or RIGHT one
				// square is valid.
				if ((move.getToRow() - move.getFromRow()) == ONE 
						&& Math.abs(move.getToColumn() - move.getFromColumn()) 
						== ONE 
						&& board[move.getToRow()][ move.getToColumn()] 
								== null) {
					return true;
				}
				
				// Check moving DOWN two squares, and either LEFT or RIGHT two
				// squares and the to position is open.
				if (Math.abs(move.getToRow() - move.getFromRow()) == TWO 
						&& Math.abs(move.getToColumn() - move.getFromColumn()) 
						== TWO) {
					
					// Check that the movement was LEFT.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						xPos = ONE_NEG;
						yPos = ONE;
						
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getToRow() + xPos]
								[move.getToColumn() + yPos] != null 
								&& board[move.getToRow() + xPos]
										[move.getToColumn() + yPos].player() 
										== Player.White) {
							return true;
						}
					}
		
					// Check that the movement was RIGHT.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						xPos = ONE_NEG;
						yPos = ONE_NEG;
						
						// If there was an opposing player piece between the 
						// from position and the to position return true.
						if (board[move.getToRow() + xPos]
								[move.getToColumn() + yPos] != null 
								&& board[move.getToRow() + xPos]
										[ move.getToColumn() + yPos].player() 
										== Player.White) {
							return true;
						}
					}	
				}
			}
		}
		// Return false if above conditions are not met.
		return false;	
	}			
}
