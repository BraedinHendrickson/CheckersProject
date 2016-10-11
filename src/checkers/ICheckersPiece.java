package checkers;

public interface ICheckersPiece {

	Player player();
	
	String type();
	
	boolean isValidMove(Move move, ICheckersPiece[][] board);
}
