

package checkers;

/*******************************************************************************
 * Interface for the model of checkers.
 ******************************************************************************/
public interface ICheckersModel {

	/***************************************************************************
	 * Determines if the game has finished.
	 * 
	 * @return    The status of the game state.
	 **************************************************************************/
	boolean isGameOver();
	
	/***************************************************************************
	 * Determines the validity of a move.
	 * 
	 * @param  move  The current move in question of being valid.
	 * @return       Returns whether or not the move was valid.
	 **************************************************************************/
	boolean isValidMove(Move move);
	
	/***************************************************************************
	 * Executes a move.
	 * 
	 * @param  move  The move to be executed.
	 **************************************************************************/
	void move(Move move);
	
	/***************************************************************************
	 * Determines the validity of a move.
	 * 
	 * @return       Returns who the current player is, i.e. whose turn it is.
	 **************************************************************************/
	Player currentPlayer();
}
