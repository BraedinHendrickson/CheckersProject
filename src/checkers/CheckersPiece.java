

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
		
		// Checks that a move cannot be to the same position the piece
		// originated from.
		if (move.getToRow() != move.getFromRow() && move.getToColumn() 
				!= move.getFromColumn()) {
	
			// Checks that only diagonal movements are aloud.
			if (Math.abs(move.getToRow() - move.getFromRow()) 
					== Math.abs(move.getToColumn() - move.getFromColumn())) {
				
				// Checks that the "piece" selected existed (Checks that a 
				// blank square was not selected).
				if (board[move.getToRow()][move.getToColumn()] == null) {
					
					return true;
					
				}
			}
		}
		
		return false;
		
	}
}
