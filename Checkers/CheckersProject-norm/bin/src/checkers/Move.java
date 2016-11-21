package checkers;

/**********************************************************************
 * Holds values for the coordinates for a piece's current location and
 * where it is trying to move to.
 *********************************************************************/
public class Move {

	/** The row to move from. */
	private int fromRow;
	
	/** The column to move from. */
	private int fromColumn;
	
	/** The row to move to. */
	private int toRow;
	
	/** The column to move to. */
	private int toColumn;
	
	/**********************************************************************
	 * Constructor.
	 *********************************************************************/
	public Move() {
		
	}
	
	/**********************************************************************
	 * Passes parameters to class move variables.
	 * 
	 * @param  mfromRow     The row from which to move.
	 * @param  mfromColumn  The column from which to move.
	 * @param  mtoRow       The row to move to.
	 * @param  mtoColumn    The column to move to.
	 *********************************************************************/
	public Move(final int mfromRow, final int mfromColumn, 
			final int mtoRow, final int mtoColumn) {
		
		fromRow = mfromRow;
		fromColumn = mfromColumn;
		toRow = mtoRow;
		toColumn = mtoColumn;
		
	}

	/**********************************************************************
	 * Returns the row from which to move.
	 * 
	 * @return    Returns the "from" row.
	 *********************************************************************/
	public final int getFromRow() {
		return fromRow;
	}

	/**********************************************************************
	 * Returns the column from which to move.
	 * 
	 * @return    Returns the "from" column.
	 *********************************************************************/
	public final int getFromColumn() {
		return fromColumn;
	}

	/**********************************************************************
	 * Returns the row to move to.
	 * 
	 * @return    Returns the "to" row.
	 *********************************************************************/
	public  final int getToRow() {
		return toRow;
	}

	/**********************************************************************
	 * Returns the column to move to.
	 * 
	 * @return    Returns the "to" column.
	 *********************************************************************/
	public final int getToColumn() {
		return toColumn;
	}
	
}
