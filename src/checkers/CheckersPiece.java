package checkers;

public abstract class CheckersPiece implements ICheckersPiece {

	private Player owner;
	private CheckersModel model;
	
	protected CheckersPiece(Player player) {
		this.owner = player;
	}
	
	public abstract String type();

	public Player player() {
		
		return owner;
	
	}
	
	public boolean isValidMove(Move move, ICheckersPiece[][] board){
		
		if (move.toRow != move.fromRow && move.toColumn != move.fromColumn) {
	
			if (Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn)) {
				
				if (board[move.toRow][move.toColumn] == null) {
					
					return true;
					
				}
			}
		}
		
		return false;
		
	}
}
