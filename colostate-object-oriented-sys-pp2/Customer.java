/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 2
 * 
 * 								Customer class for creating customer objects and holding customer information.
 * 
 * 								Date Created: 3/07/2019
 * 
 * 								Saved in: Customer.java
 */
package PP2;

public class Customer {
	
	private int id;
	private String fName, lName;
	private double amount;
	private CreditCard card;
	
	public Customer(String fName, String lName, int id, double amount, CreditCard card) 
	{
		super();
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.amount = amount;
		this.card = card;		
	}
	
	// add public setter/getter methods, and also the toString method
	@Override
	public String toString() 
	{
		//Override toSting method for Customer info
		return "Customer ID#: " + id + "\nFirst Name: " + fName + "\nLast Name: " + lName + "\nAmount: $" + amount + "\n" + card.toString();
				
	}
	
	public String fileString() 
	{
		//The string written to file
		return "Customer ID#: " + id + ", First Name: " + fName + ", Last Name: " + lName + ", Amount: $" + amount + ", Card Number: ";
	}
	
	//Getters and Setters
	public String getfName() 
	{
		return fName;
	}

	public String getlName() 
	{
		return lName;
	}

	public double getAmount() 
	{
		return amount;
	}

	public CreditCard getCard() 
	{
		return card;
	}
		
}
