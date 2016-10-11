package checkers;

public class DoubleDisk extends CheckersPiece {

	protected DoubleDisk(Player player) {
		super(player);
	}

	public String type() {
		return "DoubleDisk";
	}

	public boolean isValidMove(Move move, ICheckersPiece[][] board) {

		int rPos = 0;
		int cPos = 0;

		if (Math.abs(move.toRow - move.fromRow) == 1 && Math.abs(move.toColumn - move.fromColumn) == 1) {

			return true;

		}

		if (Math.abs(move.toRow - move.fromRow) == 2 && Math.abs(move.toColumn - move.fromColumn) == 2) {
			if (board[move.fromRow][move.fromColumn].player() == Player.User) {
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == 2) {
					rPos = 1;
					cPos = 1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.Other) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == -2) {
					rPos = -1;
					cPos = -1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.Other) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == -2) {
					rPos = 1;
					cPos = -1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.Other) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == 2) {
					rPos = -1;
					cPos = 1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.Other) {
						return true;
					}
				}
			}
			else if (board[move.fromRow][move.fromColumn].player() == Player.Other) {
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == 2) {
					rPos = 1;
					cPos = 1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.User) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == -2) {
					rPos = -1;
					cPos = -1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.User) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == 2 && move.toColumn - move.fromColumn == -2) {
					rPos = 1;
					cPos = -1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.User) {
						return true;
					}
				}
				if (move.toRow - move.fromRow == -2 && move.toColumn - move.fromColumn == 2) {
					rPos = -1;
					cPos = 1;
					if (board[move.fromRow + rPos][move.fromColumn + cPos].player() == Player.User) {
						return true;
					}
				}
			}

		}

		return false;

	}

}
