package checkers;

/*******************************************************************************
* Class for a singly stacked checker piece.
*******************************************************************************/
public class SingleDisk extends CheckersPiece {
	
	/** Final value 0. */
	private static final int ZERO = 0;
	
	/** Final value 1. */
	private static final int ONE = 1;
	
	/** Final value -1. */
	private static final int ONE_NEG = -1;
	
	
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
			
			int mTC = move.getToColumn();
			int mTR = move.getToRow();
			int mFC = move.getFromColumn();
			int mFR = move.getFromRow();
		
			// If the piece being moved belongs to user White.
			if (board[mFR][mFC].player() == Player.White) {
				
				// Check moving UP one square.
				if ((mTR - mFR) == ONE_NEG) {
					return true;
				} else if (mTR - mFR < ZERO) {
					xPos = ONE;
					yPos = checkDirection(mTC, mFC);
					if (board[mTR + xPos][mTC + yPos] != null 
							&& board[mTR + xPos][mTC + yPos].player() 
									== Player.Red) {
							return true;
					}
				}
				

			// If the piece being moved belongs to user Red.
			} else if (board[mFR][mFC].player() == Player.Red) {
				
				// Check moving DOWN one square.
				if ((mTR - mFR) == ONE) {
					return true;
				} else if (mTR - mFR > ZERO) {
					xPos = ONE_NEG;
					yPos = checkDirection(mTC, mFC);
					if (board[mTR + xPos][mTC + yPos] != null 
							&& board[mTR + xPos][mTC + yPos].player() 
									== Player.White) {
							return true;
					}
				}
			}
		}
		// Return false if above conditions are not met.
		return false;	
	}	
	
	/***************************************************************************
	 * Checks to see if the piece is moving left or right.
	 * 
	 * @param  mTC        move to column position.
	 * @param  mFC        move from column position.
	 * @return 1 or -1    returns -1 for left, 1 for right.
	 **************************************************************************/
	private int checkDirection(final int mTC, final int mFC) {
		int result = mTC - mFC;
		if (result < ZERO) {
			return ONE;
		} else {
			return ONE_NEG;
		}	
	}	
	
}
