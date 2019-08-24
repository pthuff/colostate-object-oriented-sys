/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA04
 * 
 * 								Product Class for scanning text file, passing to arrays, displaying arrays, and saving to new file.
 * 
 * 								Date Created: 2/22/2019
 * 
 * 								Saved in: Product.java
 */

import javax.swing.*;
import java.io.*;

public class Product {
	
	//Declare global arrays (I would never do this in the real-world, encapsulation with arraylist instead)
	public static String[] pName;
	public static double[] pPrice;

	//Main method
	public static void main(String[] args) 
	{ 
	    //Prompt user for filename
		JFileChooser fileChooser = new JFileChooser();
	    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
	    {
	      //Assign filename to variable
	      File file = fileChooser.getSelectedFile();

	      //Call and pass to readFromFile method
	      readFromFile(file);
	      
	      //Call sortArrays method
	      sortArrays();
	      
	      //Call writeToFile method
	      writeToFile();
	    }
	}
	
	//Method to parse file to arrays
	public static void readFromFile(File file)
	{
		//Initialize the global arrays
		pName = new String[50];
		pPrice = new double[50];
		
		//Try/catch to read file and parse to arrays
	    try
	    {
	    	//Instantiate a file and buffer reader
	    	FileReader buffFile = new FileReader(file);
	        BufferedReader reader = new BufferedReader(buffFile);
	        
	        //Declare local variables
	        String lineSplit[] = new String[2];
	        String display = "";
	    	
	        //Loop to parse file data to arrays
	        for (int i=0; i < pName.length; i++) 
	        {
	        	String line;
				line = reader.readLine();
				if(line != null)
	        	{
	                   lineSplit = line.split(",");
	                   pName[i] = lineSplit[0];
	                   pPrice[i] = Double.parseDouble(lineSplit[1]);
	                   display += pName[i] + ", " + "$" + String.format("%.2f", pPrice[i]) + "\n";
	        	}
	        	//System.out.println(pName[i] + " " + pPrice[i]);	        	
	        }
	        //Close the reader and display arrays
	        reader.close();        
	        JOptionPane.showMessageDialog(null, display);
	    }	    
	    catch (IOException ex)
	    {
	    	//Display exception
	    	JOptionPane.showMessageDialog(null, "Error!");
	    }
	}
	
	//Method to pass arrays to sort class
	public static void sortArrays()
	{
		Sort.selectionSort(pName, pPrice);
	}
	
	//Method to write sorted arrays to file
	public static void writeToFile()
	{
		//Declare a file and print stream
	    FileOutputStream output;
	    PrintStream printStream;
	    String display = "";
	    
		try 
		{
			//Instantiate a new file and print stream, pass values
			output = new FileOutputStream("sortedProducts.txt", false);
			printStream = new PrintStream(output);
			
			//Write formated/sorted arrays values to file
		    for (int i = 0; i < pName.length; i++)
		    {
		    	printStream.println(pName[i] + ", " + "$" + String.format("%.2f", pPrice[i]) + "\n");
		    	display += pName[i] + ", " + "$" + String.format("%.2f", pPrice[i]) + "\n";
		    }
		      
		    // Close the streams and display arrays
		    printStream.close();
		    output.close();
			JOptionPane.showMessageDialog(null, display);
		} 
		catch (IOException e) 
		{
			//Display exception
	    	JOptionPane.showMessageDialog(null, "Error!");
		}	    
	}
}
