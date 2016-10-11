package checkers;

public class SingleDisk extends CheckersPiece {

	protected SingleDisk(Player player) {
		super(player);
	}
	
	public String type() {
		return "SingleDisk";
	}
	
	public boolean isValidMove (Move move, ICheckersPiece[][] board) {
		int xPos = 0;
		int yPos = 0;
		
		
		if (board[move.fromRow][move.fromColumn].player() == Player.User){
			if ((move.toRow - move.fromRow) == -1 && Math.abs(move.toColumn - move.fromColumn) == 1 && board[move.toRow][ move.toColumn] == null){
				return true;
			}
			if ((move.toRow - move.fromRow) == -2 && Math.abs(move.toColumn - move.fromColumn) == 2 && board[move.toRow][ move.toColumn] == null){
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == -2){
					xPos = 1;
					yPos = 1;
					if (board[move.toRow + xPos][ move.toColumn + yPos] != null && board[move.toRow + xPos][move.toColumn + yPos].player() == Player.Other ){
						return true;
					}
				}
		
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == 2){
					xPos = 1;
					yPos = -1;
					if (board[move.toRow + xPos][ move.toColumn + yPos] != null && board[move.toRow + xPos][ move.toColumn + yPos].player() == Player.Other ){
						return true;
					}
				}				
			}	
		}	
		else if (board[move.fromRow][move.fromColumn].player() == Player.Other){
			if ((move.toRow - move.fromRow) == 1 && Math.abs(move.toColumn - move.fromColumn) == 1 && board[move.toRow][ move.toColumn] == null){
				return true;
			}
			if (Math.abs(move.toRow - move.fromRow) == 2 && Math.abs(move.toColumn - move.fromColumn) == 2){
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == -2){
					xPos = -1;
					yPos = 1;
					if (board[move.toRow + xPos][ move.toColumn + yPos] != null && board[move.toRow + xPos][move.toColumn + yPos].player() == Player.User ){
						return true;
					}
				}
		
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == 2){
					xPos = -1;
					yPos = -1;
					if (board[move.toRow + xPos][ move.toColumn + yPos] != null && board[move.toRow + xPos][ move.toColumn + yPos].player() == Player.User ){
						return true;
					}
				}	
			}
		}
		return false;	
	}
				
}
