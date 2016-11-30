package checkers;

/**********************************************************************
 * Checkers Pieces.
 *********************************************************************/
public abstract class CheckersPiece implements ICheckersPiece {

	/** The player of the piece. */
	private Player owner;
	
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
		int MTC = move.getToColumn();
		int MTR = move.getToRow();
		int MFC = move.getFromColumn();
		int MFR = move.getFromRow();
		
		// Checks that the "piece" selected existed (Checks that a 
		// blank square was not selected).
		if (board[MTR][MTC] == null) {
		
			// Checks that a move cannot be to the same position the piece
			// originated from.
			if (MTR != MFR && MTC != MFC) {
	
				// Checks that only diagonal movements are aloud.
				if (Math.abs(MTR - MFR) == Math.abs(MTC - MFC)) {
					
					// Check that move is within board boundary.
					if ((MTR >= 0 && MTR <= 7) && (MTC >= 0 && MTC <= 7)) {
						
						// Check the move is a max of two tiles 
						//away from origin.
						if (Math.abs(MTC - MFC) <= 2 
								&& Math.abs(MTR - MFR) <= 2) {
						
							return true;
						
						}
					}
				}
			}
		}
		
		return false;
		
	}
}
