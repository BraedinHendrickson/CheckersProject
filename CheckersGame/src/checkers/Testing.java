package checkers;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Norman
 *
 */
public class Testing {
	
	CheckersModel model = new CheckersModel();
	
	Move move = new Move();
	 
	CheckersPiece singleRed = new SingleDisk(Player.Red);
	CheckersPiece doubleRed = new DoubleDisk(Player.Red);
	CheckersPiece singleWhite = new SingleDisk(Player.White);
	CheckersPiece doubleWhite = new DoubleDisk(Player.White);
	
	private void clearBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				model.setBoardPosition(i, j, null);
			}
		}
	}
	
	@Test
	public void coordinatesShouldExist() {
		assertNotNull(model.pieceAt(0, 0));
		assertNotNull(model.pieceAt(2, 6));
		assertNotNull(model.pieceAt(5, 3));
		assertNotNull(model.pieceAt(6, 4));
	}
		
		@Test
		public void coordinatesShouldNotExist() {
		assertNull(model.pieceAt(0, 1));
		assertNull(model.pieceAt(1, 0));
		assertNull(model.pieceAt(5, 4));
		assertNull(model.pieceAt(7, 0));
	}
	
		@Test
		public void checkPlayers() {
		assertEquals(model.pieceAt(0, 0).player(), Player.Red);
		assertEquals(model.pieceAt(2, 4).player(), Player.Red);
		assertEquals(model.pieceAt(1, 1).player(), Player.Red);
		
		assertEquals(model.pieceAt(7, 1).player(), Player.White);
		assertEquals(model.pieceAt(5, 5).player(), Player.White);
		assertEquals(model.pieceAt(6, 6).player(), Player.White);
		
	}
		
		@Test
		public void checkPlayerType() {
			
		assertEquals(model.pieceAt(0, 0).type(), "SingleDisk");
		assertEquals(model.pieceAt(2, 4).type(), "SingleDisk");
		assertEquals(model.pieceAt(7, 1).type(), "SingleDisk");
		assertEquals(model.pieceAt(5, 5).type(), "SingleDisk");
		
		model.setBoardPosition(0, 0, doubleRed);
		model.setBoardPosition(2, 4, doubleRed);
		model.setBoardPosition(7, 1, doubleWhite);
		model.setBoardPosition(5, 5, doubleWhite);
		
		assertEquals(model.pieceAt(0, 0).type(), "DoubleDisk");
		assertEquals(model.pieceAt(2, 4).type(), "DoubleDisk");
		assertEquals(model.pieceAt(7, 1).type(), "DoubleDisk");
		assertEquals(model.pieceAt(5, 5).type(), "DoubleDisk");

		
	}
		
		@Test
		public void checkValidMoveSingleDisk() {
			clearBoard();
			
			model.setBoardPosition(1, 1, singleWhite);
			move = new Move(1, 1, 0, 0);
			model.move(move);
			clearBoard();
			
			model.setBoardPosition(2, 2, singleRed);
			move = new Move(2, 2, 3, 3);
		assertTrue(model.isValidMove(move));
			move = new Move(2, 2, 3, 1);
		assertTrue(model.isValidMove(move));
		
			model.setBoardPosition(3, 3, singleWhite);
			move = new Move(2, 2, 4, 4);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(3, 1, singleWhite);	
			move = new Move(2, 2, 4, 0);
		assertTrue(model.isValidMove(move));
		
			move = new Move(3, 3, 5, 7);
		assertFalse(model.isValidMove(move));
		
			move = new Move(2, 2, 6, 6);
		assertFalse(model.isValidMove(move));
		
			model.setBoardPosition(2, 2, null);
			model.setBoardPosition(3, 3, null);
			model.setBoardPosition(3, 1, null);
			
			model.setBoardPosition(0, 0, singleRed);
			move = new Move(0, 0, 1, 1);
			model.move(move);
			model.setBoardPosition(1, 1, null);
		
			model.setBoardPosition(3, 3, singleWhite);
			move = new Move(3, 3, 2, 2);
		assertTrue(model.isValidMove(move));
			move = new Move(3, 3, 2, 4);
		assertTrue(model.isValidMove(move));
			
			model.setBoardPosition(2, 2, singleRed);
			move = new Move(3, 3, 1, 1);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(2, 4, singleRed);	
			move = new Move(3, 3, 1, 5);
		assertTrue(model.isValidMove(move));
			
		model.setBoardPosition(3, 3, null);
		model.setBoardPosition(2, 2, null);
		model.setBoardPosition(3, 1, null);
		

	}
		
		
		@Test
		public void checkValidMoveDoubleDisk() {
			clearBoard();
			
			model.setBoardPosition(1, 1, singleWhite);
			move = new Move(1, 1, 0, 0);
			model.move(move);
			clearBoard();
			
			model.setBoardPosition(2, 2, doubleRed);
			move = new Move(2, 2, 3, 3);
		assertTrue(model.isValidMove(move));
			move = new Move(2, 2, 3, 1);
		assertTrue(model.isValidMove(move));
			move = new Move(2, 2, 1, 3);
		assertTrue(model.isValidMove(move));
			move = new Move(2, 2, 1, 1);
		assertTrue(model.isValidMove(move));
		
			model.setBoardPosition(3, 3, singleWhite);
			move = new Move(2, 2, 4, 4);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(3, 1, singleWhite);	
			move = new Move(2, 2, 4, 0);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(1, 3, singleWhite);
			move = new Move(2, 2, 0, 4);
			assertTrue(model.isValidMove(move));
			model.setBoardPosition(1, 1, singleWhite);	
			move = new Move(2, 2, 0, 0);
		assertTrue(model.isValidMove(move));
		
			move = new Move(2, 2, 5, 7);
		assertFalse(model.isValidMove(move));
		
			model.setBoardPosition(2, 2, null);
			model.setBoardPosition(3, 3, null);
			model.setBoardPosition(3, 1, null);
			model.setBoardPosition(1, 3, null);
			model.setBoardPosition(1, 1, null);
			
			model = new CheckersModel();
			clearBoard();
		
			model.setBoardPosition(3, 3, doubleWhite);
			move = new Move(3, 3, 2, 2);
		assertTrue(model.isValidMove(move));
			move = new Move(3, 3, 2, 4);
		assertTrue(model.isValidMove(move));
			move = new Move(3, 3, 4, 2);
		assertTrue(model.isValidMove(move));
			move = new Move(3, 3, 4, 4);
		assertTrue(model.isValidMove(move));
			
			model.setBoardPosition(2, 2, singleRed);
			move = new Move(3, 3, 1, 1);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(2, 4, singleRed);	
			move = new Move(3, 3, 1, 5);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(4, 2, singleRed);
			move = new Move(3, 3, 5, 1);
		assertTrue(model.isValidMove(move));
			model.setBoardPosition(4, 4, singleRed);	
			move = new Move(3, 3, 5, 5);
		assertTrue(model.isValidMove(move));
			
		model.setBoardPosition(3, 3, null);
		model.setBoardPosition(2, 2, null);
		model.setBoardPosition(2, 4, null);
		model.setBoardPosition(4, 2, null);
		model.setBoardPosition(4, 4, null);
		

	}
		
		@Test
		public void checkPromotion() {
			clearBoard();
			
				model.setBoardPosition(1, 1, singleWhite);
				move = new Move(1, 1, 0, 0);
				model.move(move);
			assertEquals(model.pieceAt(0, 0).type(), "DoubleDisk");
				model.setBoardPosition(0, 0, null);
			
				model.setBoardPosition(6, 6, singleRed);
				move = new Move(6, 6, 7, 7);
				model.move(move);
			assertEquals(model.pieceAt(7, 7).type(), "DoubleDisk");
				model.setBoardPosition(7, 7, null);
				
		}
		
		@Test
		public void checkPieceDeletion() {
			clearBoard();
			
			model.setBoardPosition(1, 1, singleWhite);
			move = new Move(1, 1, 0, 0);
			model.move(move);
			model.setBoardPosition(0, 0, null);
			
			
				model.setBoardPosition(0, 0, singleRed);
				model.setBoardPosition(1, 1, singleWhite);
				model.setBoardPosition(3, 1, singleWhite);
				move = new Move(0, 0, 2, 2);
				model.move(move);
			assertNull(model.pieceAt(1, 1));
				move = new Move(2, 2, 4, 0);
				model.move(move);
			assertNull(model.pieceAt(1, 1));
				model.setBoardPosition(4, 0, null);
				
				model.setBoardPosition(7, 7, singleWhite);
				model.setBoardPosition(6, 6, singleRed);
				model.setBoardPosition(4, 6, singleRed);
				move = new Move(7, 7, 5, 5);
				model.move(move);
			assertNull(model.pieceAt(6, 6));
				move = new Move(5, 5, 3, 7);
				model.move(move);
			assertNull(model.pieceAt(4, 6));
				model.setBoardPosition(3, 7, null);
		} 
		
		@Test
		public void checkForMultipleJumps() {
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 5, singleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(4, 2, singleRed);
			move = new Move(7, 5, 5, 3);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
			move = new Move(5, 3, 3, 1);
			model.move(move);
		
			clearBoard();	
			model.setBoardPosition(0, 0, singleRed);
			model.setBoardPosition(1, 1, singleWhite);
			model.setBoardPosition(3, 3, singleWhite);
			move = new Move(0, 0, 2, 2);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
		
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 5, singleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(4, 4, singleRed);
			move = new Move(7, 5, 5, 3);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
		
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 5, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(4, 2, singleRed);
			move = new Move(7, 5, 5, 3);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
	
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 5, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(4, 4, singleRed);
			move = new Move(7, 5, 5, 3);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
	
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 5, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(6, 2, singleRed);
			move = new Move(7, 5, 5, 3);
			model.move(move);
		assertTrue(model.checkSameTurn(move));

			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 3, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(6, 6, singleRed);
			move = new Move(7, 3, 5, 5);
			model.move(move);
		assertTrue(model.checkSameTurn(move));
		
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 3, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			move = new Move(7, 3, 5, 5);
			model.move(move);
		assertFalse(model.checkSameTurn(move));	
			
		} 
		
		@Test
		public void checkValidSameTurn() {
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 3, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(6, 6, singleRed);
			move = new Move(7, 3, 5, 5);
			model.move(move);
			move = new Move(5, 4, 3, 3);
		assertFalse(model.validSameTurn(move));
		
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 3, doubleWhite);
			model.setBoardPosition(6, 4, singleRed);
			model.setBoardPosition(6, 6, singleRed);
			move = new Move(7, 3, 5, 5);
			model.move(move);
			move = new Move(4, 5, 3, 3);
		assertFalse(model.validSameTurn(move));
		}
		
		@Test
		public void checkIsValidMove() {
			
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 3, doubleWhite);
			model.setBoardPosition(6, 4, doubleWhite);
			move = new Move(7, 3, 6, 4);
		assertFalse(model.isValidMove(move));
		}
		
		@Test
		public void checkGameOver() {
			assertFalse(model.isGameOver());
			
			model = new CheckersModel();
			clearBoard();	
			model.setBoardPosition(7, 7, doubleWhite);
			assertTrue(model.isGameOver());
			
			clearBoard();	
			model.setBoardPosition(7, 7, doubleRed);
			assertTrue(model.isGameOver());
		}
		
		@Test
		public void checkEnum() {
			Player.valueOf(Player.Red.toString());
		}
		
		
}
