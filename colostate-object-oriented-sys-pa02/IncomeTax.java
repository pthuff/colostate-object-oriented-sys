/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA02
 * 
 * 								IncomeTax Class for calculating income based on filing status and income amount
 * 
 * 								Date Created: 1/30/2019
 * 
 * 								Saved in: IncomeTax.java
 */

import javax.swing.JOptionPane;

public class IncomeTax {
	public static void main(String[] args) {
		
		//Input income
		String incomeInputVaue =JOptionPane.showInputDialog("Enter income of one person. Enter value as a whole number, without any decimal, e.g., 15125");
		
		//Convert the value to double
		double income=Double.parseDouble(incomeInputVaue);
		
		//Input filing status
		String statusInString =JOptionPane.showInputDialog("Enter status of person, Enter either the value of Single, or Married.");
		
		//Declare local variable
		int status = 0;
		
		//Switch statement to set filing status
		switch(statusInString){	   
        case "Single":
        	status = 0;
            break;
        case "Married":
        	status = 1;
            break;
        default:
        	// Default section of the code is executed if the value entered does not match the abobe values
        	JOptionPane.showMessageDialog(null, "Invalid entry. Exiting application.");
        	
        	//Close the application
        	System.exit(0);
		}
		
		double incomeTax = 0;
	    
    	//Calculate tax for single filers
	    if (status == 0)
	    {
	    	if (income <= 8350)
	    	{
	    		incomeTax = (0.10 * income);
	    	}
	    	else if (income > 8350 && income <= 33950)
	    	{
	    		incomeTax = (0.10 * 8350 + (0.15 * (income - 8350)));
	    	}
	    	else if (income > 33950 && income <= 82250)
	    	{
	    		incomeTax = (0.10 * 8350 + (0.15 * 25600 + (0.25 * (income - 33950))));
	    	}
	    	else if (income > 82250 && income <= 171550)
	    	{
	    		incomeTax = (0.10 * 8350 + (0.15 * 25600 + (0.25 * 48300 + (0.28 * (income - 82250)))));
	    	}
	    	else if (income > 171550 && income <= 372950)
	    	{
	    		incomeTax = (0.10 * 8350 + (0.15 * 25600 + (0.25 * 48300 + (0.28 * 89300 + (0.33 * (income - 171500))))));
	    	}
	    	else if (income > 372950)
	    	{
	    		incomeTax = (0.10 * 8350 + (0.15 * 25600 + (0.25 * 48300 + (0.28 * 89300 + (0.33 * 201400 + (0.35 * (income - 372950)))))));
	    	}
	    }
	    
	    //Calculate tax for married filing jointly
	    else if (status == 1)
	    {
	    	if (income <= 16700)
	    	{
	    		incomeTax = (0.10 * income);
	    	}
	    	else if (income > 16700 && income <= 67900)
	    	{
	    		incomeTax = (0.10 * 16700 + (0.15 * (income - 16700)));
	    	}
	    	else if (income > 67900 && income <= 137050)
	    	{
	    		incomeTax = (0.10 * 16700 + (0.15 * 51200 + (0.25 * (income - 67900))));
	    	}
	    	else if (income > 137050 && income <= 208850)
	    	{
	    		incomeTax = (0.10 * 16700 + (0.15 * 51200 + (0.25 * 69150 + (0.28 * (income - 137050)))));
	    	}
	    	else if (income > 208850 && income <= 372950)
	    	{
	    		incomeTax = (0.10 * 16700 + (0.15 * 51200 + (0.25 * 69150 + (0.28 * 71800 + (0.33 * (income - 208850))))));
	    	}
	    	else if (income > 372950)
	    	{
	    		incomeTax = (0.10 * 16700 + (0.15 * 51200 + (0.25 * 69150 + (0.28 * 71800 + (0.33 * 164100 + (0.35 * (income - 372950)))))));
	    	}
	    }
	    
	    //Display results
	    JOptionPane.showMessageDialog(null, "Income = " + income + "\n" + "Status = "+ statusInString  + "\n" + "Tax= " + incomeTax);
	    
	    //Close application
	    System.exit(0);	    
	}   
}
