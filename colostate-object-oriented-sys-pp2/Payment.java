/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 2
 * 
 * 								Payment class for inputting/outputting customer, payment, and credit card information.
 * 
 * 								Date Created: 3/07/2019
 * 
 * 								Saved in: Payment.java
 */
package PP2;

import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

public class Payment {

	public static Validation validating;
	public static HashCode hashing;
	public static Customer[] customers;
	//Added this to track the customer count and use for index
	private static int customerCount = 0;

	// this will check whether a card is valid
	public static Boolean isValidCard(String number)
	{
		//Check if valid number
		return validating.aValidNumber(number);

	}// end of the isValidCard method

	// creates a hash code for the credit card number to be stored in file
    public static String createHashCode(String number)
    {
    	//Get a hash code
    	return hashing.getHashCode(number);

	}// end of the createHashCode method


     // it adds a new customer to the array of customers once the payment was successful
 	 public static void addCustomer(Customer customer)
 	 {
 		//Assign new customer to index value 
 		 customers[customerCount] = customer;
 		 
 		//Increment customer count
 		 customerCount++;
 		
 	 } // end of the addCustomer method


	// it displays the payments AVG, MAX payment, and MIN payment,
	// only for accepted payments with valid cards
	public static void displayStat()
	{
		//Declare local variables
		double avg = 0.00;
		double max = 0.00;
		double min = Double.MAX_VALUE;
		double total = 0.00;
		String maxName = null;
		String minName = null;; 
		String display = null;
		
		//Calculate the AVG, MAX payment, and MIN payment
		for (int i = 0; i < customerCount; i++)
		{
			if (customers[i].getAmount() < min)
			{
				min = (customers[i].getAmount());
				minName = (customers[i].getfName() + " " + customers[i].getlName());
			}
			if (customers[i].getAmount() > max)
			{
				max = (customers[i].getAmount());
				maxName = (customers[i].getfName() + " " + customers[i].getlName());
			}
			//Total it up
			total += (customers[i].getAmount());
		}
		//Calculate the average
		avg = (total / customerCount);
		
		//Format display of stats and pass to JOptionPane
		display = "Average spent:  $" + String.format("%.2f", avg) + "\nMax spent: " + maxName + "  $" + String.format("%.2f", max) + "\nMin spent: " + minName + "  $" + String.format("%.2f", min) + "\n";
		JOptionPane.showMessageDialog(null, display, "Customer Spending Info", JOptionPane.INFORMATION_MESSAGE);
		
	}// end of the displayStat method


	// write data to file, the credit card number should be encrypted
	// using one-way hash method in the Hashing class
    public static void writeToFile()
    {
    	//Declare local variables
    	String display = "";
    	Long number;
    	
    	try 
    	{
    		//Instantiate new file object, wrap in PrintWriter
        	java.io.File file = new java.io.File("Customer.txt");
			java.io.PrintWriter output = new java.io.PrintWriter(file);
			
			//Print each array index to the output file
			for (int i = 0; i < customerCount; i++)
			{
				//Get each card number
				number = customers[i].getCard().getNumber();
				
				//Write to output using fileString method
				display = customers[i].fileString() + " " + hashing.getHashCode(String.valueOf((number)));
				output.println(display);
			}
			//Close the writer
			output.close();
			
		} 
    	catch (FileNotFoundException e) 
    	{
    		//Display exception
	    	JOptionPane.showMessageDialog(null, "Error writing file!");
		}   	

    } // end of the writeToFile method


	// the main entry method of the program that will get data from user and
	// perform the business logic
	public static void main(String[] args) 
	{
		hashing = new HashCode();
		validating = new  Validation();
		
		int n = 0; // must hold the number of customers based on the user input
		
		//Try/Catch for number of customers input
		try
		{
			//Input number of customers
			n = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of customers in whole number, e.g., 3"));
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Value must be a whole number.");
			
			//Close application
		    System.exit(0);
		}
		
        // input the number of customers and stor it into variable n
		customers = new Customer[n];
				
		//Declare local variables
		int customerId = 0;
		double amount = 0.00;
		String number = null;
		String firstName = null;
		String lastName = null;
		String exp = null;
		
		//New Customer object to hold all the values
		Customer newCustomer;
				
		//Loop for inputs from user
		for (int i = 0; i < n; i++)
		{
			try
			{
				//Input customer id
				customerId = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter customer's ID#: "));
								
				//Input first and last name with validation
				boolean nameValid = false;
				while (nameValid != true)
				{
					firstName = JOptionPane.showInputDialog(null,"Enter customer's first name: ");
					lastName = JOptionPane.showInputDialog(null,"Enter customer's last name: ");
					nameValid = (firstName != null) && firstName.matches("^[A-Z][a-z]*$") && (lastName != null) && lastName.matches("^[A-Z][a-z]*$");
					if (nameValid != true)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid first and last name.");
					}
				}
				
				//Input amount
				amount = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter $.$$ amount: "));
								
				//Input card number with validation
				boolean numValid = false;
				while (numValid != true)
				{
					number = JOptionPane.showInputDialog(null, "Enter credit card number: ");
					numValid = (number != null) && number.matches("^[0-9]{1,19}$");
					if (numValid != true)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid number.");
					}
				}
				
				//Input exp date with validation
				boolean expValid = false;
				while (expValid != true)
				{
					exp = JOptionPane.showInputDialog(null, "Enter expiration date MMYYY: ");
					expValid = (exp != null) && exp.matches("^(0[1-9]|1[0-2])([0-9]{4})$");
					if (expValid != true)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid exp date. (e.g., 032020)");
					}
				}
				
				//Validate card number
				if (isValidCard(number))
					{
						JOptionPane.showMessageDialog(null, "Credit card successfully validated!");
					}
				else 
					{
						JOptionPane.showMessageDialog(null, "Invalid card number. Bye!");
						
						//Close application
					    System.exit(0);
					}
				
				//Pass values to new Customer object
				newCustomer = new Customer(firstName, lastName, customerId, amount, new CreditCard(Long.parseLong(number), exp));
				
				//Add to array
				addCustomer(newCustomer);				
				
				//Display customer payment info
				JOptionPane.showMessageDialog(null, "Payment processed.\n"+ newCustomer.toString());
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Bye!");
				
				//Close application
			    System.exit(0);
			}			
		}
		
		//Display stats for more than one customer and write to file
		if (n == 1)
		{
			writeToFile();
			
			//Close application
		    System.exit(0);
		}
		else
		{
			displayStat();
			writeToFile();
			
			//Close application
		    System.exit(0);
		}

	}
}
