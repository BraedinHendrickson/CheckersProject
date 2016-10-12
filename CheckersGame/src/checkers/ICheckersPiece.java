package checkers;

/*******************************************************************************
 * Interface for the checker pieces of checkers.
 ******************************************************************************/
public interface ICheckersPiece {

	/***************************************************************************
	 * Player consists of the two users(players) who are engaging in the game. 
	 *
	 * @return    The user in question.  
	 **************************************************************************/
	Player player();
	
	/***************************************************************************
	 * Type is the "title" of what the given object(checker piece) is. 
	 *
	 * @return    The type of piece in question.  
	 **************************************************************************/
	String type();
	
	/***************************************************************************
	 * Determines if basic conditions are met for a move to be valid of the 
	 * current playing board. 
	 *
	 * @param  move   The move to be evaluated.
	 * @param  board  The current state of the playing board.
	 * @return        Returns whether or not the move in question is valid.  
	 **************************************************************************/
	boolean isValidMove(Move move, ICheckersPiece[][] board);
}
