package checkers;

/**********************************************************************
 * Checkers Pieces.
 *********************************************************************/
public abstract class CheckersPiece implements ICheckersPiece {

	/** The player of the piece. */
	private Player owner;
	
	/** Final value of 7. */
	private static final int SEVEN = 7;
	
	/**********************************************************************
	 * Constructor, sets player.
	 * 
	 * @param  player  The selected user.
	 *********************************************************************/
	protected CheckersPiece(final Player player) {
		this.owner = player;
	}
	
	/**********************************************************************
	 * The type differentiates pieces.
	 * 
	 * @return     Returns string that represents different pieces.
	 *********************************************************************/
	public abstract String type();

	/**********************************************************************
	 * Player is the users of the game.
	 * 
	 * @return     Returns the player.
	 *********************************************************************/
	public final Player player() {
		
		return owner;
	
	}
	
	/**********************************************************************
	 * Checks that a move is valid.
	 * 
	 * @param  move   The move in question.
	 * @param  board  The playing board.
	 * @return        Returns whether or not the move is valid.
	 *********************************************************************/
	public boolean isValidMove(final Move move, 
			final ICheckersPiece[][] board) {
		int mTC = move.getToColumn();
		int mTR = move.getToRow();
		int mFC = move.getFromColumn();
		int mFR = move.getFromRow();
		
		// Checks that the "piece" selected existed (Checks that a 
		// blank square was not selected).
		if (board[mTR][mTC] == null) {
		
			// Checks that a move cannot be to the same position the piece
			// originated from.
			if (mTR != mFR && mTC != mFC) {
	
				// Checks that only diagonal movements are aloud.
				if (Math.abs(mTR - mFR) == Math.abs(mTC - mFC)) {
					
					// Check that move is within board boundary.
					if ((mTR >= 0 && mTR <= SEVEN) && (mTC >= 0 
							&& mTC <= SEVEN)) {
						
						// Check the move is a max of two tiles from origin.
						if (Math.abs(mTC - mFC) <= 2 
								&& Math.abs(mTR - mFR) <= 2) {
						
							return true;
						
						}
					}
				}
			}
		}
		
		return false;
		
	}
}
