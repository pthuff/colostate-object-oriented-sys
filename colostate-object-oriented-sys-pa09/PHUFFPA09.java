/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA09
 * 
 * 								PHUFFPA09 Class for parsing a text file, matching patterns, and outputting result
 * 
 * 								Date Created: 4/17/2019
 * 
 * 								Saved in: PHUFFPA09.java
 */
package PA09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PHUFFPA09 {

	public static void main(String[] args) 
	{
		//Declare variables
		String display = "";
		String file = "RetailStore.txt";
		File chosenFile = null;
		
		//Prompt user for file
		try 
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Choose a File");
			chooser.showOpenDialog(null);				
			chosenFile = chooser.getSelectedFile();
			
			if (chosenFile.exists())
			{
				file = chosenFile.toString();
			}
		}
		//Use default file if none selected
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null,"File not selected. Will continue with the default file: \"" + file + "\".");
				
		}
		
		//Declare the patterns and matchers
		Pattern titlePattern = Pattern.compile("\\b(?:(?<=title=\").*?(?=\" href=))");
		Pattern pricePattern = Pattern.compile("(?<=Suggested Retail Price:).*?(?=\")");
		Matcher titleMatcher, priceMatcher;		
		
		//Parse the file and match the patterns
		try 
		{
			//Read the file line by line
			BufferedReader reader = new BufferedReader(new FileReader(file));			
			boolean endOfFile = false;
			while (!endOfFile)
			{
				String line = reader.readLine();
				if (line != null)
				{					
					//Check each line for pattern match
					titleMatcher = titlePattern.matcher(line);
					priceMatcher = pricePattern.matcher(line);
					
					//If match found, add to display string
					if (titleMatcher.find() && priceMatcher.find()) 
					{
						//Add title and price
						display += "\r\nProduct Title: " + titleMatcher.group() + ", Suggested Retail Price: " + priceMatcher.group();
					}								
				}
				//End of file, close the reader
				else
				{
					endOfFile = true;
					reader.close();
					
					//Remove blank lines
					display = display.trim();
				}
			}
			
		} 
		catch (IOException e) 
		{
			//Display exception
			JOptionPane.showMessageDialog(null, "Error reading file!");
		}
		
		//Display results 
		JOptionPane.showMessageDialog(null, display);
		
		//Write results to file
		try 
		{		
			//Instantiate new file object, wrap in PrintWriter
			java.io.File outFile = new java.io.File("matcherout.txt");
			java.io.PrintWriter output = new java.io.PrintWriter(outFile);
			
			//Write to file
			output.print(display);
			output.close();
		} 
		catch (FileNotFoundException e) 
		{
			// Display exception
			JOptionPane.showMessageDialog(null, "Error writing file!");
		}
		
		//Close application
		System.exit(0);
	}
}
