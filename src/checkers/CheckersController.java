package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/************************************************************
 * The Controller class for checkers.
 *********************************************************/
public class CheckersController {

	/**The model for the game. **/
	private CheckersModel model;
	
	/**The panel for the game. **/
	private CheckersView view;
	
	/**The save/load for scores. **/
	private TextAreaHandling taHandler;
	
	/**The drop down menu. **/
	private PopupMenu menu;
	
	/**The popup for when you win. **/
	private PopupWinner popup;
	
	
	/***************************************************************************
	 * Constructor for the CheckersController.
	 **************************************************************************/
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
		displayAdditional();
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
			
			// If source is select button from popup menu.
			if (e.getSource() == menu.getSelectButton()) {
				if (menu.getOption().substring(7, 8).equals("a")) {
					menu.performOperation(0);
				} else if (menu.getOption().substring(7, 8).equals("4")) {
					menu.close();
					model.reset();
					taHandler.setUp();
					displayBoard();
					displayAdditional();
				} else {
					menu.performOperation(Integer.parseInt(menu.getOption
							().substring(7, 8)));
				}
				
			// If source is option button.	
			} else if (e.getSource() == view.getOptionButton()) {
				menu.clearPanel();
				menu.setCBoxIndex();
				menu.setCBoxIntro();
				menu.setRunning();
				menu.setVisible();
				
			// If source is cBox Zero.
			} else if (e.getSource() == menu.getCBoxZero()) {
				menu.setColorSelectZero(menu.getCBoxZero().getSelectedIndex());
				displayBoard();
			
			// If source is cBox One.
			} else if (e.getSource() == menu.getCBoxOne()) {	
				menu.setColorSelectOne(menu.getCBoxOne().getSelectedIndex());
				displayBoard();
				
			// If source is session button.
			} else if (e.getSource() == view.getGameInfoButton()) {
			
				view.setTextArea(taHandler.readSession());
				
			// If source is ranking button.
			} else if (e.getSource() == view.getScoreBoardButton()) {
				view.setTextArea(taHandler.readRanking());
				
			// If source is select button for winning menu.
			} else if (e.getSource() == popup.getSelectButton()) {
				taHandler.writeRanking(popup.selection());
				popup.close();
				
			// Else find if source was a board button.
			} else {
				
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (e.getSource() == view.getButton(row, col)) {
							model.actionHandling(row, col);
							model.setHelper(menu.getHelper());
							model.calcHelper(row, col);
							displayBoard();
							displayAdditional();
							break;
						}
					}
				}
			}
			
			
		}
	}
	
	/*******************************************************************
	 *  Sets the icons and backgrounds for the playing board.
	 ******************************************************************/
	private void displayBoard() {
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				view.setButtonIcon(row, col, model.boardLayout(row, col));
				view.setBackground(row, col, menu.getColorSelectZero(), 
						menu.getColorSelectOne());
			}
		}
		
		if (menu.getHelper()) {
			if (!model.getFirstClick()) {
				int row = 0;
				int col = 0;
				int count = 0;
				for (int item : model.getHelperList()) {
					if (count % 2 == 0) {
						row = item;
					} else {
						col = item;
						view.setHelper(row, col);
					}
					count++;
				}
			}

			if (model.isGameOver()) {
				view.gameOver();
				popup.setVisible();
			}
		}
	}
	
	/*******************************************************************
	 * Sets text area information and turn label.
	 ******************************************************************/
	private void displayAdditional() {
		view.setTurnLabel(model.currentStatusLabel());
		view.setTurnImage(model.currentPlayer().ordinal());
		view.setTimer(model.currentPlayer().ordinal());
		taHandler.writeSession(model.getSessionMove());
		view.setTextArea(taHandler.readSession());
		model.setHelper(menu.getHelper());
	}
	

}