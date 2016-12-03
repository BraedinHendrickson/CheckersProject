package checkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*********************************************************
 * The class that handles the text area.
 *
 *********************************************************/
public class TextAreaHandling {
	/** The file for the session. */
	private File sessionFile;
	
	/** The file for the rank. */
	private File rankingFile;
	
	/** THe file reader. */
	private FileReader reader;
	
	/**  The file writer. */
private FileWriter writer;
	

/*****************************************************
 * Default Constructor.
 ***************************************************/
	TextAreaHandling() {
	
		sessionFile = new File("sessionText.txt");
		rankingFile = new File("rankingText.txt");
		
		setUp();
	}
	
	/*****************************************************
	 * Creates a PrintWriter object.
	 ***************************************************/
	public void setUp() {
		PrintWriter printer;
		try {
			printer = new PrintWriter(sessionFile);
			printer.print("");
			printer.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
	}
	
	/****************************************************
	 * Loads the session file.
	 * @return A String for keeping track of score.
	 **************************************************/
	public String readSession() {
		
		try {
			reader = new FileReader(sessionFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String str = "";
		try {
            int character;
 
            while ((character = reader.read()) != -1) {
                str = str + ((char) character);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
	
	/*******************************************************
	 * Saves the session.
	 * @param str The name that the file will be named.
	 ********************************************************/
	 public void writeSession(final String str) {
		 
		 
		 try {
				writer = new FileWriter(sessionFile, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		 
	        try {
	            writer.write(str);
	            writer.write("\r\n");   // write new line
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	    }
	
	 /******************************************************
	  * Reads the saved files to provide a ranking.
	  * @return The players initials.
	  *******************************************************/
	 public String readRanking() {
			
			try {
				reader = new FileReader(rankingFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			String str = "";
			try {
	            int character;
	 
	            while ((character = reader.read()) != -1) {
	                str = str + ((char) character);
	            }
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			if (str.equals("")) {
				str = "No Rankings Currently.\n\n Be the first to win!!!";
			}
	        return str;
	    }
		
	 /**********************************************************
		 * Assigns the rankings.
		 * @param str The name of the player.
		 ********************************************************/
		 public void writeRanking(final String str) {
			 
			 
			 try {
					writer = new FileWriter(rankingFile, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			 
			 
		        try {
		            writer.write(str + "   Won on: " + new Date().toString());
		            writer.write("\r\n");   // write new line
		            writer.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		 
		    }
		

	 
	 
}