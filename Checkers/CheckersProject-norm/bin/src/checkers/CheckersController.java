package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckersController {

	private CheckersModel model;
	private CheckersView view;
	private TextAreaHandling taHandler;
	private PopupMenu menu;
	private PopupWinner popup;

	public CheckersController(CheckersView view, CheckersModel model, PopupMenu menu) {
		taHandler = new TextAreaHandling();
		popup = new PopupWinner();
		this.menu = menu;
		this.model = model;
		this.view = view;

		this.view.addBoardListener(new ButtonListener());
		this.menu.addBoardListener(new ButtonListener());
		this.popup.addBoardListener(new ButtonListener());
		
		
		displayBoard();
	}

	/*******************************************************************
	 * Action events for mouse clicks.
	 ******************************************************************/
	class ButtonListener implements ActionListener { 

		/*******************************************************************
		 * Action event detection.
		 * 
		 * @param e
		 *            Action Event.
		 ******************************************************************/
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() == menu.getSelectButton()) {
				if (menu.getOption().substring(7, 8).equals("a")) {
					menu.performOperation(0);
				} else if (menu.getOption().substring(7, 8).equals("4")) {
					menu.close();
					model.reset();
					taHandler.setUp();
					displayBoard();
				} else {
					menu.performOperation(Integer.parseInt(menu.getOption().substring(7, 8)));
				}
			}
			

			if (e.getSource() == view.getOptionButton()) {
				menu.setRunning();
				menu.setVisible();
				
			} else if (e.getSource() == view.getGameInfoButton()) {
				view.setTextArea(taHandler.readSession());
			} else if (e.getSource() == view.getScoreBoardButton()) {
				view.setTextArea(taHandler.readRanking());
			} else if (e.getSource() == popup.getSelectButton()) {
				taHandler.writeRanking(popup.selection());
				popup.close();
			} else {
				
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (e.getSource() == view.getButton(row, col)) {
							model.actionHandling(row, col);
							displayBoard();
							break;
						}
					}
				}
			}
			
			
		}
	}
	
	
	private void displayBoard() {
		view.setTurnLabel(model.currentStatusLabel());
		view.setTurnImage(model.currentPlayer().ordinal());
		view.setTimer(model.currentPlayer().ordinal());
		taHandler.writeSession(model.getSessionMove());
		view.setTextArea(taHandler.readSession());
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				view.setButtonIcon(row, col, model.boardLayout(row, col));
			}
		}
		
		if (model.isGameOver()) {
			view.gameOver();
			popup.setVisible();
		}
	}
	
	
	
	
	
	
	
}
