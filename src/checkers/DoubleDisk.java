

package checkers;

/*******************************************************************************
* Class for a doubly stacked checker piece.
*******************************************************************************/
public class DoubleDisk extends CheckersPiece {
	
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
	protected DoubleDisk(final Player player) {
		super(player);
	}

	/***************************************************************************
	 * Specifies a doubly stacked checker piece as a "DoubleDisk".
	 * 
	 * @return       Returns the pieces name.
	 **************************************************************************/
	public final String type() {
		return "DoubleDisk";
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
			int rPos = ZERO;
			// Position between the from row position and the to row position.
			int cPos = ZERO;
			// If the move is any single diagonal move to an open spot 
			// return true.
			if (Math.abs(move.getToRow() - move.getFromRow()) == ONE 
				&& Math.abs(move.getToColumn() - move.getFromColumn()) == ONE) {
				return true;
			}			
			// Check that the move is a diagonal move of two spaces and the
			// spot is open.
			if (Math.abs(move.getToRow() - move.getFromRow()) == TWO 
				&& Math.abs(move.getToColumn() - move.getFromColumn()) == TWO) {
				
				// If the piece being moved belongs to user White.
				if (board[move.getFromRow()][move.getFromColumn()].player() 
						== Player.White) {		
					// Check moving DOWN two squares and RIGHT two squares.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						rPos = ONE;
						cPos = ONE;
					
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.Red) {
							return true;
						}
					}	
					// Check moving UP two squares and LEFT two squares.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						rPos = ONE_NEG;
						cPos = ONE_NEG;
						
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.Red) {
							return true;
						}
					}
					// Check moving DOWN two squares and LEFT two squares.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						rPos = ONE;
						cPos = ONE_NEG;
						
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.Red) {
							return true;
						}
					}			
					// Check moving UP two squares and RIGHT two squares.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						rPos = ONE_NEG;
						cPos = ONE;
						
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
							[move.getFromColumn() + cPos].player() 
							== Player.Red) {
						return true;
						}
					}
				// If the piece being moved belongs to user Red.
				} else if (board[move.getFromRow()]
						[move.getFromColumn()].player() == Player.Red) {		
					// Check moving DOWN two squares and RIGHT two squares.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						rPos = ONE;
						cPos = ONE;
					
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.White) {
							return true;
						}
					}				
					// Check moving UP two squares and LEFT two squares.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						rPos = ONE_NEG;
						cPos = ONE_NEG;
					
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.White) {
							return true;
						}
					}				
					// Check moving DOWN two squares and LEFT two squares.
					if (move.getToRow() - move.getFromRow() == TWO 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO_NEG) {
						rPos = ONE;
						cPos = ONE_NEG;
					
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
								== Player.White) {
							return true;
						}
					}				
					// Check moving UP two squares and RIGHT two squares.
					if (move.getToRow() - move.getFromRow() == TWO_NEG 
							&& move.getToColumn() - move.getFromColumn() 
							== TWO) {
						rPos = ONE_NEG;
						cPos = ONE;
					
						// Check that there was an opposing player piece 
						// between the from position and the to position.
						if (board[move.getFromRow() + rPos]
								[move.getFromColumn() + cPos].player() 
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
