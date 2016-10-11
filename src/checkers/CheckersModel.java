package checkers;


public class CheckersModel implements ICheckersModel{
	private final int BOARDSIZE = 8;
	private ICheckersPiece[][] board;
	private Player player;
	private boolean sameTurn;
	private Move prevMove;
	
	public CheckersModel(){
		player = Player.User;
		boardSetUp();
		
	}
	
	public ICheckersPiece[][] boardSetUp() {
		board = new ICheckersPiece[BOARDSIZE][BOARDSIZE];
		
		board[0][0] = new SingleDisk(Player.Other);
		board[0][2] = new SingleDisk(Player.Other);
		board[0][4] = new SingleDisk(Player.Other);
		board[0][6] = new SingleDisk(Player.Other);
		board[1][1] = new SingleDisk(Player.Other);
		board[1][3] = new SingleDisk(Player.Other);
		board[1][5] = new SingleDisk(Player.Other);
		board[1][7] = new SingleDisk(Player.Other);
		board[2][0] = new SingleDisk(Player.Other);
		board[2][2] = new SingleDisk(Player.Other);
		board[2][4] = new SingleDisk(Player.Other);
		board[2][6] = new SingleDisk(Player.Other);
		
		board[5][1] = new SingleDisk(Player.User);
		board[5][3] = new SingleDisk(Player.User);
		board[5][5] = new SingleDisk(Player.User);
		board[5][7] = new SingleDisk(Player.User);
		board[6][0] = new SingleDisk(Player.User);
		board[6][2] = new SingleDisk(Player.User);
		board[6][4] = new SingleDisk(Player.User);
		board[6][6] = new SingleDisk(Player.User);
		board[7][1] = new SingleDisk(Player.User);
		board[7][3] = new SingleDisk(Player.User);
		board[7][5] = new SingleDisk(Player.User);
		board[7][7] = new SingleDisk(Player.User);
		
		return board;
	}
	
	public Player currentPlayer() {
		
		return player;
		
	}
	
	public ICheckersPiece pieceAt(int row, int col) {
		return board[row][col];
	}
	
	public void move(Move move) {
		prevMove = move;
		sameTurn = false;
		
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
		
		if (Math.abs(move.toRow - move.fromRow) == 2){
			int cPos = move.toColumn - move.fromColumn;
			int rPos = move.toRow - move.fromRow;
			takePiece(rPos, cPos, move);
			sameTurn = checkSameTurn(move);
		}
		
		promotion(move);
		
		if (sameTurn == false){
			if (currentPlayer() == Player.User)
				player = Player.Other;
			else
				player = Player.User;
		}
		
	}
	
	private boolean checkSameTurn(Move move) {
		if (move.toRow >= 2 && move.toRow <= 5 && move.toColumn >= 2 && move.toColumn <= 5){	
			if (pieceAt(move.toRow, move.toColumn).type() == "SingleDisk") {
				if (board[move.toRow][move.toColumn].player() == Player.User){
					if (board[move.toRow - 2][move.toColumn - 2] == null && board[move.toRow - 1][move.toColumn - 1] != null && board[move.toRow - 1][move.toColumn - 1].player() == Player.Other) {
						return true;
					}
					if (board[move.toRow - 2][move.toColumn + 2] == null && board[move.toRow - 1][move.toColumn + 1] != null && board[move.toRow - 1][move.toColumn + 1].player() == Player.Other) {
						return true;
					}
				}
				else {
					if (board[move.toRow + 2][move.toColumn - 2] == null && board[move.toRow + 1][move.toColumn - 1] != null && board[move.toRow + 1][move.toColumn - 1].player() == Player.User) {
						return true;
					}
					if (board[move.toRow + 2][move.toColumn + 2] == null && board[move.toRow + 1][move.toColumn + 1] != null && board[move.toRow + 1][move.toColumn + 1].player() == Player.User) {
						return true;
					}
				}
			}
			else if (pieceAt(move.toRow, move.toColumn).type() == "DoubleDisk") {
				if (board[move.toRow - 2][move.toColumn - 2] == null && board[move.toRow - 1][move.toColumn - 1] != null && board[move.toRow - 1][move.toColumn - 1].player() != currentPlayer()) {
					return true;
				}
				if (board[move.toRow - 2][move.toColumn + 2] == null && board[move.toRow - 1][move.toColumn + 1] != null && board[move.toRow - 1][move.toColumn + 1].player() != currentPlayer()) {
					return true;
				}
				if (board[move.toRow + 2][move.toColumn - 2] == null && board[move.toRow + 1][move.toColumn - 1] != null && board[move.toRow + 1][move.toColumn - 1].player() != currentPlayer()) {
					return true;
				}
				if (board[move.toRow + 2][move.toColumn + 2] == null && board[move.toRow + 1][move.toColumn + 1] != null && board[move.toRow + 1][move.toColumn + 1].player() != currentPlayer()) {
					return true;
				}
			}	
		}
		return false;
	}
	
	public boolean validSameTurn(Move move) {
		if (sameTurn == true){
			if (prevMove.toRow != move.fromRow && prevMove.toColumn != move.fromColumn){
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidMove(Move move) {
		if (pieceAt(move.toRow, move.toColumn) != null && pieceAt(move.toRow, move.toColumn).player() == player)
			return false;
		
		return pieceAt(move.fromRow, move.fromColumn).isValidMove(move, board);	
		
	}
	
	private void takePiece(int rPos, int cPos, Move move) {
		if (rPos == 2 && cPos == 2) {
			board[move.fromRow + 1][move.fromColumn + 1] = null;
		}
		if (rPos == 2 && cPos == -2) {
			board[move.fromRow + 1][move.fromColumn - 1] = null;
		}
		if (rPos == -2 && cPos == 2) {
			board[move.fromRow - 1][move.fromColumn + 1] = null;
		}
		if (rPos == -2 && cPos == -2) {
			board[move.fromRow - 1][move.fromColumn - 1] = null;
		}
	}
	
	private void promotion(Move move) {
		if (board[move.toRow][move.toColumn].player() == Player.User && board[move.toRow][move.toColumn].type() == "SingleDisk" && move.toRow == 0){
			board[move.toRow][move.toColumn] = new DoubleDisk(Player.User);
		}
		if (board[move.toRow][move.toColumn].player() == Player.Other && board[move.toRow][move.toColumn].type() == "SingleDisk" && move.toRow == 7){
			board[move.toRow][move.toColumn] = new DoubleDisk(Player.Other);
		}
	}

	public boolean isGameOver() {
		
		return false;
		
	}
}
