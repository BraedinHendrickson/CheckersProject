package checkers;

public interface ICheckersModel {

	boolean isGameOver();
	
	boolean isValidMove(Move move);
	
	void move(Move move);
	
	Player currentPlayer();
}
