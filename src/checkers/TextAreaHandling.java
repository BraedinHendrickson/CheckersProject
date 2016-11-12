package checkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextAreaHandling {
	 File sessionFile;
	 File rankingFile;
	 FileReader reader;
	 FileWriter writer;
	
	TextAreaHandling() {
	
		sessionFile = new File("sessionText.txt");
		rankingFile = new File("rankingText.txt");
		
		
		PrintWriter printer;
		try {
			printer = new PrintWriter(sessionFile);
			printer.print("");
			printer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
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
                str = str+((char) character);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
	
	
	 public void writeSession(String str) {
		 
		 
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
	                str = str+((char) character);
	            }
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			if (str.equals("")){
				str = "No Rankings Currently.\n\n Be the first to win and become #1!!!";
			}
	        return str;
	    }
		
		
		 public void writeRanking(String str) {
			 
			 
			 try {
					writer = new FileWriter(rankingFile, true);
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
		

	 
	 
}
