package checkers;

/*******************************************************************************
* Class for a doubly stacked checker piece.
*******************************************************************************/
public class DoubleDisk extends CheckersPiece {
	
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
			int rPos = 0;
			// Position between the from row position and the to row position.
			int cPos = 0;
			
			int mTC = move.getToColumn();
			int mTR = move.getToRow();
			int mFC = move.getFromColumn();
			int mFR = move.getFromRow();
			
			if (Math.abs(mTR - mFR) == 1) {
				return true;
			} else {
				rPos = checkVerticalDirection(mTR, mFR);
				cPos = checkHorizontalDirection(mTC, mFC);
				if (board[mTR + rPos][mTC + cPos] != null 
						&& board[mTR + rPos][mTC + cPos].player() 
								!= super.player()) {
						return true;
				}
			}
		}
		return false;
	}
			private int checkHorizontalDirection(int mTC, int mFC) {
				int result = mTC - mFC;
				if (result < 0) {
					return 1;
				} else {
					return -1;
				}	
			}
			
			private int checkVerticalDirection(int mTR, int mFR) {
				int result = mTR - mFR;
				if (result < 0) {
					return 1;
				} else {
					return -1;
				}	
			}
}
